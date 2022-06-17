package com.init_android.app.presentation.ui.open.todo.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.init_android.app.data.ServiceCreator
import com.init_android.app.data.request.project.RequestProjectMember
import com.init_android.app.data.request.todo.RequestToDoMember
import com.init_android.app.data.response.todo.ResponseAllToDo
import com.init_android.app.data.response.todo.ResponseToDoMember
import kotlinx.coroutines.launch

class ToDoViewModel() : ViewModel() {

    private val _readAllToDo = MutableLiveData<ResponseAllToDo>()
    val readAllToDo: LiveData<ResponseAllToDo>
        get() = _readAllToDo

    private val _todoMember = MutableLiveData<ResponseToDoMember>()
    val todoMember: LiveData<ResponseToDoMember>
        get() = _todoMember

    //todo 기획 조회
    fun postReadPlanToDo(requestProjectMember: RequestProjectMember) {
        viewModelScope.launch {
            kotlin.runCatching { ServiceCreator.initService.postLookUpPlanTodo(requestProjectMember) }
                .onSuccess {
                    _readAllToDo.value = it
                    Log.d("ReadPlanToDo", "서버 통신 성공")
                }

                .onFailure {
                    it.printStackTrace()
                    Log.d("ReadPlanToDo", "서버 통신 실패")
                }
        }
    }

    //todo 디자인 조회
    fun postReadDesignToDo(requestProjectMember: RequestProjectMember) {
        viewModelScope.launch {
            kotlin.runCatching {
                ServiceCreator.initService.postLookUpDesignTodo(
                    requestProjectMember
                )
            }
                .onSuccess {
                    _readAllToDo.value = it
                    Log.d("ReadDesignToDo", "서버 통신 성공")
                }

                .onFailure {
                    it.printStackTrace()
                    Log.d("ReadDesignToDo", "서버 통신 실패")
                }
        }
    }

    //todo ios 조회
    fun postReadIosToDo(requestProjectMember: RequestProjectMember) {
        viewModelScope.launch {
            kotlin.runCatching { ServiceCreator.initService.postLookUpIosTodo(requestProjectMember) }
                .onSuccess {
                    _readAllToDo.value = it
                    Log.d("ReadIosToDo", "서버 통신 성공")
                }

                .onFailure {
                    it.printStackTrace()
                    Log.d("ReadIosToDo", "서버 통신 실패")
                }
        }
    }


    //todo aos 조회
    fun postReadAosToDo(requestProjectMember: RequestProjectMember) {
        viewModelScope.launch {
            kotlin.runCatching { ServiceCreator.initService.postLookUpAosTodo(requestProjectMember) }
                .onSuccess {
                    _readAllToDo.value = it
                    Log.d("ReadAosToDo", "서버 통신 성공")
                }

                .onFailure {
                    it.printStackTrace()
                    Log.d("ReadAosToDo", "서버 통신 실패")
                }
        }
    }


    //todo web 조회
    fun postReadWebToDo(requestProjectMember: RequestProjectMember) {
        viewModelScope.launch {
            kotlin.runCatching { ServiceCreator.initService.postLookUpWebTodo(requestProjectMember) }
                .onSuccess {
                    _readAllToDo.value = it
                    Log.d("ReadWebToDo", "서버 통신 성공")
                }

                .onFailure {
                    it.printStackTrace()
                    Log.d("ReadWebToDo", "서버 통신 실패")
                }
        }
    }

    //todo game 조회
    fun postReadGameToDo(requestProjectMember: RequestProjectMember) {
        viewModelScope.launch {
            kotlin.runCatching { ServiceCreator.initService.postLookUpGameTodo(requestProjectMember) }
                .onSuccess {
                    _readAllToDo.value = it
                    Log.d("ReadGameToDo", "서버 통신 성공")
                }

                .onFailure {
                    it.printStackTrace()
                    Log.d("ReadGameToDo", "서버 통신 실패")
                }
        }
    }

    //todo server 조회
    fun postReadServerToDo(requestProjectMember: RequestProjectMember) {
        viewModelScope.launch {
            kotlin.runCatching {
                ServiceCreator.initService.postLookUpServerTodo(
                    requestProjectMember
                )
            }
                .onSuccess {
                    _readAllToDo.value = it
                    Log.d("ReadServerToDo", "서버 통신 성공")
                }

                .onFailure {
                    it.printStackTrace()
                    Log.d("ReadServerToDo", "서버 통신 실패")
                }
        }
    }

    fun postToDoMember(requestToDoMember: RequestToDoMember) {
        viewModelScope.launch {
            kotlin.runCatching { ServiceCreator.initService.postToDoMember(requestToDoMember) }
                .onSuccess {
                    _todoMember.value = it
                    Log.d("todoMember", "서버 통신 성공")
                }
                .onFailure {
                    it.printStackTrace()
                    Log.d("todoMember", "서버 통신 실패")
                }
        }
    }
}