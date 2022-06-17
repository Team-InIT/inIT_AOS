package com.init_android.app.data.response.project.approve

data class ResponseProjectMember(
    val approvedAos: List<ApprovedAo>?,
    val approvedDesign: List<ApprovedDesign>?,
    val approvedGame: List<ApprovedGame>?,
    val approvedIos: List<ApprovedIo>?,
    val approvedPlan: List<ApprovedPlan>?,
    val approvedServer: List<ApprovedServer>?,
    val approvedWeb: List<ApprovedWeb>?,
    val code: Int
) {
    data class ApprovedPlan(
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

    data class ApprovedDesign(
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

    data class ApprovedAo(
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

    data class ApprovedIo(
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

    data class ApprovedGame(
        val Recruits: List<Recruit>,
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

    data class ApprovedWeb(
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
}
