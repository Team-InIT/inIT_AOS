package com.init_android.app.data.response

data class ResponseFeed(
    val code:Int,
    val feeds:List<Feed>
){
    data class Feed(
        val fNum:Int,
        val fTitle:String,
        val fPhoto:Any?,
    )
}