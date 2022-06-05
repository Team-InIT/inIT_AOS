package com.init_android.app.presentation.ui.mypage.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.init_android.app.data.ServiceCreator
import com.init_android.app.data.request.mypage.*
import com.init_android.app.data.response.mypage.*
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


    private val _quit = MutableLiveData<ResponseQuit>()
    val quit: LiveData<ResponseQuit>
        get() = _quit

    private val _countProject = MutableLiveData<ResponseCountProject>()
    val countProject: LiveData<ResponseCountProject>
        get() = _countProject

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
            kotlin.runCatching {
                ServiceCreator.initService.postModifyBasicInfo(
                    requestModifyBasicInfo
                )
            }
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

    //회원탈퇴 서버통신
    fun postQuit(requestQuit: RequestQuit) {
        viewModelScope.launch {
            kotlin.runCatching { ServiceCreator.initService.postQuit(requestQuit) }
                .onSuccess {
                    _quit.value = it
                    Log.d("Quit", "서버 통신 성공")
                }
                .onFailure {
                    it.printStackTrace()
                    Log.d("Quit", "서버 통신 실패")
                }
        }
    }

    //프로젝트 갯수 조회 서버통신
    fun postCountProject(requestCountProject: RequestCountProject) {
        viewModelScope.launch {
            kotlin.runCatching { ServiceCreator.initService.postCountProject(requestCountProject) }
                .onSuccess {
                    _countProject.value = it
                    Log.d("CountProject", "서버 통신 성공")
                }
                .onFailure {
                    it.printStackTrace()
                    Log.d("CountProject", "서버 통신 실패")
                }
        }
    }

}