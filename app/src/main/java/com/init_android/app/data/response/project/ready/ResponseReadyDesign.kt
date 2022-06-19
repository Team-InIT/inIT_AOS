package com.init_android.app.data.response.project.ready

data class ResponseReadyDesign(
    val approvedDesign: List<ApprovedDesign>?,
    val code: Int,
    val waitingDesign: List<WaitingDesign>?
) {
    data class ApprovedDesign(
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

    data class WaitingDesign(
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