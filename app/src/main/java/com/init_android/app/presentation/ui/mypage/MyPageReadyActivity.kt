package com.init_android.app.presentation.ui.mypage

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import androidx.fragment.app.viewModels
import com.init_android.R
import com.init_android.app.data.request.mypage.RequestWaitingApproval
import com.init_android.app.data.request.project.RequestProjectMember
import com.init_android.app.data.response.project.approve.ResponseProjectMember
import com.init_android.app.data.response.project.approve.ResponsemyWaitingApproval
import com.init_android.app.presentation.ui.mypage.adapter.ReadyAdapter
import com.init_android.app.presentation.ui.mypage.viewmodel.MyPageViewModel
import com.init_android.app.presentation.ui.open.partner.adapter.PartnerAdapter
import com.init_android.app.presentation.ui.open.viewmodel.ProjectViewModel
import com.init_android.databinding.ActivityMyPageReadyBinding
import com.playtogether_android.app.presentation.base.BaseActivity

class MyPageReadyActivity : BaseActivity<ActivityMyPageReadyBinding>(R.layout.activity_my_page_ready) {

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
        Log.d("TEst", "TEST")
        val requestWaitingApproval = RequestWaitingApproval(mNum = 1)
        myPageViewModel.postWaitingApprove(requestWaitingApproval)
        readyAdapter = ReadyAdapter(1)
        binding.rvMypageApprove.adapter = readyAdapter
        myPageViewModel.waitingApprove.observe(this) {
            readyAdapter.setQuestionPost((it.projectInfoList) as MutableList<ResponsemyWaitingApproval.ProjectInfo>)
        }
    }
}