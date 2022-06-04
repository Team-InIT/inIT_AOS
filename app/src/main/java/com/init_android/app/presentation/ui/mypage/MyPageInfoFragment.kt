package com.init_android.app.presentation.ui.mypage

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.init_android.R
import com.init_android.app.data.request.mypage.RequestMyInfo
import com.init_android.app.presentation.ui.mypage.adapter.MyPageTabAdapter
import com.init_android.app.presentation.ui.mypage.adapter.StackAdapter
import com.init_android.app.presentation.ui.mypage.viewmodel.MyPageViewModel
import com.init_android.databinding.FragmentMyPageInfoBinding
import com.playtogether_android.app.presentation.base.BaseFragment


class MyPageInfoFragment : BaseFragment<FragmentMyPageInfoBinding>(R.layout.fragment_my_page_info) {
    private lateinit var stackAdapter: StackAdapter

    private val myPageViewModel : MyPageViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        btnClickListener()
        linkClickListener()
        basicInfoListener()
        initNetwork()

    }

    override fun onResume() {
        super.onResume()
        initNetwork()

    }

    private fun btnClickListener() {
        binding.tvMypageBasicStackModify.setOnClickListener {
            val intentStack = Intent(requireActivity(), MyPageModifyStackActivity::class.java)
            startActivity(intentStack)
        }
    }

    private fun initNetwork() {
        val requestMyInfo = RequestMyInfo(
            mNum = 1
        )

        myPageViewModel.postMyInfo(requestMyInfo)

        myPageViewModel.myInfoData.observe(viewLifecycleOwner) {
            binding.user = it.mInfo
        }
    }

    //링크 수정 클릭 리스너
    private fun linkClickListener() {
        binding.tvMypageBasicLinkModify.setOnClickListener {
            val intentLink = Intent(requireActivity(), MyPageModifyLinkActivity::class.java)
            intentLink.putExtra("github", binding.tvMypageBasicGithubAnswer.text.toString())
            intentLink.putExtra("notion", binding.tvMypageBasicNotionAnswer.text.toString())
            intentLink.putExtra("blog", binding.tvMypageBasicBlogAnswer.text.toString())
            startActivity(intentLink)
        }
    }

    //기본 정보 수정 클릭 리스너
    private fun basicInfoListener() {
        binding.tvMypageBasicInfoModify.setOnClickListener {
            val intentBasicInfo = Intent(requireActivity(), MyPageModifyBasicInfoActivity::class.java)
            intentBasicInfo.putExtra("email", binding.tvMypageBasicInfoEmailAnswer.text.toString())
            intentBasicInfo.putExtra("belong", binding.tvMypageBasicInfoBelongAnswer.text.toString())
            intentBasicInfo.putExtra("academic", binding.tvMypageBasicInfoAcademicAnswer.text.toString())
            intentBasicInfo.putExtra("gender", binding.tvMypageBasicInfoGenderAnswer.text.toString())


            startActivity(intentBasicInfo)
        }
    }


}