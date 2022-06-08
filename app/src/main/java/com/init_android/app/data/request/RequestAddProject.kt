package com.init_android.app.data.request

import java.util.*

data class RequestAddProject(

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
    val pOnOff: Int,
    val pGender: Int?,
    val pAcademic: Int?,
    val pPlanf: Int?,
    val pDesignf: Int?,
    val pIosf: Int?,
    val pAosf: Int?,
    val pGamef: Int?,
    val pWebf: Int?,
    val pServerf: Int?,
    val mNum: Int,
    val pStack: String?,

)
