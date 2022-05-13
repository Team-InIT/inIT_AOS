package com.init_android.app.data.request

import com.google.gson.annotations.SerializedName

data class RequestSignIn(
    @SerializedName("mID") val id: String,
    @SerializedName("mPW")val pw: String
)
