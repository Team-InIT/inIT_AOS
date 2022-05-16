package com.init_android.app.presentation.ui.home.signup

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.EditText
import android.widget.ImageButton
import android.widget.Toast
import androidx.core.widget.doOnTextChanged
import com.init_android.R
import com.init_android.app.ServiceCreator
import com.init_android.app.data.request.RequestIdCheck
import com.init_android.app.data.request.RequestSignIn
import com.init_android.app.data.response.ResponseIdCheck
import com.init_android.app.data.response.ResponseSignIn
import com.init_android.app.presentation.ui.MainActivity
import com.init_android.databinding.FragmentPersonalInfoBinding
import com.playtogether_android.app.presentation.base.BaseFragment
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.util.regex.Pattern

// 회원가입(id/pw)
class PersonalInfoFragment :
    BaseFragment<FragmentPersonalInfoBinding>(R.layout.fragment_personal_info) {

    // check 변수 확인
    // checkArray => (중복확인, 비밀번호 정규식, 비밀번호 일치)
    var checkArray = arrayOf(false, false, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initTransFragmentEvent() // fragment 교체
        observeEditTxtField() // 텍스트 활동 감지
        textClearEvent() // 텍스트 clear 이벤트
        initIdConfirmBtn() // 중복확인 버튼 이벤트

        goBackBtn() // 뒤로가기
    }


    // Fragment 교체
    private fun initTransFragmentEvent() {
        binding.btnNext.setOnClickListener {
            val baseInfoOneFragment = BaseInfoOneFragment()

            val id = binding.etvId.text.toString()
            val pw = binding.etvPw.text.toString()

            // fragment 에 회원가입 정보 넘겨주기 (id, pw)
            val signUpArray = arrayListOf<Any>(id,pw) // 넘겨줄 배열
            val bundle = Bundle()
            bundle.putSerializable("signUpArray",signUpArray)
            baseInfoOneFragment.arguments = bundle

            parentFragmentManager.beginTransaction()
                .replace(R.id.fragment_sign_up, baseInfoOneFragment)
                .commit()
        }
    }

    // id, pw, pw 확인 입력 감지 함수
    private fun observeEditTxtField() {
        binding.apply {
            etvId.doOnTextChanged { text, start, before, count ->
                checkCloseBtnState(etvId, ibtnIdClose) // 'x' 버튼 활성화 체크
                checkIdConfirmBtnState() // id 정규식 체크
                checkNextBtnState() // 다음 버튼 활성화 여부 체크
            }

            etvPw.doOnTextChanged { text, start, before, count ->
                checkCloseBtnState(etvPw, ibtnPwClose) // 'x' 버튼 활성화 체크
                isValidRegistrationPw() // 비밀번호 정규식 체크
                isPwEquals() // 비밀번호 일치여부 체크
                checkNextBtnState()
            }

            etvPwConfirm.doOnTextChanged { text, start, before, count ->
                checkCloseBtnState(etvPwConfirm, ibtnPwConfirmClose) // 'x' 버튼 활성화 체크
                isPwEquals() // 비밀번호 일치여부 체크
                checkNextBtnState()
            }
        }
    }


    // x버튼 클릭이벤트
    private fun textClearEvent() {
        binding.apply {
            ibtnIdClose.setOnClickListener { binding.etvId.text.clear() }
            ibtnPwClose.setOnClickListener { binding.etvPw.text.clear() }
            ibtnPwConfirmClose.setOnClickListener { binding.etvPwConfirm.text.clear() }
        }
    }

    // 아이디 정규식 체크 및 중복확인 버튼 활성화
    private fun checkIdConfirmBtnState() { // 길이가 2이상이면 활성화
        if (binding.etvId.text.length >= 2) {
            binding.btnIdConfirm.isEnabled = true
            binding.tvWarnId.visibility = View.INVISIBLE
        } else {
            binding.btnIdConfirm.isEnabled = false
            binding.tvWarnId.visibility = View.VISIBLE
        }
    }

    // 비밀번호 정규식
    private fun isValidRegistrationPw() = with(binding) {
        if (!Pattern.matches(
                "^[a-z|A-Z|0-9|(!,@,#,$,&,*,(,))|]{8,15}",
                etvPw.text.toString()
            )
        ) {
            tvWarnPw.visibility = View.VISIBLE
            checkArray[1] = false
        } else {
            tvWarnPw.visibility = View.INVISIBLE
            checkArray[1] = true
        }
    }

    // 비밀번호 일치여부
    private fun isPwEquals() {
        if (binding.etvPw.text.toString() == binding.etvPwConfirm.text.toString()) {
            binding.tvWarnPwConfirm.visibility = View.INVISIBLE
            checkArray[2] = true
        } else {
            binding.tvWarnPwConfirm.visibility = View.VISIBLE
            checkArray[2] = false
        }
    }

    // x 버튼 활성화 여부
    private fun checkCloseBtnState(etv: EditText, closeBtn: ImageButton) {
        if (etv.text.isNotEmpty()) closeBtn.visibility = View.VISIBLE
        else closeBtn.visibility = View.GONE
    }

    // 다음 버튼 활성화 여부 체크
    private fun checkNextBtnState() {
        binding.btnNext.isEnabled = checkArray.contentEquals(arrayOf(true, true, true))
    }

    // 뒤로가기 버튼 활성화
    private fun goBackBtn(){
        binding.ibtnBack.setOnClickListener {
            (activity as SignUpActivity).finish()
        }
    }

    // 중복확인 버튼 이벤트
    private fun initIdConfirmBtn(){
        binding.btnIdConfirm.setOnClickListener {
            tryPostCheckId()
        }
    }

    // 중복확인 서버 통신 시도
    private fun tryPostCheckId(){

        val requestIdCheck = RequestIdCheck(id=binding.etvId.text.toString())

        val call: Call<ResponseIdCheck> = ServiceCreator.initService.postIdCheck(requestIdCheck)

        call.enqueue(object: Callback<ResponseIdCheck> {
            override fun onResponse(
                call: Call<ResponseIdCheck>,
                response: Response<ResponseIdCheck>
            ) {
                if(response.body()?.is_check == true){ // 통과
                    checkArray[0] = true
                    binding.btnIdConfirm.isEnabled = false // 버튼 활성화 막기
                    Toast.makeText(requireContext(), "사용 가능한 아이디입니다.", Toast.LENGTH_SHORT).show()
                }else{ // 이미 사용중인 아이디입니다.
                    Toast.makeText(requireContext(), "이미 사용중인 아이디입니다.", Toast.LENGTH_SHORT).show()
                    checkArray[1] = false
                }

                checkNextBtnState()
            }

            override fun onFailure(call: Call<ResponseIdCheck>, t: Throwable) { // 서버 통신에러
                Log.e("NetworkTest", "error:$t")
            }

        })

    }

    // 다음화면으로 데이터 넘겨주기
}