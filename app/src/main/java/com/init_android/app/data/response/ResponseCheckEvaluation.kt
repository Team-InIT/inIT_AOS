package com.init_android.app.data.response

data class ResponseCheckEvaluation(
    val code:Int,
    val evaluation: Evaluation
){
    data class Evaluation(
        val eNum:Int,
        val eRecommend:Int,
        val eComment:String
    )
}
