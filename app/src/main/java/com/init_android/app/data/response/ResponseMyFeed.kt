package com.init_android.app.data.response

data class ResponseMyFeed (
    val code:Int,
    val Feeds:List<MyFeeds>?
        ){
    data class MyFeeds(
        val fNum:Int?,
        val fTitle:String?,
        val fTest:String?
    )
}