package com.init_android.app.data

import com.init_android.app.data.request.*
import com.init_android.app.data.request.mypage.RequestModifyBasicInfo
import com.init_android.app.data.request.mypage.RequestModifyLink
import com.init_android.app.data.request.mypage.RequestMyInfo
import com.init_android.app.data.request.mypage.RequestQuit
import com.init_android.app.data.response.*
import com.init_android.app.data.response.mypage.ResponseModifyBasicInfo
import com.init_android.app.data.response.mypage.ResponseModifyLink
import com.init_android.app.data.response.mypage.ResponseMyInfo
import com.init_android.app.data.response.mypage.ResponseQuit
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST

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
    fun postAddProject(
        @Body body: RequestAddProject
    ): Call<ResponseAddProject>

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
    suspend fun postQuit(@Body body: RequestQuit) : ResponseQuit
}