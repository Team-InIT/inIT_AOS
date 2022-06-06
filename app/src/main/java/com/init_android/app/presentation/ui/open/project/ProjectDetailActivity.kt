package com.init_android.app.presentation.ui.open.project

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.init_android.R
import com.init_android.app.presentation.ui.open.partner.PartnerCheckActivity
import com.init_android.databinding.ActivityProjectDetailBinding
import com.playtogether_android.app.presentation.base.BaseActivity

class ProjectDetailActivity : BaseActivity<ActivityProjectDetailBinding>(R.layout.activity_project_detail) {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        backBtnListener()
        checkMemBtnClickListener()
        heartClickListener()

    }

    //뒤로가기 클릭 리스너
    private fun backBtnListener() {
        binding.ivProjectDetailBack.setOnClickListener {
            finish()
        }
    }

    //모집인원 확인 버튼 클릭 리스너
    private fun checkMemBtnClickListener() {
        binding.btnDetailCheckMember.setOnClickListener {
            startActivity(Intent(this, PartnerCheckActivity::class.java))
        }
    }

    //하트 클릭 리스너
    private fun heartClickListener() {
        binding.ivProjectDetailHeart.setOnClickListener {
            binding.ivProjectDetailHeart.isSelected = !binding.ivProjectDetailHeart.isSelected
        }
    }


}