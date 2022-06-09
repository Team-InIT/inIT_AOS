package com.init_android.app.presentation.ui.feed

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.init_android.app.data.ServiceCreator
import com.init_android.app.data.request.RequestAddFeed
import com.init_android.app.data.request.RequestDeleteFeed
import com.init_android.app.data.response.ResponseBase
import com.init_android.app.data.response.ResponseFeed
import kotlinx.coroutines.launch

class FeedViewModel : ViewModel() {

    // 피드 목록 가져오가
    private val _feedList = MutableLiveData<ResponseFeed>()
    val feedList: LiveData<ResponseFeed>
        get() = _feedList

    // 피드 등록, 삭제
    private val _editFeed = MutableLiveData<ResponseBase>()
    val editFeed: LiveData<ResponseBase>
        get() = _editFeed

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
                    Log.d("feedList", "서버 통신 실패")
                }
        }
    }

    // 피드 등록
    fun postAddFeed(requestAddFeed: RequestAddFeed) {
        viewModelScope.launch {
            kotlin.runCatching { ServiceCreator.initService.postAddFeed(requestAddFeed) }
                .onSuccess {
                    _editFeed.value = it
                    Log.d("feedList", "서버 통신 성공")
                }
                .onFailure {
                    it.printStackTrace()
                    Log.d("feedList", "서버 통신 실패")
                }
        }
    }

    // 피드 삭제
    fun postDeleteFeed(requestDeleteFeed: RequestDeleteFeed) {
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
    }

}