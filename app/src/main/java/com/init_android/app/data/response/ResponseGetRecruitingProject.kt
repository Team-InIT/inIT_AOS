package com.init_android.app.data.response

import com.init_android.app.data.Belong
import java.util.*

data class ResponseGetRecruitingProject(
    val code: Int,
    val projectInfo: List<RecruitingProjectData>?,
) {
    data class RecruitingProjectData(
        val recruitingProject: Belong,
        val projectState:String
    )
}