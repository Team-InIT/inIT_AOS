package com.init_android.app.presentation.ui.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.init_android.app.data.response.Member
import com.init_android.app.data.response.ResponseSignIn

class MainViewModel() : ViewModel() {

    // 로그인 response 데이터
    var signData = MutableLiveData<Member>()

    //유저 아이디
    var mNum = MutableLiveData<Int>()
    var mPosition = MutableLiveData<Int>()
    var mLevel = MutableLiveData<Int>()

    var text = MutableLiveData<String>()
    var part = MutableLiveData<String>()
    var partNum = MutableLiveData<Int>()
    var project = MutableLiveData<String>()
}