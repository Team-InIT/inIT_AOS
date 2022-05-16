package com.init_android.app.data.request

import java.util.*

data class RequestAddProject(

    val pTitle: String,
    val pType: Int,
    val pRdateStart: String,
    val pRdateDue: String,
    val pPdateStart: String,
    val pPdateDue: String,
    val pPlan: Int,
    val pDesign: Int,
    val pAndroid: Int,
    val pIos: Int,
    val pGame:Int,
    val pWeb: Int,
    val pServer: Int,
    val mNum: Int

)
