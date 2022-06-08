package com.init_android.app.presentation.ui.home.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.CoroutineExceptionHandler

class mainViewModel : ViewModel() {
    var text = MutableLiveData<String>()
    var part = MutableLiveData<String>()
    var partNum = MutableLiveData<Int>()
    var project = MutableLiveData<String>()
}
