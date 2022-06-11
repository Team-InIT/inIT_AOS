package com.init_android.app.presentation.ui.open.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.init_android.app.data.ServiceCreator
import com.init_android.app.data.request.RequestAddProject
import com.init_android.app.data.request.project.RequestApplyProject
import com.init_android.app.data.request.project.RequestProjectDetail
import com.init_android.app.data.request.project.RequestProjectMember
import com.init_android.app.data.response.ResponseAddProject
import com.init_android.app.data.response.project.ResponseApplyProject
import com.init_android.app.data.response.project.ResponseProjectDetail
import com.init_android.app.data.response.project.approve.ResponseProjectMember
import kotlinx.coroutines.launch

class ProjectViewModel() : ViewModel() {

    private val _openProject = MutableLiveData<ResponseAddProject>()
    val openProject: LiveData<ResponseAddProject>
        get() = _openProject

    private val _detailProject = MutableLiveData<ResponseProjectDetail>()
    val detailProject: LiveData<ResponseProjectDetail>
        get() = _detailProject


    private val _applyProject = MutableLiveData<ResponseApplyProject>()
    val applyProject: LiveData<ResponseApplyProject>
        get() = _applyProject


    private val _projectMember = MutableLiveData<ResponseProjectMember>()
    val projectMember: LiveData<ResponseProjectMember>
        get() = _projectMember


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

    //프로젝트 지원하기 서버통신
    fun postApplyProject(requestApplyProject: RequestApplyProject) {
        viewModelScope.launch {
            kotlin.runCatching { ServiceCreator.initService.postApplyProject(requestApplyProject) }
                .onSuccess {
                    _applyProject.value = it
                    Log.d("ApplyProject", "서버 통신 성공")
                }
                .onFailure {
                    it.printStackTrace()
                    Log.d("ApplyProject", "서버 통신 실패")
                }
        }
    }

    //팀원 조회 서버통신
    fun postProjectMember(requestProjectMember: RequestProjectMember) {
        viewModelScope.launch {
            kotlin.runCatching { ServiceCreator.initService.postProjectMember(requestProjectMember) }
                .onSuccess {
                    _projectMember.value = it
                    Log.d("projectMember", "서버 통신 성공")
                }
                .onFailure {
                    it.printStackTrace()
                    Log.d("ProjectMember", "서버 통신 실패")
                }
        }
    }
}