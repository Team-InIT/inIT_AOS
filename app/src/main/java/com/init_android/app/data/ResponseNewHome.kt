package com.init_android.app.data

import java.util.*

data class ResponseNewHome(
    val list_join:List<Join>,
    val list_recommend:List<Recommend>
)

data class Join(
    val list_belong:Belong,
    val projectState:String
)

data class Recommend(
     val listRecommend:Belong,
     val projectState2:String
)

data class Belong(
    val mNum: Int?,
    val pAcademic: Int?,
    val pAos: Int,
    val pAosf: Int?,
    val pDescription: String?,
    val pDesign: Int,
    val pDesignf: Int?,
    val pDue: Date,
    val pGame: Int,
    val pGamef: Int?,
    val pGender: Int?,
    val pIos: Int,
    val pIosf: Any?,
    val pNum: Int?,
    val pOnOff: Int?,
    val pPlan: Int,
    val pPlanf: Int?,
    val pRecruitDue: Date?,
    val pRecruitStart: Date?,
    val pServer: Int,
    val pServerf: Int?,
    val pStart: Date?,
    val pState: Int?,
    val pTitle: String?,
    val pType: Int?,
    val pWeb: Int,
    val pWebf: Int?,
    val Member:HomeMember
){
    data class HomeMember(
        val mNum:Int,
        val mName:String
    )
}