package com.init_android.app.presentation.ui.search

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.init_android.app.data.ServiceCreator
import com.init_android.app.data.response.ResponseGetNotRecruitingProject
import com.init_android.app.data.response.ResponseGetRecruitingProject
import com.init_android.app.data.response.ResponseGetUserAll
import kotlinx.coroutines.launch

class SearchViewModel : ViewModel() {

    // 모집중 response 데이터
    private val _recruitingData = MutableLiveData<ResponseGetRecruitingProject>()
    val recruitingData: LiveData<ResponseGetRecruitingProject>
        get() = _recruitingData

    // 모집완료 response 데이터
    private val _notRecruitingData = MutableLiveData<ResponseGetNotRecruitingProject>()
    val notRecruitingProject: LiveData<ResponseGetNotRecruitingProject>
        get() = _notRecruitingData

    // 파트너 response 데이터
    private val _partnerData = MutableLiveData<ResponseGetUserAll>()
    val partnerData: LiveData<ResponseGetUserAll>
        get() = _partnerData

    fun getUserAll() {
        viewModelScope.launch {
            kotlin.runCatching { ServiceCreator.initService.getUserAll() }
                .onSuccess {
                    _partnerData.value = it
                    Log.d("partnerData", "서버 통신 성공")
                }
                .onFailure {
                    it.printStackTrace()
                    Log.d("partnerData", it.printStackTrace().toString())
                }
        }
    }

    fun getRecruitingProject(){
        viewModelScope.launch {
            kotlin.runCatching { ServiceCreator.initService.getRecrutingProject() }
                .onSuccess {
                    _recruitingData.value = it
                    Log.d("recruitingData", "서버 통신 성공")
                }
                .onFailure {
                    it.printStackTrace()
                    Log.d("recruitingData", it.printStackTrace().toString())
                }
        }
    }

    fun getNotRecruitingProject(){
        viewModelScope.launch {
            kotlin.runCatching { ServiceCreator.initService.getNotRecruitingProject() }
                .onSuccess {
                    _notRecruitingData.value = it
                    Log.d("recruitingData", "서버 통신 성공")
                }
                .onFailure {
                    it.printStackTrace()
                    Log.d("notRecruitingData", it.printStackTrace().toString())
                }
        }
    }
}