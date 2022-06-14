package com.init_android.app.presentation.ui.home.signin.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.init_android.app.data.ServiceCreator
import com.init_android.app.data.request.RequestSignIn
import com.init_android.app.data.response.Member
import com.init_android.app.data.response.ResponseSignIn
import kotlinx.coroutines.launch
import okhttp3.Response

class SignViewModel(): ViewModel() {

    private val _logIn = MutableLiveData<ResponseSignIn>()
    val logIn : LiveData<ResponseSignIn>
    get() = _logIn

    private var _signIn = MutableLiveData<Member>()
    val signIn : LiveData<Member>
    get() = _signIn


    //로그인 서버통신
    fun postSignIn(requestSignIn: RequestSignIn) {
        viewModelScope.launch {
            kotlin.runCatching {
                ServiceCreator.initService.postLogin(requestSignIn)
            }
                .onSuccess {
                    _logIn.value = it
                    Log.d("SignIn", "서버 통신 성공")
                }
                .onFailure {
                    it.printStackTrace()
                    Log.d("SignIn", "서버 통신 실패")
                }
        }
    }

}