package com.init_android.app.presentation.ui.mypage

import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import com.init_android.R
import com.init_android.app.data.request.mypage.RequestWaitingApproval
import com.init_android.app.data.response.project.approve.ResponsemyWaitingApproval
import com.init_android.app.presentation.ui.main.MainViewModel
import com.init_android.app.presentation.ui.mypage.adapter.ReadyAdapter
import com.init_android.app.presentation.ui.mypage.viewmodel.MyPageViewModel
import com.init_android.databinding.ActivityMyPageReadyBinding
import com.playtogether_android.app.presentation.base.BaseActivity

class MyPageReadyActivity : BaseActivity<ActivityMyPageReadyBinding>(R.layout.activity_my_page_ready) {

    private val mainViewModel: MainViewModel by viewModels()
    private val myPageViewModel: MyPageViewModel by viewModels()
    private lateinit var readyAdapter: ReadyAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        backBtnListener()
        initNetwork()

    }

    private fun backBtnListener() {
        binding.ivMypageApproveBack.setOnClickListener {
            finish()
        }
    }

    private fun initNetwork() {
        val mNum = mainViewModel.otherNum.value ?: 1
        val requestWaitingApproval = RequestWaitingApproval(mNum = mNum)
        myPageViewModel.postWaitingApprove(requestWaitingApproval)
        readyAdapter = ReadyAdapter(1)
        binding.rvMypageApprove.adapter = readyAdapter
        myPageViewModel.waitingApprove.observe(this) {
            readyAdapter.setQuestionPost((it.projectInfoList) as MutableList<ResponsemyWaitingApproval.ProjectInfo>)
        }
    }
}