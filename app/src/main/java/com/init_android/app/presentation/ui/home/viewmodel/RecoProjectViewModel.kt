package com.init_android.app.presentation.ui.home.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.init_android.app.ServiceCreator
import com.init_android.app.data.request.RequestRecoProject
import com.init_android.app.data.response.ResponseRecoProject
import kotlinx.coroutines.launch

class RecoProjectViewModel:ViewModel() {
    private val _recoProjectData = MutableLiveData<ResponseRecoProject>()
    val recoProjectData: LiveData<ResponseRecoProject>
        get() = _recoProjectData

    // 서버통신
    fun postRecoProjectData(requestRecoProject: RequestRecoProject) {
        viewModelScope.launch {
            kotlin.runCatching { ServiceCreator.initService.postRecoProject(requestRecoProject) }
                .onSuccess {
                    _recoProjectData.value = it
                    Log.d("UserInfo", "서버 통신 성공")
                }
                .onFailure {
                    it.printStackTrace()
                    Log.d("UserInfo", "서버 통신 실패")
                }
        }
    }
}