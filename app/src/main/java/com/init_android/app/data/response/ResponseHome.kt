package com.init_android.app.data.response

import java.util.*

data class ResponseHome (
    val code:Int,
    val list_belong:MutableList<ProjectData>?,
    val list_recommend:MutableList<ProjectData>?,
    val writerInfo_belong:MutableList<MutableList<WriterInfo>?>?,
    val writerInfo_recommend:MutableList<MutableList<WriterInfo>?>?
    )

data class ProjectData(
        val mNum: Int?,
        val pAcademic: Int?,
        val pAos: Int?,
        val pAosf: Int?,
        val pDescription: String?,
        val pDesign: Int?,
        val pDesignf: Int?,
        val pDue: Date?,
        val pGame: Int?,
        val pGamef: Int?,
        val pGender: Int?,
        val pIos: Int?,
        val pIosf: Any?,
        val pNum: Int?,
        val pOnOff: Int?,
        val pPlan: Int?,
        val pPlanf: Int?,
        val pRecruitDue: Date?,
        val pRecruitStart: Date?,
        val pServer: Int?,
        val pServerf: Int?,
        val pStart: Date?,
        val pState: Int?,
        val pTitle: String?,
        val pType: Int?,
        val pWeb: Int?,
        val pWebf: Int?,
        val Recruits:MutableList<Recruits>?
)

data class Recruits(
        val mNum:Int?,
        val pNum:Int?,
        val rApproval:Int?
)

data class WriterInfo(
        val mNum:Int?,
        val mName:String?
)