package com.init_android.app.presentation.ui.mypage

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.init_android.R
import com.init_android.databinding.ActivityMyPageReadyBinding
import com.playtogether_android.app.presentation.base.BaseActivity

class MyPageReadyActivity : BaseActivity<ActivityMyPageReadyBinding>(R.layout.activity_my_page_ready) {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        backBtnListener()

    }

    private fun backBtnListener() {
        binding.ivMypageApproveBack.setOnClickListener {
            finish()
        }
    }
}