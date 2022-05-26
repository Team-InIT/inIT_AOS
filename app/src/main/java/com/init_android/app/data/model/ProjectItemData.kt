package com.init_android.app.data.model

data class ProjectItemData (
    val pType:String, // 포지션
    val pTitle:String, // 제목
    val pLocation:String, // 위치
    val pTotalNum:String, // 모집인원 (현재인원/총인원-구분필요?)
    val pStartDate:String, // 프로젝트 시작일
    val pEndDate:String, // 프로젝트 종료일
    val pWriter:String, // 작성자
    val pDDay:String // 디데이
)