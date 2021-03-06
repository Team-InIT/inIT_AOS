package com.init_android.app.presentation.ui.home.signin

import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.activity.viewModels
import com.init_android.R
import com.init_android.app.data.ServiceCreator
import com.init_android.app.data.request.RequestSignIn
import com.init_android.app.data.response.ResponseSignIn
import com.init_android.app.presentation.ui.main.MainActivity
import com.init_android.app.presentation.ui.home.signin.viewmodel.SignViewModel
import com.init_android.app.presentation.ui.home.signup.SignUpActivity
import com.init_android.app.presentation.ui.main.MainViewModel
import com.init_android.databinding.ActivitySignInBinding
import com.playtogether_android.app.presentation.base.BaseActivity
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import kotlin.math.sign

class SignInActivity : BaseActivity<ActivitySignInBinding>(R.layout.activity_sign_in) {

    private val signViewModel: SignViewModel by viewModels()
    private val mainViewModel: MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        observeEditTxtField() // 텍스트 필드 감지
        textClearEvent() // 텍스트 필드 초기화
        initSignUpBtn() // 회원가입 버튼 초기화
        initLoginBtn() // 로그인 버튼 초기화
    }

    // id, pw 입력 감지 함수
    private fun observeEditTxtField() {
        binding.etvId.addTextChangedListener(textWatcher)
        binding.etvPw.addTextChangedListener(textWatcher)
    }

    // 텍스트 감지 이벤트
    private val textWatcher: TextWatcher = object : TextWatcher {
        override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {}

        override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {
            // 로그인 버튼 활성화
            binding.btnLogin.isEnabled =
                (binding.etvPw.text.isNotEmpty() && binding.etvId.text.isNotEmpty())

//            // 'x' 버튼 활성화
//            if (binding.etvId.text.isNotEmpty()) binding.ibtnIdClose.visibility = View.VISIBLE
//            else binding.ibtnIdClose.visibility = View.GONE // id
//
//            if (binding.etvPw.text.isNotEmpty()) binding.ibtnPwClose.visibility = View.VISIBLE
//            else binding.ibtnPwClose.visibility = View.GONE // pw
        }

        override fun afterTextChanged(s: Editable) {}
    }

    // x버튼 클릭이벤트
    private fun textClearEvent() {
        binding.apply {
//            ibtnIdClose.setOnClickListener { binding.etvId.text.clear() }
//            ibtnPwClose.setOnClickListener { binding.etvPw.text.clear() }
        }
    }

    // 회원가입 화면 이동
    private fun initSignUpBtn() {
        binding.tvSignup.setOnClickListener {
            startActivity(Intent(this, SignUpActivity::class.java))
        }
    }

    // 홈화면 이동
    private fun initLoginBtn() {
        binding.btnLogin.setOnClickListener {
            tryPostLogin()// 로그인 서버 통신 시도
        }
    }

    // 로그인 서버통신 함수
    private fun tryPostLogin() {
        val requestSignIn = RequestSignIn(
            id = binding.etvId.text.toString(),
            pw = binding.etvPw.text.toString()
        )


        signViewModel.postSignIn(requestSignIn)
        //Log.d("SignViewModel", signViewModel.logIn.value.member.indexOf(0))
        signViewModel.logIn.observe(this) {
            if (it.code == 204) {
                val userId = signViewModel.signIn.value?.mNum ?: 1
                Log.d("TestUserId", userId.toString())
                val position = signViewModel.signIn.value?.mPosition ?: 1
                val level = signViewModel.signIn.value?.mLevel ?: 1

                val intent = Intent(this@SignInActivity, MainActivity::class.java)
                intent.putExtra("userId", userId)
                intent.putExtra("position", position)
                intent.putExtra("level", level)

                startActivity(intent)
                finish()
                Toast.makeText(this@SignInActivity, it.message, Toast.LENGTH_SHORT).show()

//                mainViewModel.signData.value = it.member.toMutableList()[0]
            } else {
                Toast.makeText(this@SignInActivity, "존재하지 않습니다.", Toast.LENGTH_SHORT).show()
            }
        }


    }


}