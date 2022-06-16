package com.init_android.app.presentation.ui.mypage

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import com.init_android.R
import com.init_android.app.data.request.mypage.RequestWaitingApproval
import com.init_android.app.data.response.mypage.ResponseUpload
import com.init_android.app.data.response.project.approve.ResponsemyWaitingApproval
import com.init_android.app.presentation.ui.mypage.adapter.ReadyAdapter
import com.init_android.app.presentation.ui.mypage.adapter.UploadAdapter
import com.init_android.app.presentation.ui.mypage.viewmodel.MyPageViewModel
import com.init_android.databinding.ActivityMyPageUploadBinding
import com.playtogether_android.app.presentation.base.BaseActivity

class MyPageUploadActivity : BaseActivity<ActivityMyPageUploadBinding>(R.layout.activity_my_page_upload) {

    private val myPageViewModel: MyPageViewModel by viewModels()
    private lateinit var uploadAdapter: UploadAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        backBtnListener()
        initNetwork()
    }

    private fun backBtnListener() {
        binding.ivMypageUploadBack.setOnClickListener {
            finish()
        }
    }

    private fun initNetwork() {
        Log.d("TEst", "TEST")
        val requestUpload = RequestWaitingApproval(mNum = 1)
        myPageViewModel.postUploadProject(requestUpload)
        uploadAdapter = UploadAdapter(1)
        binding.rvMypageUpload.adapter = uploadAdapter
        myPageViewModel.zzimList.observe(this) {
            uploadAdapter.setQuestionPost((it.projectInfoList) as MutableList<ResponseUpload.ProjectInfo>)
        }
    }
}