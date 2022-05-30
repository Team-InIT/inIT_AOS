package com.init_android.app.presentation.ui.mypage

import android.content.Intent
import android.os.Bundle
import com.init_android.R
import com.init_android.databinding.ActivityMyPageSettingBinding
import com.playtogether_android.app.presentation.base.BaseActivity

class MyPageSettingActivity : BaseActivity<ActivityMyPageSettingBinding>(R.layout.activity_my_page_setting) {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        movePage()

    }

    private fun movePage() {
        binding.ivMypageAlarm.setOnClickListener {
            val intent = Intent(this, NotificationSettingActivity::class.java)
            startActivity(intent)
        }
    }
}