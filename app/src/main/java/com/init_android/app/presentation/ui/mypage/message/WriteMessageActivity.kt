package com.init_android.app.presentation.ui.mypage.message

import android.graphics.Color
import android.os.Bundle
import androidx.activity.viewModels
import com.init_android.R
import com.init_android.app.data.model.SelectableData
import com.init_android.app.util.CustomBottomSheetDialog
import com.init_android.app.presentation.ui.home.viewmodel.mainViewModel
import com.init_android.databinding.ActivityWriteMessageBinding
import com.playtogether_android.app.presentation.base.BaseActivity

class WriteMessageActivity :
    BaseActivity<ActivityWriteMessageBinding>(R.layout.activity_write_message) {


    private val mainViewModel: mainViewModel by viewModels()

    val partBottomSheetDialog = CustomBottomSheetDialog("지원할 파트")
    val projectBottomSheetDialog = CustomBottomSheetDialog("프로젝트")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        backBtnListener()
        bottomSheetListener()
    }

    private fun backBtnListener() {
        binding.ivMessageMovePage.setOnClickListener {
            finish()
        }
    }

    private fun bottomSheetListener() {
//        binding.tvMessageProject.setOnClickListener {
//            projectBottomSheetDialog.show(
//                supportFragmentManager,
//                projectBottomSheetDialog.tag
//            )
//        }
//        signUpBasicInfoViewModel.getFirstDepartment(1, "firstMajor")
//        signUpBasicInfoViewModel.firstDepartment.observe(this) {
//
//            firstDepartmentBottomSheetDialog.setDataList(it.data.filter { it.isFirstMajor }
//                .map { SelectableData(it.majorId, it.majorName, false) }.toMutableList())
//        }
//
//        //데이터 넣기
//        firstDepartmentBottomSheetDialog.setCompleteListener {
//            val firstMajor = firstDepartmentBottomSheetDialog.getSelectedData()
//            signViewModel.firstMajor.value = firstMajor?.name
//            signViewModel.firstMajor
//                .observe(this) {
//                    binding.textSignupMajorinfoMajor.setText(it)
//                    binding.textSignupMajorinfoMajor.text = it
//
//                    binding.textSignupMajorinfoMajor.setTextColor(Color.parseColor("#001D19"))
//                    binding.textSignupMajorinfoMajorMint.text = "변경"
//                }
//            signUpBasicInfoViewModel.firstDepartmentClick.value = true
//        }



        //파트 바텀시트
//        var partData = mutableListOf(
//            SelectableData(1, "기획", false),
//            SelectableData(2, "디자인", false),
//            SelectableData(3, "웹", false),
//            SelectableData(4, "안드로이드", false),
//            SelectableData(5, "IOS", false),
//            SelectableData(6, "게임", false),
//            SelectableData(7, "서버", false),
//        )
//        partBottomSheetDialog.setDataList(partData)
//
//        binding.tvMessageProject.setOnClickListener {
//            partBottomSheetDialog.show(
//                supportFragmentManager,
//                partBottomSheetDialog.tag
//            )
//
//            partBottomSheetDialog.setCompleteListener {
//                val firstMajorPeriod = partBottomSheetDialog.getSelectedData()
//                mainViewModel.part.value = firstMajorPeriod?.name
//                partBottomSheetDialog.binding.btnBottomsheetCancel
//            }
//
//            mainViewModel.part.observe(this) {
//                binding.tvMessageProject.setText(it)
//                binding.tvMessageProject.setTextColor(Color.parseColor("#FF000000"))
//
//            }
//        }
    }
}