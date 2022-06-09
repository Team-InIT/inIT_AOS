package com.init_android.app.data.request

import okhttp3.MultipartBody
import okhttp3.RequestBody
import retrofit2.http.Part

/*data class RequestAddFeed (
    val fTitle:String,
    val fType:Int,
    val fDescription:String,
    val fLink:String,
    val mNum:Int,
    val pNum:Int
    )
*/

data class RequestAddFeed(
    @Part val file: MultipartBody.Part,
    @Part ("fTitle") val fTitle: RequestBody,
    @Part ("fDescription") val fDescription: RequestBody,
    @Part ("fLink") val fLink: RequestBody,
    @Part ("mNum") val mNum: RequestBody,
    @Part ("pNum") val pNum: RequestBody,
    @Part ("fType")val fType: RequestBody
)