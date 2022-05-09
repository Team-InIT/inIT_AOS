package com.init_android.app.presentation.ui.home.signup

import android.content.Context
import android.os.Bundle
import android.util.AttributeSet
import android.view.View
import com.init_android.R
import com.init_android.databinding.ActivitySignUpBinding
import com.playtogether_android.app.presentation.base.BaseActivity

class SignUpActivity : BaseActivity<ActivitySignUpBinding>(R.layout.activity_sign_up) {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initTransFragmentEvent()
    }

    // Fragment 초기화
    private fun initTransFragmentEvent() {
        val fragment_depth1 = PersonalInfoFragment()

        supportFragmentManager.beginTransaction().add(R.id.fragment_sign_up, BaseInfoTwoFragment())
            .commit()
    }
}