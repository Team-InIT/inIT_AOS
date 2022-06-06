package com.init_android.app.presentation.ui.open.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.init_android.app.data.ServiceCreator
import com.init_android.app.data.request.RequestAddProject
import com.init_android.app.data.request.project.RequestProjectDetail
import com.init_android.app.data.response.ResponseAddProject
import com.init_android.app.data.response.project.ResponseProjectDetail
import kotlinx.coroutines.launch

class ProjectViewModel() : ViewModel() {

    private val _openProject = MutableLiveData<ResponseAddProject>()
    val openProject: LiveData<ResponseAddProject>
        get() = _openProject

    private val _detailProject = MutableLiveData<ResponseProjectDetail>()
    val detailProject: LiveData<ResponseProjectDetail>
        get() = _detailProject

    //프로젝트 작성 서버통신
    fun postOpenProject(requestAddProject: RequestAddProject) {
        viewModelScope.launch {
            kotlin.runCatching { ServiceCreator.initService.postAddProject(requestAddProject) }
                .onSuccess {
                    _openProject.value = it
                    Log.d("OpenProject", "서버 통신 성공")
                }
                .onFailure {
                    it.printStackTrace()
                    Log.d("OpenProject", "서버 통신 실패")
                }
        }

    }

    //프로젝트 상세보기 서버통신
    fun postProjectDetail(requestProjectDetail: RequestProjectDetail) {
        viewModelScope.launch {
            kotlin.runCatching { ServiceCreator.initService.postProjectDetail(requestProjectDetail) }
                .onSuccess {
                    _detailProject.value = it
                    Log.d("DetailProject", "서버 통신 성공")
                }
                .onFailure {
                    it.printStackTrace()
                    Log.d("DetailProject", "서버 통신 실패")
                }
        }
    }
}