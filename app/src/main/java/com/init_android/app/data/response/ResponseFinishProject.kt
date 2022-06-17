package com.init_android.app.data.response

data class ResponseFinishProject(
    val code: Int,
    var project: List<Project>
) {
    data class Project(
        val Recruits: List<Recruit>,
        val pNum: Int,
        val pTitle: String
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