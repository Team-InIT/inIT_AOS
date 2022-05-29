package com.init_android.app.presentation.ui.mypage

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.init_android.R
import com.init_android.databinding.ActivityMyPageModifyProfileBinding
import com.playtogether_android.app.presentation.base.BaseActivity

class MyPageModifyProfileActivity : BaseActivity<ActivityMyPageModifyProfileBinding>(R.layout.activity_my_page_modify_profile) {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        backBtnClick()
        initSetting()

        with(binding) {
            makeRadioButtonLevel(btnTop,btnMiddle, btnBottom)
        }
    }

    private fun backBtnClick() {
        binding.ivMyPageEditProfileBack.setOnClickListener {
            finish()
        }
    }

    private fun initSetting() {
        val name = intent.getStringExtra("name") ?: ""
        val position = intent.getStringExtra("position") ?: ""
        val level = intent.getStringExtra("level") ?: ""

        //추후에 introduction 서버통신으로 받아오기
        val introduction = intent.getStringExtra("introduction") ?: ""

        binding.etMyPageEditProfileName.setText(name)
        binding.etMyPageIntroduction.setText(introduction)

        if(level == "상") {
            binding.btnTop.isSelected = true
        } else if(level == "중") {
            binding.btnMiddle.isSelected = true
        } else if(level == "하") {
            binding.btnBottom.isSelected = true
        }
    }

    //라디오 버튼 속성 추가
    private fun makeRadioButtonLevel(view1 : View, view2 : View, view3 : View) {
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
}