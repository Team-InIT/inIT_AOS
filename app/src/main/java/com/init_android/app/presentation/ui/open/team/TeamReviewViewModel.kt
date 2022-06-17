package com.init_android.app.presentation.ui.open.team

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.init_android.app.data.ServiceCreator
import com.init_android.app.data.request.RequestAddEvaluate
import com.init_android.app.data.request.RequestAlreadyEvaluate
import com.init_android.app.data.request.RequestNotEveluate
import com.init_android.app.data.response.ResponseAlreadyEvaluate
import com.init_android.app.data.response.ResponseBase
import com.init_android.app.data.response.ResponseNotEveluate
import kotlinx.coroutines.launch

class TeamReviewViewModel : ViewModel() {
    // 평가할/평가된 팀원 Num
    var selectedPersonNum = MutableLiveData<Int>()

    // 미평가 팀원 리스트 조회 데이터
    private val _notEveluate = MutableLiveData<ResponseNotEveluate>()
    val notEveluate: LiveData<ResponseNotEveluate>
        get() = _notEveluate

    // 평가 완료 팀원 리스트 조회 데이터
    private val _evaluate = MutableLiveData<ResponseAlreadyEvaluate>()
    val evaluate: LiveData<ResponseAlreadyEvaluate>
        get() = _evaluate

    // 넘겨줄 정보 (서버가 주는 값에 따라 변경될 예정)
    private val _postReviewData = MutableLiveData<ResponseBase>()
    val postReviewData: LiveData<ResponseBase>
        get() = _postReviewData

    // 미평가 리스트 조회 서버통신
    fun postNotEveluate(requestNotEveluate: RequestNotEveluate) {
        viewModelScope.launch {
            kotlin.runCatching { ServiceCreator.initService.postNotEveluate(requestNotEveluate) }
                .onSuccess {
                    _notEveluate.value = it
                    Log.d("notEveluate", "서버 통신 성공")
                }
                .onFailure {
                    it.printStackTrace()
                    Log.d("notEveluate", it.printStackTrace().toString())
                }
        }
    }

    // 팀원 평가 서버통신
    fun postAddEvaluate(requestAddEvaluate: RequestAddEvaluate) {
        viewModelScope.launch {
            kotlin.runCatching { ServiceCreator.initService.postAddEveluate(requestAddEvaluate) }
                .onSuccess {
                    _postReviewData.value = it
                    Log.d("postReviewData", "서버 통신 성공")
                }
                .onFailure {
                    it.printStackTrace()
                    Log.d("postReviewData", it.printStackTrace().toString())
                }
        }
    }

    // 평가완료된 팀원 리스트 서버통신
    fun postAlreadyEveluate(requestAlreadyEvaluate: RequestAlreadyEvaluate) {
        viewModelScope.launch {
            kotlin.runCatching { ServiceCreator.initService.postAlreadyEvaluate(requestAlreadyEvaluate) }
                .onSuccess {
                    _evaluate.value = it
                    Log.d("evaluate", "서버 통신 성공")
                }
                .onFailure {
                    it.printStackTrace()
                    Log.d("evaluate", it.printStackTrace().toString())
                }
        }
    }
}