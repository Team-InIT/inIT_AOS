package com.init_android.app.presentation.ui.mypage

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.init_android.R
import com.init_android.app.presentation.ui.mypage.adapter.MyPageTabAdapter
import com.init_android.app.presentation.ui.mypage.adapter.StackAdapter
import com.init_android.databinding.FragmentMyPageInfoBinding
import com.playtogether_android.app.presentation.base.BaseFragment


class MyPageInfoFragment : BaseFragment<FragmentMyPageInfoBinding>(R.layout.fragment_my_page_info) {
    private lateinit var stackAdapter: StackAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        btnClickListener()

    }

    private fun btnClickListener() {
        binding.tvMypageBasicInfoModify.setOnClickListener {
            val intentBasicInfo = Intent(requireActivity(), MyPageModifyBasicInfoActivity::class.java)
            startActivity(intentBasicInfo)
        }

        binding.tvMypageBasicStackModify.setOnClickListener {
            val intentStack = Intent(requireActivity(), MyPageModifyStackActivity::class.java)
            startActivity(intentStack)
        }

        binding.tvMypageBasicLinkModify.setOnClickListener {
            val intentLink = Intent(requireActivity(), MyPageModifyLinkActivity::class.java)
            intentLink.putExtra("github", binding.tvMypageBasicGithubAnswer.text.toString())
            intentLink.putExtra("notion", binding.tvMypageBasicNotionAnswer.text.toString())
            intentLink.putExtra("blog", binding.tvMypageBasicBlogAnswer.text.toString())
            startActivity(intentLink)
        }
    }


}