package com.init_android.app.data.response

data class ResponseAlreadyEvaluate(
    val code:Int,
    val members:List<EvaluateMembers>?
){
    data class EvaluateMembers(
        val mNum:Int,
        val mName:String,
        val mPhoto:String?,
        val mPosition:Int
    )
}
