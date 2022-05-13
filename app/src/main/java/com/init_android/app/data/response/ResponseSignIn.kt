package com.init_android.app.data.response

import java.lang.reflect.Member

data class ResponseSignIn(
    val member: List<Member>,
    val message: String
)

data class Member(
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
