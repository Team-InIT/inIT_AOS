package com.init_android.app.data.response.project.approve

data class ResponseProjectMember(
    val approvedAos: List<Any>?,
    val approvedDesign: List<Any>?,
    val approvedGame: List<Any>?,
    val approvedIos: List<Any>?,
    val approvedPlan: List<ApprovedPlan>?,
    val approvedServer: List<Any>?,
    val approvedWeb: List<Any>?,
    val code: Int
) {
    data class ApprovedPlan(
        val Recruits: List<Recruit>,
        val mEmail: String,
        val mName: String,
        val mNum: Int
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
