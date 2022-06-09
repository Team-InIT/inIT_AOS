package com.init_android.app.data.response.mypage

data class test(
    val code: Int,
    val mInfo: MInfo
) {
    data class MInfo(
        val mAcademic: Int,
        val mApproval: Int,
        val mBlog: Any,
        val mDept: String,
        val mEmail: String,
        val mGender: Int,
        val mGit: Any,
        val mID: String,
        val mIntroduction: String,
        val mLevel: Int,
        val mName: String,
        val mNotion: Any,
        val mNum: Int,
        val mPW: String,
        val mPhoto: MPhoto,
        val mPosition: Int,
        val mStacks: List<String>
    ) {
        data class MPhoto(
            val `data`: List<Int>,
            val type: String
        )
    }
}