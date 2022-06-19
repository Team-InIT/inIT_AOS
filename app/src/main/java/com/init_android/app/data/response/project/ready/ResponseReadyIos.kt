package com.init_android.app.data.response.project.ready

data class ResponseReadyIos(
    val approvedIos: List<ApprovedIos>?,
    val code: Int,
    val waitingIos: List<WaitingIos>?
) {
    data class ApprovedIos(
        val Recruits: List<Recruit>?,
        val mEmail: String,
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

    data class WaitingIos(
        val Recruits: List<Recruit>?,
        val mEmail: String,
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