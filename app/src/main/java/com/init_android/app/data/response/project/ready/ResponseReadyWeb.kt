package com.init_android.app.data.response.project.ready

data class ResponseReadyWeb(
    val approvedWeb: List<ApprovedWeb>?,
    val code: Int?,
    val waitingWeb: List<WaitingWeb>?
) {
    data class ApprovedWeb(
        val Recruits: List<Recruit>?,
        val mEmail: String?,
        val mName: String?,
        val mNum: Int?,
        val mPhoto: String?
    ) {
        data class Recruit(
            val mNum: Int?,
            val pNum: Int?,
            val rApproval: Int?,
            val rNum: Int?,
            val rPosition: Int?
        )
    }

    data class WaitingWeb(
        val Recruits: List<Recruit>?,
        val mEmail: String?,
        val mName: String?,
        val mNum: Int?,
        val mPhoto: String?
    ) {
        data class Recruit(
            val mNum: Int?,
            val pNum: Int?,
            val rApproval: Int?,
            val rNum: Int?,
            val rPosition: Int?
        )
    }
}