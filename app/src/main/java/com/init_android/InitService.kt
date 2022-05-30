package com.init_android

import com.init_android.app.data.request.*
import com.init_android.app.data.response.*
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
    ):Call<ResponseIdCheck>

    @POST("/signUp")
    fun postRegister(
        @Body body: RequestSignUp
    ):Call<ResponseSignUp>

    @POST("/addProject")
    fun postAddProject(
        @Body body: RequestAddProject
    ) : Call<ResponseAddProject>

    @POST("/home")
    suspend fun postHome(@Body body: RequestHome): ResponseHome

    @POST("/getRecommenedProject")
    suspend fun postRecoProject(@Body body: RequestRecoProject): ResponseRecoProject
}