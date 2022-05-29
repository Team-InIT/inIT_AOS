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
        val pNum:Int?,
        val pTitle:String?,
        val pType:Int?,
        val pRecruitStart:Date?,
        val pRecruitDue:Date?,
        val pStart:Date?,
        val pDue:Date?,
        val pPlan:Int?,
        val pDesign:Int?,
        val pIos:Int?,
        val pAos:Int?,
        val pGame:Int?,
        val pWeb:Int?,
        val pServer:Int?,
        val pDescription:String?,
        val pOnOff:Int?,
        val pGender:Int?,
        val pAcademic:Int?,
        val pPlanf:Int?,
        val pIosf:Int?,
        val pAosf:Int?,
        val pGamef:Int?,
        val pWebf:Int?,
        val pServerf:Int?,
        val pState:Int?,
        val mNum:Int?,
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