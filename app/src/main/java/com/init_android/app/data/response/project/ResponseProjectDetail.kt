package com.init_android.app.data.response.project

data class ResponseProjectDetail(
    val code: Int?,
    val detailInfo: DetailInfo?,
    val isApproval: Boolean?
) {
    data class DetailInfo(
        val projectInfo: ProjectInfo?,
        val projectState: String?,
        val writerInfo: WriterInfo?
    ) {
        data class ProjectInfo(
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
            val pStack: List<String>,
            val pStart: String?,
            val pState: Int?,
            val pTitle: String?,
            val pType: Int?,
            val pWeb: Int?,
            val pWebf: Int?
        )

        data class WriterInfo(
            val mName: String?,
            val mNum: Int?,
            val mPhoto: String?,
            val mPosition: Int?
        )
    }
}