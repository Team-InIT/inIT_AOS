package com.init_android.app.presentation.ui.feed

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.init_android.app.data.ServiceCreator
import com.init_android.app.data.request.RequestAddFeed
import com.init_android.app.data.request.RequestDeleteFeed
import com.init_android.app.data.request.RequestFeedDetail
import com.init_android.app.data.request.RequestFinishProject
import com.init_android.app.data.response.ResponseBase
import com.init_android.app.data.response.ResponseFeed
import com.init_android.app.data.response.ResponseFeedDetail
import com.init_android.app.data.response.ResponseFinishProject
import kotlinx.coroutines.launch
import okhttp3.MultipartBody
import okhttp3.RequestBody
import retrofit2.http.Part

class FeedViewModel : ViewModel() {

    // 피드 목록 가져오기
    private val _feedList = MutableLiveData<ResponseFeed>()
    val feedList: LiveData<ResponseFeed>
        get() = _feedList

    // 피드 등록
    private val _addFeed = MutableLiveData<ResponseBase>()
    val addFeed: LiveData<ResponseBase>
        get() = _addFeed

    // 완료 프로젝트 목록 가져오기
    private val _finishProjectList = MutableLiveData<ResponseFinishProject>()
    val finishProject: LiveData<ResponseFinishProject>
        get() = _finishProjectList

    // 피드 상세 보기
    private val _feedDetail = MutableLiveData<ResponseFeedDetail>()
    val feedDetail:LiveData<ResponseFeedDetail>
        get() = _feedDetail


    // 피드 상세 보기
    fun postDetailFeed(requestDetailFeed:RequestFeedDetail){
        viewModelScope.launch {
            kotlin.runCatching { ServiceCreator.initService.postDetailFeed(requestDetailFeed) }
                .onSuccess {
                    _feedDetail.value = it
                    Log.d("feedDetail","서버 통신 성공")
                }.onFailure {
                    it.printStackTrace()
                    Log.d("feedDetail","서버 통신 실패")
                    Log.d("feedDetail",it.printStackTrace().toString())
                }
        }
    }

    // 서버 통신(불러오기)
    fun getAllFeedList() {
        viewModelScope.launch {
            kotlin.runCatching { ServiceCreator.initService.getAllFeed() }
                .onSuccess {
                    _feedList.value = it
                    Log.d("feedList", "서버 통신 성공")
                }
                .onFailure {
                    it.printStackTrace()
                    Log.d("feedList", it.printStackTrace().toString())
                }
        }
    }

    // 피드 등록
    fun postAddFeed(@Part file: MultipartBody.Part,
                    @Part ("fTitle") fTitle: RequestBody,
                    @Part ("fDescription") fDescription: RequestBody,
                    @Part ("fLink") fLink: RequestBody,
                    @Part ("mNum") mNum: RequestBody,
                    @Part ("pNum") pNum: RequestBody,
                    @Part ("fType") fType: RequestBody
    ) {
        viewModelScope.launch {
            kotlin.runCatching {
                ServiceCreator.initService.postAddFeed(
                  file, fTitle,
                  fDescription, fLink, mNum, pNum, fType
                )
            }
                .onSuccess {
                    _addFeed.value = it
                    Log.d("addFeed", "서버 통신 성공")
                }
                .onFailure {
                    it.printStackTrace()
                    Log.d("addFeed", it.printStackTrace().toString())
                    Log.d("addFeed", it.message.toString())
                    Log.d("addFeed", "서버 통신 실패")
                }
        }
    }

    // 피드 삭제
    /*fun postDeleteFeed(requestDeleteFeed: RequestDeleteFeed) {
        viewModelScope.launch {
            kotlin.runCatching { ServiceCreator.initService.postDeleteFeed(requestDeleteFeed) }
                .onSuccess {
                    _editFeed.value = it
                    Log.d("feedList", "서버 통신 성공")
                }
                .onFailure {
                    it.printStackTrace()
                    Log.d("feedList", "서버 통신 실패")
                }
        }
    }*/

    // 프로젝트 목록 받아오기
    fun postFinishProject(requestFinishProject: RequestFinishProject) {
        viewModelScope.launch {
            kotlin.runCatching { ServiceCreator.initService.postFinishProject(requestFinishProject) }
                .onSuccess {
                    _finishProjectList.value = it
                    Log.d("finishProjectList", "서버 통신 성공")
                }
                .onFailure {
                    it.printStackTrace()
                    Log.d("finishProjectList", "서버 통신 실패")
                }
        }
    }

}