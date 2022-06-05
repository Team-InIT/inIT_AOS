package com.init_android.app.util

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.CoroutineExceptionHandler

class mainViewModel : ViewModel() {


 var text = MutableLiveData<String>()
 var firstMajor = MutableLiveData<String>()
 var part =  MutableLiveData<String>()
 var secondMajor =  MutableLiveData<String>()
 var secondMajorPeriod =  MutableLiveData<String>()
 var deviceToken = MutableLiveData<String>()


}
