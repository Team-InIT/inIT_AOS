package com.init_android.app.data.response

data class ResponseGetNotRecruitingProject(
    val code: Int,
    val notRecruitingProject: List<RecruitingProject>,
    val writer: List<Writer>
) {
    data class RecruitingProject(
        val pNum: Int,
        val pTitle: String,
        val pType: Int,
        val pRecruitStart: String,
        val pRecruitDue: String,
        val pStart: String,
        val pDue: String,
        val pPlan: Int?,
        val pDesign: Int?,
        val pIos: Int?,
        val pAos: Int?,
        val pGame: Int?,
        val pWeb: Int?,
        val pServer: Int?,
        val pDescription: String,
        val pIosf: Int?,
        val pAosf: Int?,
        val pGamef: Int?,
        val pWebf: Int?,
        val pServerf: Int?,
        val pState: Int?,
        val pOnOff: Int,
        val mNum:Int,
        // val pStack:String
    )

    data class Writer(
        val mNum: Int,
        val mName: String
    )
}