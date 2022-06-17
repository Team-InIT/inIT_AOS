package com.init_android.app.data.response

import com.init_android.app.data.Belong

class ResponseRecoProject(
    val list_recommend:List<Recommend>,
){
    data class Recommend(
        val pInfo:Belong,
        val projectState:String
    )
}