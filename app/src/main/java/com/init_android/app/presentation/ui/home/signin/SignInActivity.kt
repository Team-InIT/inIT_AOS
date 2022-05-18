package com.init_android.app.presentation.ui.home.signin

import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.View
import android.widget.Toast
import com.init_android.R
import com.init_android.app.ServiceCreator
import com.init_android.app.data.request.RequestSignIn
import com.init_android.app.data.response.ResponseSignIn
import com.init_android.app.presentation.ui.MainActivity
import com.init_android.app.presentation.ui.home.signup.SignUpActivity
import com.init_android.databinding.ActivitySignInBinding
import com.playtogether_android.app.presentation.base.BaseActivity
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class SignInActivity : BaseActivity<ActivitySignInBinding>(R.layout.activity_sign_in) {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        observeEditTxtField() // 텍스트 필드 감지
        textClearEvent() // 텍스트 필드 초기화
        initSignUpBtn() // 회원가입 버튼 초기화
        initLoginBtn() // 로그인 버튼 초기화
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
    private fun initSignUpBtn(){
        binding.tvSignup.setOnClickListener {
            startActivity(Intent(this, SignUpActivity::class.java))
        }
    }

    // 홈화면 이동
    private fun initLoginBtn(){
        binding.btnLogin.setOnClickListener {
            tryPostLogin()// 로그인 서버 통신 시도
        }
    }

    // 로그인 서버통신 함수
    private fun tryPostLogin(){
        val requestSignIn = RequestSignIn(
            id = binding.etvId.text.toString(),
            pw = binding.etvPw.text.toString()
        )


        val call: Call<ResponseSignIn> = ServiceCreator.initService.postLogin(requestSignIn)

        call.enqueue(object:Callback<ResponseSignIn>{
            override fun onResponse(
                call: Call<ResponseSignIn>,
                response: Response<ResponseSignIn>
            ) {
                if(response.body()?.code == 205){ // 로그인 성공
                    startActivity(Intent(this@SignInActivity, MainActivity::class.java))
                    Toast.makeText(this@SignInActivity, response.body()?.message, Toast.LENGTH_SHORT).show()
                }else{ // 201~204 -> 로그인 실패
                    binding.tvWarning.text = response.body()?.message
                    binding.tvWarning.visibility = View.VISIBLE
                }
            }

            override fun onFailure(call: Call<ResponseSignIn>, t: Throwable) { // 서버 통신에러
                Log.e("NetworkTest", "error:$t")
            }

        })

    }


}