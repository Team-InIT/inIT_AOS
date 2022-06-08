package com.init_android.app.presentation.ui.open.project

import android.content.Intent
import android.os.Bundle
import android.widget.LinearLayout
import androidx.activity.viewModels
import com.google.android.material.chip.Chip
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

            if(it.projectInfo.pGender == 0) {
                binding.ivDetailMan.isSelected = true
                binding.tvDetailMan.isSelected = true
            } else if(it.projectInfo.pGender == 1) {
                binding.ivDetailWoman.isSelected = true
                binding.tvDetailWoman.isSelected = true
            }


            if(it.projectInfo.pAcademic == 0) {
                binding.ivDetailInschool.isSelected = true
                binding.tvDetailInschool.isSelected = true
            } else if(it.projectInfo.pAcademic == 1) {
                binding.ivDetailRest.isSelected = true
                binding.tvDetailRest.isSelected = true
            } else if(it.projectInfo.pAcademic == 2) {
                binding.ivDetailGraduate.isSelected = true
                binding.tvDetailGraduate.isSelected = true
            }

            if(it.projectInfo.pPlanf == 0) {
                binding.ivDetailPlanTop.isSelected = true
                binding.tvDetailPlanTop.isSelected = true
            } else if(it.projectInfo.pPlanf == 1) {
                binding.ivDetailPlanMiddle.isSelected = true
                binding.tvDetailPlanMiddle.isSelected = true
            } else if(it.projectInfo.pPlanf == 2) {
                binding.ivDetailPlanLow.isSelected = true
                binding.tvDetailPlanLow.isSelected = true
            }

            if(it.projectInfo.pDesignf == 0) {
                binding.ivDetailDesignTop.isSelected = true
                binding.tvDetailDesignTop.isSelected = true
            } else if(it.projectInfo.pDesignf == 1) {
                binding.ivDetailDesignMiddle.isSelected = true
                binding.tvDetailDesignMiddle.isSelected = true
            } else if(it.projectInfo.pDesignf == 2) {
                binding.ivDetailDesignLow.isSelected = true
                binding.tvDetailDesignLow.isSelected = true
            }


            if(it.projectInfo.pWebf == 0) {
                binding.ivDetailWebTop.isSelected = true
                binding.tvDetailWebTop.isSelected = true
            } else if(it.projectInfo.pWebf == 1) {
                binding.ivDetailWebMiddle.isSelected = true
                binding.tvDetailWebMiddle.isSelected = true
            } else if(it.projectInfo.pWebf == 2) {
                binding.ivDetailWebLow.isSelected = true
                binding.tvDetailWebLow.isSelected = true
            }

            if(it.projectInfo.pAosf == 0) {
                binding.ivDetailAosTop.isSelected = true
                binding.tvDetailAosTop.isSelected = true
            } else if(it.projectInfo.pAosf == 1) {
                binding.ivDetailAosMiddle.isSelected = true
                binding.tvDetailAosMiddle.isSelected = true
            } else if(it.projectInfo.pAosf == 2) {
                binding.ivDetailAosLow.isSelected = true
                binding.tvDetailAosLow.isSelected = true
            }

            if(it.projectInfo.pIosf == 0) {
                binding.ivDetailIosTop.isSelected = true
                binding.tvDetailIosTop.isSelected = true
            } else if(it.projectInfo.pIosf == 1) {
                binding.ivDetailIosMiddle.isSelected = true
                binding.tvDetailIosMiddle.isSelected = true
            } else if(it.projectInfo.pIosf == 2) {
                binding.ivDetailIosLow.isSelected = true
                binding.tvDetailIosLow.isSelected = true
            }

            if(it.projectInfo.pGamef == 0) {
                binding.ivDetailGameTop.isSelected = true
                binding.tvDetailGameTop.isSelected = true
            } else if(it.projectInfo.pGamef == 1) {
                binding.ivDetailGameMiddle.isSelected = true
                binding.tvDetailGameMiddle.isSelected = true
            } else if(it.projectInfo.pGamef == 2) {
                binding.ivDetailGameLow.isSelected = true
                binding.tvDetailGameLow.isSelected = true
            }

            if(it.projectInfo.pServerf == 0) {
                binding.ivDetailServerTop.isSelected = true
                binding.tvDetailServerTop.isSelected = true
            } else if(it.projectInfo.pServerf == 1) {
                binding.ivDetailServerMiddle.isSelected = true
                binding.tvDetailServerMiddle.isSelected = true
            } else if(it.projectInfo.pServerf == 2) {
                binding.ivDetailServerLow.isSelected = true
                binding.tvDetailServerLow.isSelected = true
            }


            val myList = arrayOf("Chip1", "Chip2", "Chip3", "Chip4")
            for (i in 0 until myList.size) {
                // Here I am creating Chip view dynamically using current Context
                val chip = Chip(binding.clDetailStack.getContext())
                val layoutParams = LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.WRAP_CONTENT,
                    LinearLayout.LayoutParams.WRAP_CONTENT
                )
                layoutParams.setMargins(5, 5, 5, 5)
                chip.layoutParams = layoutParams
                chip.setText(myList.get(i))
                chip.isCloseIconEnabled = true
                chip.chipBackgroundColor = resources.getColorStateList(R.color.main_default)
                chip.setTextColor(resources.getColorStateList(R.color.white))
                chip.closeIconTint = resources.getColorStateList(R.color.white)
                chip.isClickable = true
                chip.isCheckable = false
                binding.clDetailStack.addView(chip)
            }


        }
    }


}