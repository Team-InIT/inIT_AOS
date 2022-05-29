package com.init_android.app.data.model

import java.util.*

data class ProjectItemData (
    val pType:Int, // 타입
    val pTitle:String, // 제목
    val pOnOff:Int, // 위치
    val pTotalNum:Int, // 총인원
    val pStartDate:String, // 프로젝트 시작일
    val pEndDate: String, // 프로젝트 종료일
    val pWriter:String, // 작성자
    val pState:Int // 디데이 상태
)