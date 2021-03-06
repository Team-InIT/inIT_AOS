package com.init_android.app.presentation.ui.mypage.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.init_android.app.data.ServiceCreator
import com.init_android.app.data.request.RequestMyFeed
import com.init_android.app.data.request.mypage.*
import com.init_android.app.data.response.ResponseMyFeed
import com.init_android.app.data.response.ResponseUpdateProfile
import com.init_android.app.data.response.mypage.*
import com.init_android.app.data.response.project.approve.ResponsemyWaitingApproval
import kotlinx.coroutines.launch
import okhttp3.MultipartBody
import okhttp3.RequestBody
import okhttp3.Response
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


    private val _endApprove = MutableLiveData<ResponsemyWaitingApproval>()
    val endApprove: LiveData<ResponsemyWaitingApproval>
        get() = _endApprove

    private val _modifyStack = MutableLiveData<ResponseModifyStack>()
    val modifyStack: LiveData<ResponseModifyStack>
        get() = _modifyStack

    private val _feedList = MutableLiveData<ResponseMyFeed>()
    val feedList: LiveData<ResponseMyFeed>
        get() = _feedList

    private val _zzimList = MutableLiveData<ResponsemyWaitingApproval>()
    val zzimList: LiveData<ResponsemyWaitingApproval>
        get() = _zzimList

    private val _uploadList = MutableLiveData<ResponseUpload>()
    val uploadList: LiveData<ResponseUpload>
        get() = _uploadList


    private val _myEvaluation = MutableLiveData<ResponseEvaluation>()
    val myEvaluation: LiveData<ResponseEvaluation>
        get() = _myEvaluation

    // ????????????
    fun postMyInfo(requestMyInfo: RequestMyInfo) {
        viewModelScope.launch {
            kotlin.runCatching { ServiceCreator.initService.postMyPageInfo(requestMyInfo) }
                .onSuccess {
                    _myInfoData.value = it
                    Log.d("UserInfo", "?????? ?????? ??????")
                }
                .onFailure {
                    it.printStackTrace()
                    Log.d("UserInfo", "?????? ?????? ??????")
                }
        }
    }

    //?????? ?????? ?????? ?????? ??????
    fun postModifyLink(requestModifyLink: RequestModifyLink) {
        viewModelScope.launch {
            kotlin.runCatching { ServiceCreator.initService.postModifyLink(requestModifyLink) }
                .onSuccess {
                    _modifyLink.value = it
                    Log.d("ModifyLink", "?????? ?????? ??????")
                }
                .onFailure {
                    it.printStackTrace()
                    Log.d("ModifyLink", "?????? ?????? ??????")
                }
        }
    }

    //?????? ?????? ?????? ?????? ??????
    fun postModifyBasicInfo(requestModifyBasicInfo: RequestModifyBasicInfo) {
        viewModelScope.launch {
            kotlin.runCatching {
                ServiceCreator.initService.postModifyBasicInfo(
                    requestModifyBasicInfo
                )
            }
                .onSuccess {
                    _modifyBasicInfo.value = it
                    Log.d("ModifyBasicInfo", "?????? ?????? ??????")
                }
                .onFailure {
                    it.printStackTrace()
                    Log.d("ModifyBasicInfo", "?????? ?????? ??????")
                }
        }
    }

    //?????????????????? ????????????
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
                    Log.d("updateProfile", "?????? ?????? ??????")
                }
                .onFailure {
                    it.printStackTrace()
                    Log.d("updateProfile", "?????? ?????? ??????")
                }
        }
    }

    //???????????? ????????????
    fun postQuit(requestQuit: RequestQuit) {
        viewModelScope.launch {
            kotlin.runCatching { ServiceCreator.initService.postQuit(requestQuit) }
                .onSuccess {
                    _quit.value = it
                    Log.d("Quit", "?????? ?????? ??????")
                }
                .onFailure {
                    it.printStackTrace()
                    Log.d("Quit", "?????? ?????? ??????")
                }
        }
    }

    //???????????? ?????? ?????? ????????????
    fun postCountProject(requestCountProject: RequestCountProject) {
        viewModelScope.launch {
            kotlin.runCatching { ServiceCreator.initService.postCountProject(requestCountProject) }
                .onSuccess {
                    _countProject.value = it
                    Log.d("CountProject", "?????? ?????? ??????")
                }
                .onFailure {
                    it.printStackTrace()
                    Log.d("CountProject", "?????? ?????? ??????")
                }
        }
    }

    //?????????????????? ???????????? ????????????
    fun postWaitingApprove(requestWaitingApproval: RequestWaitingApproval) {
        viewModelScope.launch {
            kotlin.runCatching {
                ServiceCreator.initService.postWaitingApproval(
                    requestWaitingApproval
                )
            }
                .onSuccess {
                    _waitingApprove.value = it
                    Log.d("WaitingApprove", "?????? ?????? ??????")
                }
                .onFailure {
                    it.printStackTrace()
                    Log.d("WaitingApprove", "?????? ?????? ??????")
                }
        }
    }


    //?????? ??????
    fun postModifyStack(requestModifyStack: RequestModifyStack) {
        viewModelScope.launch {
            kotlin.runCatching {
                ServiceCreator.initService.postModifyStack(requestModifyStack)
            }
                .onSuccess {
                    _modifyStack.value = it
                    Log.d("ModifyStack", "?????? ?????? ??????")
                }
                .onFailure {
                    it.printStackTrace()
                    Log.d("ModifyStack", "?????? ?????? ??????")


                }
        }
    }

    // ??? ?????? ????????? ????????????
    fun postMyFeeds(requestMyFeed: RequestMyFeed) {
        viewModelScope.launch {
            kotlin.runCatching {
                ServiceCreator.initService.postMyFeeds(requestMyFeed)
            }.onSuccess {
                _feedList.value = it
                Log.d("myFeedList", "?????? ?????? ??????")
            }
                .onFailure {
                    it.printStackTrace()
                    Log.d("myFeedList", "?????? ?????? ??????")

                }
        }
    }

    //???????????? ???????????? ??????
    fun postIngProject(requestWaitingApproval: RequestWaitingApproval) {
        viewModelScope.launch {
            kotlin.runCatching {
                ServiceCreator.initService.postIngProject(requestWaitingApproval)
            }
                .onSuccess {
                    _waitingApprove.value = it
                    Log.d("IngProject", "?????? ?????? ??????")
                }
                .onFailure {
                    it.printStackTrace()
                    Log.d("IngProject", "?????? ?????? ??????")
                }
        }
    }

    //?????? ???????????? ??????
    fun postEndProject(requestWaitingApproval: RequestWaitingApproval) {
        viewModelScope.launch {
            kotlin.runCatching {
                ServiceCreator.initService.postEndProject(requestWaitingApproval)
            }
                .onSuccess {
                    _endApprove.value = it
                    Log.d("EndProject", "?????? ?????? ??????")
                }
                .onFailure {
                    it.printStackTrace()
                    Log.d("EndProject", "?????? ?????? ??????")
                }
        }
    }

    fun postZzimProject(requestWaitingApproval: RequestWaitingApproval) {
        viewModelScope.launch {
            kotlin.runCatching {
                ServiceCreator.initService.postZzimProject(requestWaitingApproval)
            }
                .onSuccess {
                    _zzimList.value = it
                    Log.d("ZzimProject", "?????? ?????? ??????")
                }
                .onFailure {
                    it.printStackTrace()
                    Log.d("ZzimProject", "?????? ?????? ??????")
                }
        }
    }

    fun postUploadProject(requestWaitingApproval: RequestWaitingApproval) {
        viewModelScope.launch {
            kotlin.runCatching {
                ServiceCreator.initService.postUploadProject(requestWaitingApproval)
            }
                .onSuccess {
                    _uploadList.value = it
                    Log.d("UploadList", "?????? ?????? ??????")
                }
                .onFailure {
                    it.printStackTrace()
                    Log.d("UploadList", "?????? ?????? ??????")
                }
        }
    }

    //???????????? ????????????
    fun postEvalMem(requestWaitingApproval: RequestWaitingApproval) {
        viewModelScope.launch {
            kotlin.runCatching {
                ServiceCreator.initService.postMyEvaluation(requestWaitingApproval)
            }
                .onSuccess {
                    _myEvaluation.value = it
                    Log.d("MyEvaluation", "?????? ?????? ??????")
                }
                .onFailure {
                    it.printStackTrace()
                    Log.d("MyEvaluation", "?????? ?????? ??????")
                }
        }
    }
}