package com.init_android.app.data.response

import java.util.*

data class ResponseSearchResult(
    val code: Int,
    val projectInfo: List<ProjectInfo>?
) {
    data class ProjectInfo(
        val pNum: Int,
        val pTitle: String,
        val pType: Int,
        val pRecruitStart: Date,
        val pRecruitDue: Date,
        val pStart: Date,
        val pDue: Date,
        val pPlan: Int,
        val pDesign: Int,
        val pIos: Int,
        val pAos: Int,
        val pGame: Int,
        val pWeb: Int,
        val pServer: Int,
        val pDescription: String,
        val pIosf: Int?,
        val pAosf: Int?,
        val pGamef: Int?,
        val pWebf: Int?,
        val pServerf: Int?,
        val pState: Int?,
        val pOnOff: Int,
        val mNum: Int,
        val Member: WriteMember
    ) {
        data class WriteMember(
            val mNum: Int,
            val mName: String
        )
    }
}