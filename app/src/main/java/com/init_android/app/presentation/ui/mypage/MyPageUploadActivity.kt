package com.init_android.app.presentation.ui.mypage

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.init_android.R
import com.init_android.databinding.ActivityMyPageUploadBinding
import com.playtogether_android.app.presentation.base.BaseActivity

class MyPageUploadActivity : BaseActivity<ActivityMyPageUploadBinding>(R.layout.activity_my_page_upload) {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        backBtnListener()
    }

    private fun backBtnListener() {
        binding.ivMypageUploadBack.setOnClickListener {
            finish()
        }
    }
}