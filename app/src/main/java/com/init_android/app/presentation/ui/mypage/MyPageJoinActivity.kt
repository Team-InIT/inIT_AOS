package com.init_android.app.presentation.ui.mypage

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import com.init_android.R
import com.init_android.app.data.request.mypage.RequestWaitingApproval
import com.init_android.app.data.response.project.approve.ResponsemyWaitingApproval
import com.init_android.app.presentation.ui.mypage.adapter.ReadyAdapter
import com.init_android.app.presentation.ui.mypage.viewmodel.MyPageViewModel
import com.init_android.databinding.ActivityMyPageJoinBinding
import com.playtogether_android.app.presentation.base.BaseActivity

class MyPageJoinActivity : BaseActivity<ActivityMyPageJoinBinding>(R.layout.activity_my_page_join) {

    private val myPageViewModel: MyPageViewModel by viewModels()
    private lateinit var readyAdapter: ReadyAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        backBtnListener()
        initSetting()
        initIngNetwork()
    }

    private fun backBtnListener() {
        binding.ivMypageJoinBack.setOnClickListener {
            finish()
        }
    }

    private fun initSetting() {
        binding.tvMypageJoinIng.isSelected = true

        binding.tvMypageJoinIng.setOnClickListener {
            binding.tvMypageJoinIng.isSelected = true
            binding.tvMypageJoinFinish.isSelected = false
            initIngNetwork()
        }

        binding.tvMypageJoinFinish.setOnClickListener {
            binding.tvMypageJoinIng.isSelected = false
            binding.tvMypageJoinFinish.isSelected = true
            initEndNetwork()
        }
    }

    private fun initIngNetwork() {
        val requestWaitingApproval = RequestWaitingApproval(mNum = 1)
        myPageViewModel.postIngProject(requestWaitingApproval)
        readyAdapter = ReadyAdapter(1)
        binding.rvMypageJoin.adapter = readyAdapter
        myPageViewModel.waitingApprove.observe(this) {
            readyAdapter.setQuestionPost((it.projectInfoList) as MutableList<ResponsemyWaitingApproval.ProjectInfo>)
        }
    }

    private fun initEndNetwork() {
        val requestWaitingApproval = RequestWaitingApproval(mNum = 1)
        myPageViewModel.postEndProject(requestWaitingApproval)
        readyAdapter = ReadyAdapter(1)
        binding.rvMypageJoin.adapter = readyAdapter
        myPageViewModel.endApprove.observe(this) {
            readyAdapter.setQuestionPost((it.projectInfoList) as MutableList<ResponsemyWaitingApproval.ProjectInfo>)
        }
    }


}