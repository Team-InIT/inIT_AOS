package com.init_android.app.presentation.ui.mypage

import android.os.Bundle
import android.util.Log
import android.view.View
import com.init_android.R
import com.init_android.databinding.ActivityMyPageModifyBasicInfoBinding
import com.playtogether_android.app.presentation.base.BaseActivity

class MyPageModifyBasicInfoActivity : BaseActivity<ActivityMyPageModifyBasicInfoBinding>(R.layout.activity_my_page_modify_basic_info) {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        backBtnListener()
        initSetting()

        with(binding) {
            makeRadioButtonBelong(btnInSchool,btnRestSchool, btnGraduate)
            makeRadioButtonGender(btnMan,btnWoman,btnEtc)
        }
    }

    private fun initSetting() {
        val email = intent.getStringExtra("email") ?: ""
        val belong = intent.getStringExtra("belong") ?: ""
        val academic = intent.getStringExtra("academic") ?: ""
        val gender = intent.getStringExtra("gender") ?: ""

        Log.d("test", email)

        binding.etMyPageBasicInfoEmail.setText(email)
        binding.etMyPageBasicInfoBelong.setText(belong)

        if(academic == "재학") {
            binding.btnInSchool.isSelected = true
        } else if (academic == "휴학") {
            binding.btnRestSchool.isSelected = true
        } else if (academic == "졸업") {
            binding.btnGraduate.isSelected = true
        }

        if(gender == "남") {
            binding.btnMan.isSelected = true
        } else if(gender == "여") {
            binding.btnWoman.isSelected = true
        } else if(gender == "기타") {
            binding.btnEtc.isSelected = true
        }

    }

    private fun makeRadioButtonBelong(view1 : View, view2 : View, view3 : View) {
        view1.setOnClickListener {
            if (!view1.isSelected) {
                view1.isSelected = true
                view2.isSelected = false
                view3.isSelected = false
            }
        }
        view2.setOnClickListener {
            if (!view2.isSelected) {
                view1.isSelected = false
                view2.isSelected = true
                view3.isSelected = false
            }
        }
        view3.setOnClickListener {
            if (!view3.isSelected) {
                view1.isSelected = false
                view2.isSelected = false
                view3.isSelected = true
            }
        }
    }

    private fun makeRadioButtonGender(view1 : View, view2 : View, view3 : View) {
        view1.setOnClickListener {
            if (!view1.isSelected) {
                view1.isSelected = true
                view2.isSelected = false
                view3.isSelected = false
            }
        }
        view2.setOnClickListener {
            if (!view2.isSelected) {
                view1.isSelected = false
                view2.isSelected = true
                view3.isSelected = false
            }
        }
        view3.setOnClickListener {
            if (!view3.isSelected) {
                view1.isSelected = false
                view2.isSelected = false
                view3.isSelected = true
            }
        }
    }

    private fun backBtnListener() {
        binding.ivMyPageBasicInfoBack.setOnClickListener {
            finish()
        }
    }
}