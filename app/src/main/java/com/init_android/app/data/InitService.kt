package com.init_android.app.data

import com.init_android.app.data.request.*
import com.init_android.app.data.request.mypage.*
import com.init_android.app.data.request.project.RequestApplyProject
import com.init_android.app.data.request.project.RequestProjectDetail
import com.init_android.app.data.response.*
import com.init_android.app.data.response.mypage.*
import com.init_android.app.data.response.project.ResponseApplyProject
import com.init_android.app.data.response.project.ResponseProjectDetail
import okhttp3.MediaType
import okhttp3.MultipartBody
import okhttp3.RequestBody
import retrofit2.Call
import retrofit2.http.*

interface InitService {
    @POST("/login")
    fun postLogin(
        @Body body: RequestSignIn
    ): Call<ResponseSignIn>

    @POST("/idCheck")
    fun postIdCheck(
        @Body body: RequestIdCheck
    ): Call<ResponseIdCheck>

    @POST("/signUp")
    fun postRegister(
        @Body body: RequestSignUp
    ): Call<ResponseSignUp>

    @POST("/addProject")
    suspend fun postAddProject(
        @Body body: RequestAddProject
    ): ResponseAddProject

    @POST("/home")
    suspend fun postHome(@Body body: RequestHome): ResponseHome


    @POST("/myPage")
    suspend fun postMyPageInfo(@Body body: RequestMyInfo): ResponseMyInfo

    @POST("/getRecommenedProject")
    suspend fun postRecoProject(@Body body: RequestRecoProject): ResponseRecoProject

    //링크 수정 서버통신
    @POST("/updateLink")
    suspend fun postModifyLink(@Body body: RequestModifyLink): ResponseModifyLink

    //기본 정보 수정 서버 통신
    @POST("/editBasicInfo")
    suspend fun postModifyBasicInfo(@Body body: RequestModifyBasicInfo): ResponseModifyBasicInfo

    //회원 탈퇴 서버통신
    @POST("/withdraw")
    suspend fun postQuit(@Body body: RequestQuit): ResponseQuit


    //내 정보수정 서버통신
    @Multipart
    @POST("/updateProfile")
    suspend fun postUpdateProfile(
        @Part file: MultipartBody.Part,
        @Part("mNum") mNum : RequestBody,
        @Part("mName") mName : RequestBody,
        @Part("mPosition") mPosition: RequestBody,
        @Part("mLevel") mLevel: RequestBody,
        @Part("mIntroduction") mIntroduction : RequestBody
    ) : ResponseUpdateProfile


    //스택 수정 서버통신


    //마이페이지 프로젝트 갯수 서버통신
    @POST("/countProject")
    suspend fun postCountProject(@Body body: RequestCountProject): ResponseCountProject

    //피드 참여한 프로젝트 바텀시트
    @POST("/finishedProject")
    suspend fun postFinishProject(@Body body: RequestFinishProject): ResponseFinishProject

    //프로젝트 상세보기
    @POST("/detailProject")
    suspend fun postProjectDetail(@Body body: RequestProjectDetail): ResponseProjectDetail

    //프로젝트 지원하기
    @POST("/apply")
    suspend fun postApplyProject(@Body body: RequestApplyProject): ResponseApplyProject

    // 피드 리스트 보기
    @GET("/getAllFeed")
    suspend fun getAllFeed(): ResponseFeed

    // 피드 등록
    @Multipart
    @POST("/addFeed")
    suspend fun postAddFeed(
        @Part file: MultipartBody.Part,
        @Part ("fTitle") fTitle: RequestBody,
        @Part ("fDescription") fDescription: RequestBody,
        @Part ("fLink") fLink: RequestBody,
        @Part ("mNum") mNum: RequestBody,
        @Part ("pNum") pNum: RequestBody,
        @Part ("fType") fType: RequestBody
    ): ResponseBase

    // 피드 삭제
    @POST("/deleteFeed")
    suspend fun postDeleteFeed(@Body body: RequestDeleteFeed): ResponseBase
}