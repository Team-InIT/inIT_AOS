package com.init_android.app.presentation.ui.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.init_android.app.data.response.Member
import com.init_android.app.data.response.ResponseSignIn

class MainViewModel() : ViewModel() {

    // 로그인 response 데이터
    private val _signData = MutableLiveData<Member>()
    val signData: LiveData<Member>
        get() = _signData

    //유저 아이디
    var mId = MutableLiveData<Int>()

    var text = MutableLiveData<String>()
    var part = MutableLiveData<String>()
    var partNum = MutableLiveData<Int>()
    var project = MutableLiveData<String>()
}