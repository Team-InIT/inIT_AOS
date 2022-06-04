package com.init_android.app.presentation.ui.mypage.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.init_android.app.ServiceCreator
import com.init_android.app.data.request.RequestHome
import com.init_android.app.data.request.mypage.RequestModifyBasicInfo
import com.init_android.app.data.request.mypage.RequestModifyLink
import com.init_android.app.data.request.mypage.RequestMyInfo
import com.init_android.app.data.response.ResponseHome
import com.init_android.app.data.response.mypage.ResponseModifyBasicInfo
import com.init_android.app.data.response.mypage.ResponseModifyLink
import com.init_android.app.data.response.mypage.ResponseMyInfo
import kotlinx.coroutines.launch

class MyPageViewModel() : ViewModel() {

    private val _myInfoData = MutableLiveData<ResponseMyInfo>()
    val myInfoData: LiveData<ResponseMyInfo>
        get() = _myInfoData


    private val _modifyLink = MutableLiveData<ResponseModifyLink>()
    val modifyLink: LiveData<ResponseModifyLink>
        get() = _modifyLink

    private val _modifyBasicInfo = MutableLiveData<ResponseModifyBasicInfo>()
    val modifyBasicInfo: LiveData<ResponseModifyBasicInfo>
        get() = _modifyBasicInfo

    // 서버통신
    fun postMyInfo(requestMyInfo: RequestMyInfo) {
        viewModelScope.launch {
            kotlin.runCatching { ServiceCreator.initService.postMyPageInfo(requestMyInfo) }
                .onSuccess {
                    _myInfoData.value = it
                    Log.d("UserInfo", "서버 통신 성공")
                }
                .onFailure {
                    it.printStackTrace()
                    Log.d("UserInfo", "서버 통신 실패")
                }
        }
    }

    //링크 정보 수정 서버 통신
    fun postModifyLink(requestModifyLink: RequestModifyLink) {
        viewModelScope.launch {
            kotlin.runCatching { ServiceCreator.initService.postModifyLink(requestModifyLink) }
                .onSuccess {
                    _modifyLink.value = it
                    Log.d("ModifyLink", "서버 통신 성공")
                }
                .onFailure {
                    it.printStackTrace()
                    Log.d("ModifyLink", "서버 통신 실패")
                }
        }
    }

    //기본 정보 수정 서버 통신
    fun postModifyBasicInfo(requestModifyBasicInfo: RequestModifyBasicInfo) {
        viewModelScope.launch {
            kotlin.runCatching { ServiceCreator.initService.postModifyBasicInfo(requestModifyBasicInfo) }
                .onSuccess {
                    _modifyBasicInfo.value = it
                    Log.d("ModifyBasicInfo", "서버 통신 성공")
                }
                .onFailure {
                    it.printStackTrace()
                    Log.d("ModifyBasicInfo", "서버 통신 실패")
                }
        }
    }

}