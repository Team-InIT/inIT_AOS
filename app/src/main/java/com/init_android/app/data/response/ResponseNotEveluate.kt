package com.init_android.app.data.response

data class ResponseNotEveluate(
    val code:Int,
    val memberToEvaluate:List<MemberToEvaluate>?
){
    data class MemberToEvaluate(
        val mNum:Int,
        val mName:String,
        val mPosition:Int,
        val mPhoto:String?,
    )
}
