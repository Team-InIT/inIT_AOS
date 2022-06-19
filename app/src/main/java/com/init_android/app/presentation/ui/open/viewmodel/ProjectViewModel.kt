package com.init_android.app.presentation.ui.open.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.init_android.app.data.ServiceCreator
import com.init_android.app.data.request.RequestAddProject
import com.init_android.app.data.request.project.RequestApplyProject
import com.init_android.app.data.request.project.RequestApproveProject
import com.init_android.app.data.request.project.RequestProjectDetail
import com.init_android.app.data.request.project.RequestProjectMember
import com.init_android.app.data.response.ResponseAddProject
import com.init_android.app.data.response.project.ResponseApplyProject
import com.init_android.app.data.response.project.ResponseProjectDetail
import com.init_android.app.data.response.project.approve.ResponseApprovePlan
import com.init_android.app.data.response.project.approve.ResponseApproveProject
import com.init_android.app.data.response.project.approve.ResponseProjectMember
import com.init_android.app.data.response.project.ready.*
import kotlinx.coroutines.launch

class ProjectViewModel() : ViewModel() {

    var approveNum = MutableLiveData<Int>()

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

    private val _approvePlan = MutableLiveData<ResponseApprovePlan>()
    val approvePlan: LiveData<ResponseApprovePlan>
        get() = _approvePlan

    private val _myCrewPlan = MutableLiveData<ResponseReadyPlan>()
    val myCrewPlan: LiveData<ResponseReadyPlan>
        get() = _myCrewPlan

    private val _myCrewDesign = MutableLiveData<ResponseReadyDesign>()
    val myCrewDesign: LiveData<ResponseReadyDesign>
        get() = _myCrewDesign

    private val _myCrewIos = MutableLiveData<ResponseReadyIos>()
    val myCrewIos: LiveData<ResponseReadyIos>
        get() = _myCrewIos

    private val _myCrewAos = MutableLiveData<ResponseReadyAos>()
    val myCrewAos: LiveData<ResponseReadyAos>
        get() = _myCrewAos

    private val _myCrewWeb = MutableLiveData<ResponseReadyWeb>()
    val myCrewWeb: LiveData<ResponseReadyWeb>
        get() = _myCrewWeb

    private val _myCrewGame = MutableLiveData<ResponseReadyGame>()
    val myCrewGame: LiveData<ResponseReadyGame>
        get() = _myCrewGame

    private val _myCrewServer = MutableLiveData<ResponseReadyServer>()
    val myCrewServer: LiveData<ResponseReadyServer>
        get() = _myCrewServer

    private val _approve = MutableLiveData<ResponseApproveProject>()
    val approve: LiveData<ResponseApproveProject>
        get() = _approve


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

    //팀원 승인 & 승인 전 - 기획
    fun postMyCrewPlan(requestProjectMember: RequestProjectMember) {
        viewModelScope.launch {
            kotlin.runCatching { ServiceCreator.initService.postCrewPlan(requestProjectMember) }
                .onSuccess {
                    _myCrewPlan.value = it
                    Log.d("MyCrewPlan", "서버 통신 성공")
                }
                .onFailure {
                    it.printStackTrace()
                    Log.d("MyCrewPlan", "서버 통신 실패")
                }
        }
    }

    //팀원 승인 & 승인 전 - 디자인
    fun postMyCrewDesign(requestProjectMember: RequestProjectMember) {
        viewModelScope.launch {
            kotlin.runCatching { ServiceCreator.initService.postCrewDesign(requestProjectMember) }
                .onSuccess {
                    _myCrewDesign.value = it
                    Log.d("MyCrewDesign", "서버 통신 성공")
                }
                .onFailure {
                    it.printStackTrace()
                    Log.d("MyCrewDesign", "서버 통신 실패")
                }
        }
    }

    //팀원 승인 & 승인 전 - aos
    fun postMyCrewAos(requestProjectMember: RequestProjectMember) {
        viewModelScope.launch {
            kotlin.runCatching { ServiceCreator.initService.postCrewAos(requestProjectMember) }
                .onSuccess {
                    _myCrewAos.value = it
                    Log.d("MyCrewAos", "서버 통신 성공")
                }
                .onFailure {
                    it.printStackTrace()
                    Log.d("MyCrewAos", "서버 통신 실패")
                }
        }
    }

    //팀원 승인 & 승인 전 - ios
    fun postMyCrewIos(requestProjectMember: RequestProjectMember) {
        viewModelScope.launch {
            kotlin.runCatching { ServiceCreator.initService.postCrewIos(requestProjectMember) }
                .onSuccess {
                    _myCrewIos.value = it
                    Log.d("MyCrewIos", "서버 통신 성공")
                }
                .onFailure {
                    it.printStackTrace()
                    Log.d("MyCrewIos", "서버 통신 실패")
                }
        }
    }

    //팀원 승인 & 승인 전 - web
    fun postMyCrewWeb(requestProjectMember: RequestProjectMember) {
        viewModelScope.launch {
            kotlin.runCatching { ServiceCreator.initService.postCrewWeb(requestProjectMember) }
                .onSuccess {
                    _myCrewWeb.value = it
                    Log.d("MyCrewWeb", "서버 통신 성공")
                }
                .onFailure {
                    it.printStackTrace()
                    Log.d("MyCrewWeb", "서버 통신 실패")
                }
        }
    }

    //팀원 승인 & 승인 전 - game
    fun postMyCrewGame(requestProjectMember: RequestProjectMember) {
        viewModelScope.launch {
            kotlin.runCatching { ServiceCreator.initService.postCrewGame(requestProjectMember) }
                .onSuccess {
                    _myCrewGame.value = it
                    Log.d("MyCrewGame", "서버 통신 성공")
                }
                .onFailure {
                    it.printStackTrace()
                    Log.d("MyCrewGame", "서버 통신 실패")
                }
        }
    }

    //팀원 승인 & 승인 전 - server
    fun postMyCrewServer(requestProjectMember: RequestProjectMember) {
        viewModelScope.launch {
            kotlin.runCatching { ServiceCreator.initService.postCrewServer(requestProjectMember) }
                .onSuccess {
                    _myCrewServer.value = it
                    Log.d("MyCrewServer", "서버 통신 성공")
                }
                .onFailure {
                    it.printStackTrace()
                    Log.d("MyCrewServer", "서버 통신 실패")
                }
        }
    }

    //팀원 승인
    fun postApprove(requestApproveProject: RequestApproveProject) {
        viewModelScope.launch {
            kotlin.runCatching { ServiceCreator.initService.postApprove(requestApproveProject) }
                .onSuccess {
                    _approve.value = it
                    Log.d("TeamApprove", "서버 통신 성공")
                }
                .onFailure {
                    it.printStackTrace()
                    Log.d("TeamApprove", "서버 통신 실패")
                }
        }
    }
}