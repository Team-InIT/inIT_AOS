package com.init_android.app.data.response.todo

data class ResponseToDoMember(
    val code: Int,
    val members: List<Member>
) {
    data class Member(
        val Recruits: List<Recruit>,
        val mName: String,
        val mNum: Int,
        val mPhoto: String?
    ) {
        data class Recruit(
            val mNum: Int,
            val pNum: Int,
            val rApproval: Int,
            val rNum: Int,
            val rPosition: Int
        )
    }
}