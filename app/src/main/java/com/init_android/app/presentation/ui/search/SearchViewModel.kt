package com.init_android.app.presentation.ui.search

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.init_android.app.data.ServiceCreator
import com.init_android.app.data.request.RequestSearchEd
import com.init_android.app.data.request.RequestSearchIng
import com.init_android.app.data.response.ResponseGetNotRecruitingProject
import com.init_android.app.data.response.ResponseGetRecruitingProject
import com.init_android.app.data.response.ResponseGetUserAll
import com.init_android.app.data.response.ResponseSearchResult
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

    // 검색 response 데이터
    private val _searchResultData = MutableLiveData<ResponseSearchResult>()
    val searchResultData: LiveData<ResponseSearchResult>
        get() = _searchResultData

    // 검색 확인 플래그
    var searchFlags = MutableLiveData<Boolean>()

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

    fun getRecruitingProject() {
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

    fun getNotRecruitingProject() {
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

    // 모집중 검색하기 서버통신
    fun postSearchIng(keyword:String){
        val requestSearchIng = RequestSearchIng(keyword = keyword)
        viewModelScope.launch {
            kotlin.runCatching { ServiceCreator.initService.postSearchIng(requestSearchIng) }
                .onSuccess {
                    _searchResultData.value = it
                    Log.d("searchResultData", "서버 통신 성공")
                }
                .onFailure {
                    it.printStackTrace()
                    Log.d("searchResultData", it.printStackTrace().toString())
                }
        }
    }

    // 모집완료 검색하기 서버통신
    fun postSearchEd(keyword:String){
        val requestSearchEd = RequestSearchEd(keyword = keyword)
        viewModelScope.launch {
            kotlin.runCatching { ServiceCreator.initService.postSearchEd(requestSearchEd) }
                .onSuccess {
                    _searchResultData.value = it
                    Log.d("searchResultData", "서버 통신 성공")
                }
                .onFailure {
                    it.printStackTrace()
                    Log.d("searchResultData", it.printStackTrace().toString())
                }
        }
    }

}