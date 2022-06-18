package com.init_android.app.data.response

import com.init_android.app.data.Belong

data class ResponseGetNotRecruitingProject(
    val code: Int,
    val projectInfo: List<RecruitingProjectData>?,
) {
    data class RecruitingProjectData(
        val notRecruitingProject: Belong,
        val projectState:String
    )
}