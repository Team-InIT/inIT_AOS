package com.init_android.app.data.response

data class ResponseGetUserAll(
    val code:Int,
    val userList:List<UserList>
)

data class UserList(
    val mNum:Int,
    val mName:String,
    val mPosition:Int,
    val mIntroduction:String?,
    val mPhoto:String?
)