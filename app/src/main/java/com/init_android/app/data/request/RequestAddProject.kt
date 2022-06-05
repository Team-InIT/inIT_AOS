package com.init_android.app.data.request

import java.util.*

data class RequestAddProject(

    val pTitle: String,
    val pType: Int,
    val pRecruitStart : Date,
    val pRdateStart: Date,
    val pRdateDue: Date,
    val pPdateStart: Date,
    val pPdateDue: Date,
    val pPlan: Int,
    val pDesign: Int,
    val pAndroid: Int,
    val pIos: Int,
    val pGame:Int,
    val pWeb: Int,
    val pServer: Int,
    val mNum: Int,
    val pStack : String

)
