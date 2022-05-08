package com.init_android.app.presentation.ui.home.signin

import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import com.init_android.R
import com.init_android.app.presentation.ui.home.signup.SignUpActivity
import com.init_android.databinding.ActivitySignInBinding
import com.playtogether_android.app.presentation.base.BaseActivity

class SignInActivity : BaseActivity<ActivitySignInBinding>(R.layout.activity_sign_in) {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        observeEditTxtField() // 텍스트 필드 감지
        textClearEvent() // 텍스트 필드 초기화
        initSignUpEvent() // 회원가입 이동
    }

    // id, pw 입력 감지 함수
    private fun observeEditTxtField(){
        binding.etvId.addTextChangedListener(textWatcher)
        binding.etvPw.addTextChangedListener(textWatcher)
    }

    // 텍스트 감지 이벤트
    private val textWatcher: TextWatcher = object : TextWatcher {
        override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {}

        override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {
            // 로그인 버튼 활성화
            binding.btnLogin.isEnabled = (binding.etvPw.text.isNotEmpty() && binding.etvId.text.isNotEmpty())

            // 'x' 버튼 활성화
            if (binding.etvId.text.isNotEmpty()) binding.ibtnIdClose.visibility = View.VISIBLE
            else binding.ibtnIdClose.visibility = View.GONE // id

            if (binding.etvPw.text.isNotEmpty()) binding.ibtnPwClose.visibility = View.VISIBLE
            else binding.ibtnPwClose.visibility = View.GONE // pw
        }

        override fun afterTextChanged(s: Editable) {}
    }

    // x버튼 클릭이벤트
    private fun textClearEvent(){
        binding.apply {
            ibtnIdClose.setOnClickListener { binding.etvId.text.clear() }
            ibtnPwClose.setOnClickListener { binding.etvPw.text.clear() }
        }
    }

    // 회원가입 화면 이동
    private fun initSignUpEvent(){
        binding.tvSignup.setOnClickListener {
            startActivity(Intent(this, SignUpActivity::class.java))
        }
    }

    // todo 서버통신
    // 로그인 실패 시 -> warning 텍스트 visibility 변경해주기
}