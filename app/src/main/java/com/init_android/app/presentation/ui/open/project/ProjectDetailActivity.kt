package com.init_android.app.presentation.ui.open.project

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import com.init_android.R
import com.init_android.app.data.request.project.RequestProjectDetail
import com.init_android.app.presentation.ui.open.partner.PartnerCheckActivity
import com.init_android.app.presentation.ui.open.viewmodel.ProjectViewModel
import com.init_android.databinding.ActivityProjectDetailBinding
import com.playtogether_android.app.presentation.base.BaseActivity

class ProjectDetailActivity : BaseActivity<ActivityProjectDetailBinding>(R.layout.activity_project_detail) {

    private val projectViewModel : ProjectViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        backBtnListener()
        checkMemBtnClickListener()
        heartClickListener()
        initNetwork()

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

    private fun initNetwork() {
        val requestProjectDetail = RequestProjectDetail(
            pNum = 1
        )

        projectViewModel.postProjectDetail(requestProjectDetail)
        projectViewModel.detailProject.observe(this) {
            binding.project = it
        }
    }


}