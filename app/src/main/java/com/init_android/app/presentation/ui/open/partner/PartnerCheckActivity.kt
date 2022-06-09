package com.init_android.app.presentation.ui.open.partner

import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.viewModels
import com.google.android.material.tabs.TabLayoutMediator
import com.init_android.R
import com.init_android.app.data.model.SelectableData
import com.init_android.app.data.request.RequestFinishProject
import com.init_android.app.data.request.project.RequestApplyProject
import com.init_android.app.presentation.ui.home.viewmodel.HomeViewModel
import com.init_android.app.presentation.ui.home.viewmodel.mainViewModel
import com.init_android.app.presentation.ui.open.partner.adapter.PartnerTabAdapter
import com.init_android.app.presentation.ui.open.viewmodel.ProjectViewModel
import com.init_android.app.util.CustomBottomSheetDialog
import com.init_android.app.util.CustomDialog
import com.init_android.databinding.ActivityPartnerCheckBinding
import com.playtogether_android.app.presentation.base.BaseActivity

class PartnerCheckActivity :
    BaseActivity<ActivityPartnerCheckBinding>(R.layout.activity_partner_check) {

    val partBottomSheetDialog = CustomBottomSheetDialog("지원할 파트")
    private lateinit var partnerTabAdapter: PartnerTabAdapter
    private val mainViewModel: mainViewModel by viewModels()
    private val projectViewModel: ProjectViewModel by viewModels()
    private val homeViewModel: HomeViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        initAdapter()
        initTab()
        clickListener()
        backBtnClickListener()

    }

    //뒤로가기 버튼 클릭 리스너
    private fun backBtnClickListener() {
        binding.ivOpenProjectBack.setOnClickListener {
            finish()
        }
    }

    private fun initAdapter() {
        val fragmentList = listOf(
            PlanFragment(),
            DesignFragment(),
            WebFragment(),
            AosFragment(),
            IosFragment(),
            GameFragment(),
            ServerFragment()
        )
        partnerTabAdapter = PartnerTabAdapter(this)
        partnerTabAdapter.fragments.addAll(fragmentList)

        binding.vpPartnerTab.adapter = partnerTabAdapter
    }

    private fun initTab() {
        val tabLabel = listOf("기획", "디자인", "웹", "AOS", "IOS", "게임", "서버")
        TabLayoutMediator(binding.tlPartnerTab, binding.vpPartnerTab) { tab, position ->
            tab.text = tabLabel[position]
        }.attach()
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
        binding.btnPartnerJoin.setOnClickListener {
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

    /*
    binding.btnPartnerJoin.setOnClickListener {
        Log.d("Test", "클릭?")
        partBottomSheetDialog.show(
            supportFragmentManager,
            partBottomSheetDialog.tag
        )
    }
    val requestFinishProject = RequestFinishProject(mNum = 1)
    homeViewModel.postFinishProject(requestFinishProject)
    Log.d("Test", "서버통신")

    homeViewModel.approveItem.observe(this) {
        Log.d("Test", "" + it.pTitle)
        partBottomSheetDialog.setDataList({SelectableData(it.pNum, it.pTitle, false)})
//            val approveData = mutableListOf(
//                SelectableData(it.pNum, it.pTitle, false)
//            )
//            partBottomSheetDialog.setDataList(approveData)

     */




    private fun initAlert() {
        val title = "해당 프로젝트에 지원하시겠습니까?"
        val dialog = CustomDialog(this, title)
        dialog.showChoiceDialog(R.layout.dialog_yes_no)

        dialog.setOnClickedListener(object : CustomDialog.ButtonClickListener {
            val requestApply = RequestApplyProject(
                mNum = 1,
                pNum = 1,
                rPosition = mainViewModel.partNum.value ?: 0
            )

            override fun onClicked(num: Int) {
                if (num == 1) {
                    projectViewModel.postApplyProject(requestApply)

                    Toast.makeText(
                        this@PartnerCheckActivity,
                        "신청이 완료되었습니다.",
                        Toast.LENGTH_SHORT
                    )
                        .show()
                } else {

                    Toast.makeText(
                        this@PartnerCheckActivity,
                        "신청이 취소되었습니다.",
                        Toast.LENGTH_SHORT
                    )
                        .show()
                }
            }
        })
    }
}
