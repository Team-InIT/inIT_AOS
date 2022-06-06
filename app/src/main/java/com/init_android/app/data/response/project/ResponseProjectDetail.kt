package com.init_android.app.data.response.project

data class ResponseProjectDetail(
    val code: Int,
    val projectInfo: ProjectInfo,
    val writerInfo: WriterInfo
) {
    data class ProjectInfo(
        val mNum: Int,
        val pAcademic: Any,
        val pAos: Int,
        val pAosf: Int,
        val pDescription: String,
        val pDesign: Int,
        val pDesignf: Any,
        val pDue: String,
        val pGame: Int,
        val pGamef: Any,
        val pGender: Any,
        val pIos: Int,
        val pIosf: Any,
        val pNum: Int,
        val pOnOff: Int,
        val pPlan: Int,
        val pPlanf: Any,
        val pRecruitDue: String,
        val pRecruitStart: String,
        val pServer: Int,
        val pServerf: Int,
        val pStack: List<String>,
        val pStart: String,
        val pState: Int,
        val pTitle: String,
        val pType: Int,
        val pWeb: Int,
        val pWebf: Any
    )

    data class WriterInfo(
        val mName: String,
        val mNum: Int,
        val mPhoto: Any,
        val mPosition: Int
    )
}