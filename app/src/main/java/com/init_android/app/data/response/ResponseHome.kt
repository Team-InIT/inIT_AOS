package com.init_android.app.data.response

import java.util.*

data class ResponseHome (
    val code:Int,
    val list_belong:MutableList<BelongData>?,
    val list_recommend:MutableList<RecommendData>?
    )

data class BelongData(
        val pNum:Int?,
        val pTitle:String?,
        val pType:Int?,
        val pRecruitStart: Date?,
        val pRecruitDue:Date?,
        val pStart:Date?,
        val pDue:Int?,
        val pPlan:Int?,
        val pDesign:Int?,
        val pIos:Int?,
        val pGame:Int?,
        val pWeb:Int?,
        val pServer:String?,
        val pDescription:Int?,
        val pOnOff:Int?,
        val pAcademic:Int?,
        val pGender:Int?,
        val pPlanf:Int?,
        val pDesignf:Int?,
        val pIosf:Int?,
        val pAosf:Int?,
        val pGamef:Int?,
        val pWebf:Int?,
        val pServerf:Int?,
        val pState:Int?,
        val mNum:Int?,
        val Recruits:List<Recruits>?
        )

data class Recruits(
        val mNum:Int,
        val pNum:Int,
        val rApproval:Int,
)

data class RecommendData(
        val pNum:Int?,
        val pTitle:String?,
        val pType:Int?,
        val pRecruitStart: Date?,
        val pRecruitDue:Date?,
        val pStart:Date?,
        val pDue:Int?,
        val pPlan:Int?,
        val pDesign:Int?,
        val pIos:Int?,
        val pGame:Int?,
        val pWeb:Int?,
        val pServer:String?,
        val pDescription:Int?,
        val pOnOff:Int?,
        val pAcademic:Int?,
        val pGender:Int?,
        val pPlanf:Int?,
        val pDesignf:Int?,
        val pIosf:Int?,
        val pAosf:Int?,
        val pGamef:Int?,
        val pWebf:Int?,
        val pServerf:Int?,
        val pState:Int?,
        val mNum:Int?,
        val Recruits:List<Recruits>?
)


