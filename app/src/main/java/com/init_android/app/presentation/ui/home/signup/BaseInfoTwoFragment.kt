package com.init_android.app.presentation.ui.home.signup

import android.os.Bundle
import android.view.View
import androidx.appcompat.widget.AppCompatButton
import com.init_android.R
import com.init_android.app.presentation.ui.home.adapter.PositionAdapter
import com.init_android.app.presentation.ui.home.adapter.PositionData
import com.init_android.databinding.FragmentBaseInfoTwoBinding
import com.playtogether_android.app.presentation.base.BaseFragment

// 기본정보(2)
class BaseInfoTwoFragment : BaseFragment<FragmentBaseInfoTwoBinding>(R.layout.fragment_base_info_two) {

    private lateinit var positionAdapter:PositionAdapter
    var checkState = false

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // 프로그레스바 진행 정도
        (activity as SignUpActivity).binding.pbSignup.progress = 100

        selectBtnInit()
        initPositionRV()
    }

    // RV 초기화
    private fun initPositionRV(){

        positionAdapter = PositionAdapter()
        binding.rvPosition.adapter = positionAdapter
        positionAdapter.positionList.addAll(
            listOf(
                PositionData("DESIGN"),
                PositionData("PLAN"),
                PositionData("IOS"),
                PositionData("ANDROID"),
                PositionData("WEB"),
                PositionData("GAME"),
                PositionData("SERVER")
            )
        )
        positionAdapter.notifyDataSetChanged()
    }

    // 숙련도 선택 이벤트
    private fun selectSkillBtn(btn: AppCompatButton) {

        btn.isSelected = !btn.isSelected

        // 나머지 버튼 비활성화
        binding.apply {
            if (btn.isSelected) {
                when (btn) {
                    btnTop -> {
                        btnMiddle.isSelected = false
                        btnBottom.isSelected = false
                    }
                    btnMiddle -> {
                        btnTop.isSelected = false
                        btnBottom.isSelected = false
                    }
                    btnBottom -> {
                        btnTop.isSelected = false
                        btnMiddle.isSelected = false
                    }
                }
            }
        }
                checkNextBtnState() // 버튼 활성화 체크

    }

    // 숙련도 버튼 이벤트 초기화
    private fun selectBtnInit() {
        binding.apply {
            btnTop.setOnClickListener { selectSkillBtn(btnTop) }
            btnMiddle.setOnClickListener { selectSkillBtn(btnMiddle) }
            btnBottom.setOnClickListener { selectSkillBtn(btnBottom) }
        }
    }

    // 다음 버튼 활성화 여부 체크
    private fun checkNextBtnState() {
        binding.btnNext.isEnabled = checkState != false
    }
}