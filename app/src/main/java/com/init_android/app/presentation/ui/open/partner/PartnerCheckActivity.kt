package com.init_android.app.presentation.ui.open.partner

import android.content.Intent
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.viewModels
import com.google.android.material.tabs.TabLayoutMediator
import com.init_android.R
import com.init_android.app.data.model.SelectableData
import com.init_android.app.data.request.mypage.RequestQuit
import com.init_android.app.data.request.project.RequestApplyProject
import com.init_android.app.presentation.ui.home.signin.SignInActivity
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
    private val projectViewModel : ProjectViewModel by viewModels()

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

    private fun clickListener() {
        //파트 바텀시트
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

        binding.btnPartnerJoin.setOnClickListener {
            partBottomSheetDialog.show(
                supportFragmentManager,
                partBottomSheetDialog.tag
            )

            partBottomSheetDialog.setCompleteListener {
                val part = partBottomSheetDialog.getSelectedData()
                mainViewModel.part.value = part?.name
                mainViewModel.partNum.value = part?.id
                partBottomSheetDialog.binding.btnBottomsheetCancel
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

        dialog.setOnClickedListener(object : CustomDialog.ButtonClickListener {
            val requestApply = RequestApplyProject(
                mNum = 1,
                pNum = 1,
                rPosition = mainViewModel.partNum.value ?: 0
            )
            override fun onClicked(num: Int) {
                if (num == 1) {
                    projectViewModel.postApplyProject(requestApply)

                    Toast.makeText(this@PartnerCheckActivity, "신청이 완료되었습니다.", Toast.LENGTH_SHORT)
                        .show()
                } else {

                    Toast.makeText(this@PartnerCheckActivity, "신청이 취소되었습니다.", Toast.LENGTH_SHORT)
                        .show()
                }
            }
        })
    }
}
