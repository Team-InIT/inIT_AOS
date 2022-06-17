package com.init_android.app.presentation.ui.search

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.init_android.app.data.ServiceCreator
import com.init_android.app.data.response.ResponseGetUserAll
import kotlinx.coroutines.launch

class SearchViewModel:ViewModel() {
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
}