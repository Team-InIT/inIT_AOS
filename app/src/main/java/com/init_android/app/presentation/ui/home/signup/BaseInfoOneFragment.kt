package com.init_android.app.presentation.ui.home.signup

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.widget.EditText
import android.widget.ImageButton
import androidx.appcompat.widget.AppCompatButton
import androidx.core.widget.doOnTextChanged
import com.init_android.R
import com.init_android.databinding.FragmentBaseInfoOneBinding
import com.playtogether_android.app.presentation.base.BaseFragment

// 기본정보(1)
class BaseInfoOneFragment :
    BaseFragment<FragmentBaseInfoOneBinding>(R.layout.fragment_base_info_one) {

    // check 변수 확인
    // checkArray => (이름, 이메일, 소속, 성별)
    var checkArray = arrayOf(false, false, false, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // 프로그레스바 진행 정도
        (activity as SignUpActivity).binding.pbSignup.progress = 60

        initTransFragmentEvent()
        observeEditTxtField()
        textClearEvent()
        selectBtnInit()

    }

    // Fragment 초기화
    private fun initTransFragmentEvent() {
        binding.btnNext.setOnClickListener {
            val baseInfoTwoFragment = BaseInfoTwoFragment()

            parentFragmentManager.beginTransaction()
                .replace(R.id.fragment_sign_up, baseInfoTwoFragment)
                .commit()
        }
    }


    // 텍스트 감지 이벤트
    private fun observeEditTxtField() {
        binding.apply {
            etvName.doOnTextChanged { text, start, before, count ->
                checkCloseBtnState(etvName, ibtnNameClose) // 'x' 버튼 활성화 체크
                checkArray[0] = etvName.text.isNotEmpty() // 텍스트 필드 비어있는지 체크 -> 비어있으면 '다음' 버튼 비활성화
                checkNextBtnState() // 다음 버튼 활성화 여부 체크
            }

            etvEmail.doOnTextChanged { text, start, before, count ->
                checkCloseBtnState(etvEmail, ibtnEmailClose) // 'x' 버튼 활성화 체크
                checkArray[1] = etvEmail.text.isNotEmpty()
                checkNextBtnState()
            }

            etvBelong.doOnTextChanged { text, start, before, count ->
                checkCloseBtnState(etvBelong, ibtnBelongClose) // 'x' 버튼 활성화 체크
                checkArray[2] = etvBelong.text.isNotEmpty()
                checkNextBtnState()
            }
        }
    }

    // x 버튼 활성화 여부
    private fun checkCloseBtnState(etv: EditText, closeBtn: ImageButton) {
        if (etv.text.isNotEmpty()) closeBtn.visibility = View.VISIBLE
        else closeBtn.visibility = View.GONE
    }


    // x버튼 클릭이벤트
    private fun textClearEvent() {
        binding.apply {
            ibtnNameClose.setOnClickListener { binding.etvName.text.clear() }
            ibtnEmailClose.setOnClickListener { binding.etvEmail.text.clear() }
            ibtnBelongClose.setOnClickListener { binding.etvBelong.text.clear() }
        }
    }

    // 성별 선택 이벤트
    private fun selectSexBtn(btn: AppCompatButton) {

        btn.isSelected = !btn.isSelected

        // 나머지 버튼 비활성화
        binding.apply {
            if (btn.isSelected) {
                when (btn) {
                    btnMan -> {
                        btnWoman.isSelected = false
                        btnEtc.isSelected = false
                    }
                    btnWoman -> {
                        btnMan.isSelected = false
                        btnEtc.isSelected = false
                    }
                    btnEtc -> {
                        btnMan.isSelected = false
                        btnWoman.isSelected = false
                    }
                }

                checkArray[3] = true
            }
        }

        checkNextBtnState() // 버튼 활성화 체크
    }

    // 성별 버튼 이벤트 초기화
    private fun selectBtnInit() {
        binding.apply {
            btnMan.setOnClickListener { selectSexBtn(btnMan) }
            btnWoman.setOnClickListener { selectSexBtn(btnWoman) }
            btnEtc.setOnClickListener { selectSexBtn(btnEtc) }
        }
    }

    // 다음 버튼 활성화 여부 체크
    private fun checkNextBtnState() {
        binding.btnNext.isEnabled = checkArray.contentEquals(arrayOf(true, true, true, true))
    }
}