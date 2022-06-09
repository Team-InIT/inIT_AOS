package com.init_android.app.presentation.ui.mypage

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.init_android.R
import com.init_android.databinding.ActivityMyPageJoinBinding
import com.playtogether_android.app.presentation.base.BaseActivity

class MyPageJoinActivity : BaseActivity<ActivityMyPageJoinBinding>(R.layout.activity_my_page_join) {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        backBtnListener()
    }

    private fun backBtnListener() {
        binding.ivMypageJoinBack.setOnClickListener {
            finish()
        }
    }
}