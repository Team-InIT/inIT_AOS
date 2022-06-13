package com.init_android.app.presentation.ui.open.project

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.LinearLayout
import android.widget.Toast
import androidx.activity.viewModels
import com.google.android.material.chip.Chip
import com.init_android.R
import com.init_android.app.data.model.SelectableData
import com.init_android.app.data.request.project.RequestApplyProject
import com.init_android.app.data.request.project.RequestProjectDetail
import com.init_android.app.presentation.ui.home.viewmodel.mainViewModel
import com.init_android.app.presentation.ui.mypage.MyPageSettingActivity
import com.init_android.app.presentation.ui.open.partner.PartnerCheckActivity
import com.init_android.app.presentation.ui.open.partner.adapter.PartnerTabAdapter
import com.init_android.app.presentation.ui.open.todo.ToDoMainActivity
import com.init_android.app.presentation.ui.open.viewmodel.ProjectViewModel
import com.init_android.app.util.CustomBottomSheetDialog
import com.init_android.app.util.CustomDialog
import com.init_android.databinding.ActivityProjectDetailBinding
import com.playtogether_android.app.presentation.base.BaseActivity


class ProjectDetailActivity :
    BaseActivity<ActivityProjectDetailBinding>(R.layout.activity_project_detail) {

    private val projectViewModel: ProjectViewModel by viewModels()
    val partBottomSheetDialog = CustomBottomSheetDialog("지원할 파트")
    private lateinit var partnerTabAdapter: PartnerTabAdapter
    private val mainViewModel: mainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        backBtnListener()
        //checkMemBtnClickListener()
        heartClickListener()
        initNetwork()

        initToDoBtnListener()
        initMemListener()

    }

    //뒤로가기 클릭 리스너
    private fun backBtnListener() {
        binding.ivProjectDetailBack.setOnClickListener {
            finish()
        }
    }

    //지원하기 클릭 리스너
    private fun checkMemBtnClickListener() {
        binding.btnDetailApply.setOnClickListener {
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
        val pNum = intent.getIntExtra("pNum", 1)
        //val mNum = intent.getIntExtra("mNum", 1)
        val mNum = 1

        Log.d("pNum", " " + pNum)
        Log.d("mNum", " " + mNum)

        val requestProjectDetail = RequestProjectDetail(
            pNum = pNum, mNum = mNum
        )

        projectViewModel.postProjectDetail(requestProjectDetail)
        projectViewModel.detailProject.observe(this) {
            binding.project = it

            //click listenr 2번 변경경
           binding.btnDetailApply.setOnClickListener {
                projectViewModel.detailProject.observe(this) {
                    binding.project = it

                    if (it.code == 201) {
//                        Toast.makeText(
//                            this@ProjectDetailActivity,
//                            "이미 지원한 프로젝트입니다.",
//                            Toast.LENGTH_SHORT
//                        )
//                            .show()
                        clickListener()

                    } else if(it.writerInfo?.mNum == mNum) {
                        Toast.makeText(this@ProjectDetailActivity, "내 프로젝트 입니다", Toast.LENGTH_SHORT).show()
                        Log.d("Test", "내 프로젝트")

                    }

                    else {
                        clickListener()
                    }
                }
            }



            if (it.projectInfo?.pGender == 0) {
                binding.ivDetailMan.isSelected = true
                binding.tvDetailMan.isSelected = true
            } else if (it.projectInfo?.pGender == 1) {
                binding.ivDetailWoman.isSelected = true
                binding.tvDetailWoman.isSelected = true
            }


            if (it.projectInfo?.pAcademic == 0) {
                binding.ivDetailInschool.isSelected = true
                binding.tvDetailInschool.isSelected = true
            } else if (it.projectInfo?.pAcademic == 1) {
                binding.ivDetailRest.isSelected = true
                binding.tvDetailRest.isSelected = true
            } else if (it.projectInfo?.pAcademic == 2) {
                binding.ivDetailGraduate.isSelected = true
                binding.tvDetailGraduate.isSelected = true
            }

            if (it.projectInfo?.pPlanf == 0) {
                binding.ivDetailPlanTop.isSelected = true
                binding.tvDetailPlanTop.isSelected = true
            } else if (it.projectInfo?.pPlanf == 1) {
                binding.ivDetailPlanMiddle.isSelected = true
                binding.tvDetailPlanMiddle.isSelected = true
            } else if (it.projectInfo?.pPlanf == 2) {
                binding.ivDetailPlanLow.isSelected = true
                binding.tvDetailPlanLow.isSelected = true
            }

            if (it.projectInfo?.pDesignf == 0) {
                binding.ivDetailDesignTop.isSelected = true
                binding.tvDetailDesignTop.isSelected = true
            } else if (it.projectInfo?.pDesignf == 1) {
                binding.ivDetailDesignMiddle.isSelected = true
                binding.tvDetailDesignMiddle.isSelected = true
            } else if (it.projectInfo?.pDesignf == 2) {
                binding.ivDetailDesignLow.isSelected = true
                binding.tvDetailDesignLow.isSelected = true
            }


            if (it.projectInfo?.pWebf == 0) {
                binding.ivDetailWebTop.isSelected = true
                binding.tvDetailWebTop.isSelected = true
            } else if (it.projectInfo?.pWebf == 1) {
                binding.ivDetailWebMiddle.isSelected = true
                binding.tvDetailWebMiddle.isSelected = true
            } else if (it.projectInfo?.pWebf == 2) {
                binding.ivDetailWebLow.isSelected = true
                binding.tvDetailWebLow.isSelected = true
            }

            if (it.projectInfo?.pAosf == 0) {
                binding.ivDetailAosTop.isSelected = true
                binding.tvDetailAosTop.isSelected = true
            } else if (it.projectInfo?.pAosf == 1) {
                binding.ivDetailAosMiddle.isSelected = true
                binding.tvDetailAosMiddle.isSelected = true
            } else if (it.projectInfo?.pAosf == 2) {
                binding.ivDetailAosLow.isSelected = true
                binding.tvDetailAosLow.isSelected = true
            }

            if (it.projectInfo?.pIosf == 0) {
                binding.ivDetailIosTop.isSelected = true
                binding.tvDetailIosTop.isSelected = true
            } else if (it.projectInfo?.pIosf == 1) {
                binding.ivDetailIosMiddle.isSelected = true
                binding.tvDetailIosMiddle.isSelected = true
            } else if (it.projectInfo?.pIosf == 2) {
                binding.ivDetailIosLow.isSelected = true
                binding.tvDetailIosLow.isSelected = true
            }

            if (it.projectInfo?.pGamef == 0) {
                binding.ivDetailGameTop.isSelected = true
                binding.tvDetailGameTop.isSelected = true
            } else if (it.projectInfo?.pGamef == 1) {
                binding.ivDetailGameMiddle.isSelected = true
                binding.tvDetailGameMiddle.isSelected = true
            } else if (it.projectInfo?.pGamef == 2) {
                binding.ivDetailGameLow.isSelected = true
                binding.tvDetailGameLow.isSelected = true
            }

            if (it.projectInfo?.pServerf == 0) {
                binding.ivDetailServerTop.isSelected = true
                binding.tvDetailServerTop.isSelected = true
            } else if (it.projectInfo?.pServerf == 1) {
                binding.ivDetailServerMiddle.isSelected = true
                binding.tvDetailServerMiddle.isSelected = true
            } else if (it.projectInfo?.pServerf == 2) {
                binding.ivDetailServerLow.isSelected = true
                binding.tvDetailServerLow.isSelected = true
            }


            val myList = it.projectInfo?.pStack
            Log.d("test", "" + myList)
            //val myList = arrayOf("Chip1", "Chip2", "Chip3", "Chip4")

            if (myList?.size != null) {
                for (i in 0 until myList?.size!!) {
                    // Here I am creating Chip view dynamically using current Context
                    val chip = Chip(binding.clDetailStack.getContext())
                    val layoutParams = LinearLayout.LayoutParams(
                        LinearLayout.LayoutParams.WRAP_CONTENT,
                        LinearLayout.LayoutParams.WRAP_CONTENT
                    )
                    layoutParams.setMargins(5, 0, 5, 0)
                    chip.layoutParams = layoutParams
                    chip.setText(myList.get(i))
                    chip.closeIcon!!.isVisible
                    //chip.isCloseIconEnabled = true
                    chip.chipBackgroundColor = resources.getColorStateList(R.color.main_default)
                    chip.setTextColor(resources.getColorStateList(R.color.white))
                    //chip.closeIconTint = resources.getColorStateList(R.color.white)
                    chip.isClickable = true
                    chip.isCheckable = false
                    binding.clDetailStack.addView(chip)
                }
            }
        }
    }

    //바텀시트
    private fun clickListener() {
        //더미 데이터 넣는 부분
        var partData = mutableListOf(
            SelectableData(1, "기획", false),
            SelectableData(2, "디자인", false),
            SelectableData(3, "웹", false),
            SelectableData(4, "안드로이드", false),
            SelectableData(5, "IOS", false),
            SelectableData(6, "게임", false),
            SelectableData(7, "서버", false),
        )
        partBottomSheetDialog.setDataList(partData)

        //버튼 클릭해서 바텀시트 생성되는 부분
        binding.btnDetailApply.setOnClickListener {
            partBottomSheetDialog.show(
                supportFragmentManager,
                partBottomSheetDialog.tag
            )

            //클릭 완료되었을때 일어나는 리스너
            partBottomSheetDialog.setCompleteListener {
                val part = partBottomSheetDialog.getSelectedData()
                //뷰모델에 넣어주는 코드
                mainViewModel.part.value = part?.name
                mainViewModel.partNum.value = part?.id
                partBottomSheetDialog.binding.btnBottomsheetCancel
                //알럿
                initAlert()
                partBottomSheetDialog.binding.btnBottomsheetComplete.setOnClickListener {
                    Log.d("클릭", "완료")
                    initAlert()
                }
            }
        }
    }


    private fun initAlert() {
        val title = "해당 프로젝트에 지원하시겠습니까?"
        val dialog = CustomDialog(this, title)
        dialog.showChoiceDialog(R.layout.dialog_yes_no)

        val pNum = intent.getIntExtra("pNum", 1)
        val mNum = 1
        //val mNum = intent.getIntExtra("mNum", 1)

        Log.d("ApplypNum", " " + pNum)
        Log.d("ApplymNum", " " + mNum)

        dialog.setOnClickedListener(object : CustomDialog.ButtonClickListener {
            val requestApply = RequestApplyProject(
                pNum = pNum, mNum = mNum,
                rPosition = mainViewModel.partNum.value ?: 0
            )

            override fun onClicked(num: Int) {
                if (num == 1) {
                    projectViewModel.postApplyProject(requestApply)

                    Toast.makeText(
                        this@ProjectDetailActivity,
                        "신청이 완료되었습니다.",
                        Toast.LENGTH_SHORT
                    )
                        .show()
                } else {

                    Toast.makeText(
                        this@ProjectDetailActivity,
                        "신청이 취소되었습니다.",
                        Toast.LENGTH_SHORT
                    )
                        .show()
                }
            }
        })
    }

    // todo 버튼 클릭 리스너
    private fun initToDoBtnListener() {
        binding.tvDetailTodo.setOnClickListener {
            val intentToDo = Intent(this, ToDoMainActivity::class.java)
            startActivity(intentToDo)
        }
    }

    //팀원 정보 확인 클릭 리스너
    private fun initMemListener() {
        binding.tvDetailMember.setOnClickListener {
            val intentMem = Intent(this, PartnerCheckActivity::class.java)
            startActivity(intentMem)
        }
    }
}