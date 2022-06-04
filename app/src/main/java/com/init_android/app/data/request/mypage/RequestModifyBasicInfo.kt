package com.init_android.app.data.request.mypage

data class RequestModifyBasicInfo(
    val mNum: Int,
    val mEmail: String,
    val mDept: String,
    val mAcademic: Int,
    val mGender: Int
)