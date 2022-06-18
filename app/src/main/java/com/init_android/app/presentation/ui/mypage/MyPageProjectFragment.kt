package com.init_android.app.presentation.ui.mypage

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import com.init_android.R
import com.init_android.app.data.request.mypage.RequestCountProject
import com.init_android.app.data.request.mypage.RequestMyInfo
import com.init_android.app.presentation.ui.main.MainViewModel
import com.init_android.app.presentation.ui.mypage.viewmodel.MyPageViewModel
import com.init_android.databinding.FragmentMyPageProjectBinding
import com.playtogether_android.app.presentation.base.BaseFragment


class MyPageProjectFragment :
    BaseFragment<FragmentMyPageProjectBinding>(R.layout.fragment_my_page_project) {

    private val mainViewModel: MainViewModel by activityViewModels()
    private val myPageViewModel : MyPageViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initNetwork()
        initVisibility()
        initMove()

    }

    private fun initNetwork() {
        val mNum = mainViewModel.otherNum.value ?: 1
        val requestCountProject = RequestCountProject(
            mNum = mNum
        )
        myPageViewModel.postCountProject(requestCountProject)
        myPageViewModel.countProject.observe(viewLifecycleOwner) {
            binding.info = it
        }
    }

    private fun initVisibility() {
        //mNum intent로 연결
        val mNum = mainViewModel.otherNum.value ?: 1

        if(mNum != 1) {
            binding.clMyPageApprove.visibility = View.GONE
            binding.clMyPageHeart.visibility = View.GONE
        } else {
            binding.clMyPageApprove.visibility = View.VISIBLE
            binding.clMyPageUpload.visibility = View.VISIBLE
        }
    }

    private fun initMove() {
        binding.clMyPageUpload.setOnClickListener {
            startActivity(Intent(requireActivity(), MyPageUploadActivity::class.java))
        }
        binding.clMyPageApprove.setOnClickListener {
            startActivity(Intent(requireActivity(), MyPageReadyActivity::class.java))
        }
        binding.clMyPageHeart.setOnClickListener {
            startActivity(Intent(requireActivity(), MyPageZZimActivity::class.java))
        }
        binding.clMyPageJoin.setOnClickListener {
            startActivity(Intent(requireActivity(), MyPageJoinActivity::class.java))
        }
    }
}
