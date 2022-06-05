package com.init_android.app.presentation.ui.mypage

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import com.init_android.R
import com.init_android.app.data.request.mypage.RequestCountProject
import com.init_android.app.data.request.mypage.RequestMyInfo
import com.init_android.app.presentation.ui.mypage.viewmodel.MyPageViewModel
import com.init_android.databinding.FragmentMyPageProjectBinding
import com.playtogether_android.app.presentation.base.BaseFragment


class MyPageProjectFragment :
    BaseFragment<FragmentMyPageProjectBinding>(R.layout.fragment_my_page_project) {

    private val myPageViewModel : MyPageViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initNetwork()

    }

    private fun initNetwork() {
        val requestCountProject = RequestCountProject(
            mNum = 1
        )
        myPageViewModel.postCountProject(requestCountProject)
        myPageViewModel.countProject.observe(viewLifecycleOwner) {
            binding.tvMyPageJoinNum.setText(it.join)
            binding.tvMypageHeartNum.setText(it.zzim)
            binding.tvMyPageUploadNum.setText(it.upload)
            binding.tvMypageApproveNum.setText(it.disapproval)
        }
    }
}
