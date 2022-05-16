package com.init_android

import com.init_android.app.data.request.RequestIdCheck
import com.init_android.app.data.request.RequestSignIn
import com.init_android.app.data.request.RequestSignUp
import com.init_android.app.data.response.ResponseIdCheck
import com.init_android.app.data.response.ResponseSignIn
import com.init_android.app.data.response.ResponseSignUp
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
}