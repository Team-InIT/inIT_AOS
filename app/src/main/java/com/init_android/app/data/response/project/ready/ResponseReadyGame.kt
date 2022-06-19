package com.init_android.app.data.response.project.ready

data class ResponseReadyGame(
    val approvedGame: List<ApprovedGame>?,
    val code: Int,
    val waitingGame: List<WaitingGame>?
) {
    data class ApprovedGame(
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

    data class WaitingGame(
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