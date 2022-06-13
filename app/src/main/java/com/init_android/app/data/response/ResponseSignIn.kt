package com.init_android.app.data.response


data class ResponseSignIn(
    val member: List<Member>,
    val message: String,
    val code:Int
)

data class Member(
    var mNum:Int,
    val mType:Int,
    val mID:String,
    val mPW:String,
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
