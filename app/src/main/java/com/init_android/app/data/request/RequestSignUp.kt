package com.init_android.app.data.request

data class RequestSignUp(
    val mNum:Int,
    val mType:Int,
    val mID:String,
    val mName:String,
    val mEmail:String,
    val mDept:String,
    val mAcademic:String,
    val mGender:Int,
    val mPosition:Int,
    val mLevel:Int,
    val mApproval:Int,
    val mIntroduction:String
)
