package com.init_android.app.presentation.ui.home.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.init_android.app.data.ResponseNewHome
import com.init_android.app.data.ServiceCreator
import com.init_android.app.data.request.RequestFinishProject
import com.init_android.app.data.request.RequestHome
import com.init_android.app.data.response.ResponseFinishProject
import kotlinx.coroutines.launch

class HomeViewModel() : ViewModel() {

    private val _homeData = MutableLiveData<ResponseNewHome>()
    val homeData: LiveData<ResponseNewHome>
        get() = _homeData

    private val _finishProject = MutableLiveData<ResponseFinishProject>()
    val finishProject: LiveData<ResponseFinishProject>
        get() = _finishProject

    private val _approveProject = MutableLiveData<ResponseFinishProject.Project>()
    val approveProject: LiveData<ResponseFinishProject.Project>
        get() = _approveProject


    // 서버통신
    fun postHomeData(requestHome: RequestHome) {
        viewModelScope.launch {
            kotlin.runCatching { ServiceCreator.initService.postHome(requestHome) }
                .onSuccess {
                    _homeData.value = it
                    Log.d("UserInfo", "서버 통신 성공")
                }
                .onFailure {
                    it.printStackTrace()
                    Log.d("UserInfo", "서버 통신 실패")
                }
        }
    }
}