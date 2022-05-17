package com.init_android.app.presentation.ui.home.signin.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.init_android.app.data.response.Member
import com.init_android.app.data.response.ResponseSignIn
import okhttp3.Response

class SignViewModel(): ViewModel() {
    private var _signIn = MutableLiveData<Member>()
    val signIn : LiveData<Member>
    get() = _signIn


}