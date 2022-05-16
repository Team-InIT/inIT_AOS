package com.init_android.app.presentation.oepn.project

import android.content.Intent
import android.os.Bundle
import com.init_android.R
import com.init_android.databinding.ActivityOpenProjectBinding
import com.playtogether_android.app.presentation.base.BaseActivity

class OpenProjectActivity : BaseActivity<ActivityOpenProjectBinding>(R.layout.activity_open_project) {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        initNextBtn()

    }

    private fun initNextBtn() {
        binding.tvFinish.setOnClickListener {
            startActivity(Intent(this, OpenProjectSecondActivity::class.java))
            finish()
        }
    }


}