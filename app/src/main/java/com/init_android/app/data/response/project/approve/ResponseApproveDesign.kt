package com.init_android.app.data.response.project.approve

data class ResponseApproveDesign(
    val approvedPlan: List<ApprovedDesign>,
    val code: Int
) {
    data class ApprovedDesign(
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