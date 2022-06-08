package com.init_android.app.data.response.mypage

data class ResponseMyInfo(
    val code: Int,
    val mInfo: MInfo,
    val stacks: List<String>
) {
    data class MInfo(
        val mAcademic: Int,
        val mApproval: Int,
        val mBlog: String,
        val mDept: String,
        val mEmail: String,
        val mGender: Int,
        val mGit: String,
        val mID: String,
        val mIntroduction: String,
        val mLevel: Int,
        val mName: String,
        val mNotion: String,
        val mNum: Int,
        val mPW: String,
        val mPhoto: Any,
        val mPosition: Int
    )
}