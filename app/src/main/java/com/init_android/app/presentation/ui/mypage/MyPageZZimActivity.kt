package com.init_android.app.presentation.ui.mypage

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import com.init_android.R
import com.init_android.app.data.request.mypage.RequestWaitingApproval
import com.init_android.app.data.response.project.approve.ResponsemyWaitingApproval
import com.init_android.app.presentation.ui.main.MainViewModel
import com.init_android.app.presentation.ui.mypage.adapter.ReadyAdapter
import com.init_android.app.presentation.ui.mypage.viewmodel.MyPageViewModel
import com.init_android.databinding.ActivityMyPageZzimBinding
import com.playtogether_android.app.presentation.base.BaseActivity

class MyPageZZimActivity : BaseActivity<ActivityMyPageZzimBinding>(R.layout.activity_my_page_zzim) {

    private val mainViewModel: MainViewModel by viewModels()
    private val myPageViewModel: MyPageViewModel by viewModels()
    private lateinit var readyAdapter: ReadyAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        backBtnListener()
        initNetwork()

    }

    private fun backBtnListener() {
        binding.ivMypageZzimBack.setOnClickListener {
            finish()
        }
    }

    private fun initNetwork() {
        val mNum = mainViewModel.otherNum.value ?: 1
        val requestWaitingApproval = RequestWaitingApproval(mNum = mNum)
        myPageViewModel.postZzimProject(requestWaitingApproval)
        readyAdapter = ReadyAdapter(1)
        binding.rvMypageZzim.adapter = readyAdapter
        myPageViewModel.zzimList.observe(this) {
            readyAdapter.setQuestionPost((it.projectInfoList) as MutableList<ResponsemyWaitingApproval.ProjectInfo>)
        }
    }
}