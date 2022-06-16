package com.init_android.app.presentation.ui.open.team

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.init_android.app.data.request.RequestAddReview

class TeamReviewViewModel : ViewModel() {
    // 이름 정보 (아마 pNum 으로 넘겨줄 수도 있을듯)
    var selectedName = MutableLiveData<String>()
    /*private var _selectedName = MutableLiveData<String>()
    val selectedName: LiveData<String>
        get() = _selectedName*/

    // 넘겨줄 정보 (서버가 주는 값에 따라 변경될 예정)
    private val _postReviewData = MutableLiveData<RequestAddReview>()
    val postReviewData: LiveData<RequestAddReview>
        get() = _postReviewData

}