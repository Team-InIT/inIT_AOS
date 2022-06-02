package com.init_android.app.presentation.ui.mypage.message

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.init_android.R
import com.init_android.databinding.ActivityWriteMessageBinding
import com.playtogether_android.app.presentation.base.BaseActivity

class WriteMessageActivity : BaseActivity<ActivityWriteMessageBinding>(R.layout.activity_write_message) {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        backBtnListener()
    }

    private fun backBtnListener() {
        binding.ivMessageMovePage.setOnClickListener {
            finish()
        }
    }
}