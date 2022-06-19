package com.init_android.app.data.response.project.ready

data class ResponseReadyServer(
    val approvedPlan: List<ApprovedServer>,
    val code: Int,
    val waitingPlan: List<WaitingServer>
) {
    data class ApprovedServer(
        val Recruits: List<Recruit>,
        val mEmail: String,
        val mName: String,
        val mNum: Int,
        val mPhoto: String
    ) {
        data class Recruit(
            val mNum: Int,
            val pNum: Int,
            val rApproval: Int,
            val rNum: Int,
            val rPosition: Int
        )
    }

    data class WaitingServer(
        val Recruits: List<Recruit>,
        val mEmail: String,
        val mName: String,
        val mNum: Int,
        val mPhoto: Any
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