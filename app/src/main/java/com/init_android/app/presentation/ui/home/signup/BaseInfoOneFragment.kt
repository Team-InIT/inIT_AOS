package com.init_android.app.presentation.ui.home.signup

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.widget.EditText
import android.widget.ImageButton
import android.widget.Toast
import androidx.appcompat.widget.AppCompatButton
import androidx.core.widget.doOnTextChanged
import com.init_android.R
import com.init_android.databinding.FragmentBaseInfoOneBinding
import com.playtogether_android.app.presentation.base.BaseFragment
import java.util.*
import kotlin.collections.ArrayList
import kotlin.math.sign

// 기본정보(1)
class BaseInfoOneFragment :
    BaseFragment<FragmentBaseInfoOneBinding>(R.layout.fragment_base_info_one) {

    // check 변수 확인
    // checkArray => (이름, 이메일, 소속, 학적상태, 성별)
    var checkArray = arrayOf(false, false, false, false, false)

    // 넘겨줄 회원정보 배열 선언(id,pw, // 이름,이메일,소속,학적상태,성별)
    private var signUpArray = arrayListOf<Any>()

    // 선택된 학적상태, 성별 버튼을 찾기 위한 변수
    var academicSelected = 0
    var sexSelected = 0

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // 프로그레스바 진행 정도
        (activity as SignUpActivity).binding.pbSignup.progress = 60

        getBundle() // 이전 fragment 에서 정보 받아오기

        initTransFragmentEvent()
        observeEditTxtField()
        textClearEvent()
        selectBtnInit()

        goBackBtn() // 뒤로가기

    }

    // bundle 값 받아오기
    private fun getBundle(){
        val bundle = arguments
        val arrayList = bundle?.getSerializable("signUpArray")
        signUpArray.add((arrayList as ArrayList<*>)[0])
        signUpArray.add(arrayList[1])
    }

    // Fragment 초기화
    private fun initTransFragmentEvent() {
        binding.btnNext.setOnClickListener {
            val baseInfoTwoFragment = BaseInfoTwoFragment()

            // fragment 에 회원가입 정보 넘겨주기
            val name = binding.etvName.text.toString() // 이름
            val email = binding.etvEmail.text.toString() // 이메일
            val dept =  binding.etvBelong.text.toString()// 소속
            val academic = academicSelected// 학적상태
            val sex = sexSelected // 성별

            signUpArray.add(name)
            signUpArray.add(email)
            signUpArray.add(dept)
            signUpArray.add(academic)
            signUpArray.add(sex)

            val bundle = Bundle()
            bundle.putSerializable("signUpArray",signUpArray)
            baseInfoTwoFragment.arguments = bundle

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

    // 학적상태 선택 이벤트
    private fun selectAcademic(btn:AppCompatButton){
        btn.isSelected = !btn.isSelected

        // 나머지 버튼 비활성화
        binding.apply {
            if (btn.isSelected) {
                when (btn) {
                    btnInSchool -> {
                        btnRestSchool.isSelected = false
                        btnGraduate.isSelected = false
                    }
                    btnRestSchool -> {
                        btnInSchool.isSelected = false
                        btnGraduate.isSelected = false
                    }
                    btnGraduate -> {
                        btnInSchool.isSelected = false
                        btnRestSchool.isSelected = false
                    }
                }

                checkArray[3] = true

                when(btn){
                    btnInSchool -> academicSelected = 0
                    btnRestSchool -> academicSelected = 1
                    btnGraduate -> academicSelected = 2
                }

            }else checkArray[3] = false
        }

        checkNextBtnState() // 버튼 활성화 체크

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

                checkArray[4] = true

                when(btn){
                    btnMan -> sexSelected = 0
                    btnWoman -> sexSelected = 1
                    btnEtc -> sexSelected = 2
                }

            }else checkArray[4] = false
        }

        checkNextBtnState() // 버튼 활성화 체크
    }

    // 버튼 이벤트 초기화
    private fun selectBtnInit() {
        binding.apply {
            btnMan.setOnClickListener { selectSexBtn(btnMan) }
            btnWoman.setOnClickListener { selectSexBtn(btnWoman) }
            btnEtc.setOnClickListener { selectSexBtn(btnEtc) }

            btnInSchool.setOnClickListener { selectAcademic(btnInSchool) }
            btnRestSchool.setOnClickListener { selectAcademic(btnRestSchool) }
            btnGraduate.setOnClickListener { selectAcademic(btnGraduate) }
        }
    }

    // 다음 버튼 활성화 여부 체크
    private fun checkNextBtnState() {
        binding.btnNext.isEnabled = checkArray.contentEquals(arrayOf(true, true, true ,true, true))
    }

    // 뒤로가기 버튼 활성화
    private fun goBackBtn(){
        binding.ibtnBack.setOnClickListener {
            (activity as SignUpActivity).finish()
        }
    }
}