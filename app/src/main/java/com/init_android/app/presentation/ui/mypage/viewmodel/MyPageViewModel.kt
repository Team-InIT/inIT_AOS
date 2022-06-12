package com.init_android.app.presentation.ui.mypage.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.init_android.app.data.ServiceCreator
import com.init_android.app.data.request.mypage.*
import com.init_android.app.data.response.ResponseUpdateProfile
import com.init_android.app.data.response.mypage.*
import com.init_android.app.data.response.project.approve.ResponsemyWaitingApproval
import kotlinx.coroutines.launch
import okhttp3.MultipartBody
import okhttp3.RequestBody
import retrofit2.http.Multipart
import retrofit2.http.Part

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

    private val _updateProfile = MutableLiveData<ResponseUpdateProfile>()
    val updateProfile: LiveData<ResponseUpdateProfile>
        get() = _updateProfile


    private val _waitingApprove = MutableLiveData<ResponsemyWaitingApproval>()
    val waitingApprove: LiveData<ResponsemyWaitingApproval>
        get() = _waitingApprove


    private val _modifyStack = MutableLiveData<ResponseModifyStack>()
    val modifyStack: LiveData<ResponseModifyStack>
        get() = _modifyStack

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

    //기본정보수정 서버통신
    fun postUpdateProfile(
        @Part file: MultipartBody.Part,
        @Part("mNum") mNum: RequestBody,
        @Part("mName") mName: RequestBody,
        @Part("mPosition") mPosition: RequestBody,
        @Part("mLevel") mLevel: RequestBody,
        @Part("mIntroduction") mIntroduction: RequestBody
    ) {
        viewModelScope.launch {
            kotlin.runCatching {
                ServiceCreator.initService.postUpdateProfile(
                    file, mNum, mName, mPosition, mLevel, mIntroduction
                )
            }
                .onSuccess {
                    _updateProfile.value = it
                    Log.d("updateProfile", "서버 통신 성공")
                }
                .onFailure {
                    it.printStackTrace()
                    Log.d("updateProfile", "서버 통신 실패")
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

    //승인대기중인 프로젝트 서버통신
    fun postWaitingApprove(requestWaitingApproval: RequestWaitingApproval) {
        viewModelScope.launch {
            kotlin.runCatching {
                ServiceCreator.initService.postWaitingApproval(
                    requestWaitingApproval
                )
            }
                .onSuccess {
                    _waitingApprove.value = it
                    Log.d("WaitingApprove", "서버 통신 성공")
                }
                .onFailure {
                    it.printStackTrace()
                    Log.d("WaitingApprove", "서버 통신 실패")
                }
        }
    }

    //스택 수정
    fun postModifyStack(requestModifyStack: RequestModifyStack) {
        viewModelScope.launch {
            kotlin.runCatching {
                ServiceCreator.initService.postModifyStack(requestModifyStack)
            }
                .onSuccess {
                    _modifyStack.value = it
                    Log.d("ModifyStack", "서버 통신 성공")
                }
                .onFailure {
                    it.printStackTrace()
                    Log.d("ModifyStack", "서버 통신 실패")
                }
        }
    }

}