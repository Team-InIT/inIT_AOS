package com.init_android.app.data.response.project.approve

data class ResponsemyWaitingApproval(
    val code: Int?,
    val projectInfoList: List<ProjectInfo>?,
    val writer: List<Writer>?
) {
    data class ProjectInfo(
        val Recruits: List<Recruit>?,
        val mNum: Int?,
        val pAcademic: Int?,
        val pAos: Int?,
        val pAosf: Int?,
        val pDescription: String?,
        val pDesign: Int?,
        val pDesignf: Int?,
        val pDue: String?,
        val pGame: Int?,
        val pGamef: Int?,
        val pGender: Int?,
        val pIos: Int?,
        val pIosf: Int?,
        val pNum: Int?,
        val pOnOff: Int?,
        val pPlan: Int?,
        val pPlanf: Int?,
        val pRecruitDue: String?,
        val pRecruitStart: String?,
        val pServer: Int?,
        val pServerf: Int?,
        val pStack: String?,
        val pStart: String?,
        val pState: Int?,
        val pTitle: String?,
        val pType: Int?,
        val pWeb: Int?,
        val pWebf: Int?
    ) {
        data class Recruit(
            val mNum: Int?,
            val pNum: Int?,
            val rApproval: Int?,
            val rNum: Int?,
            val rPosition: Int?
        )
    }

    data class Writer(
        val mName: String?,
        val mNum: Int?
    )
}