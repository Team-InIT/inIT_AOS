package com.init_android.app.data.response

data class ResponseFeedDetail (
        val code:Int,
        val feedInfo:FeedInfo
        ){
        data class FeedInfo(
                val fNum:Int,
                val fTitle:String,
                val fType:Int,
                val fPhoto:Photo,
                val fDescription:String,
                val fLink:String?,
                val mNum:Int,
                val pNum:Int,
                val member: Member,
                val img:Int
        ){
                data class Photo(
                        val type:String,
                        val data: List<Int>
                )

                data class Member(
                        val mNum: Int,
                        val mName: Int,
                        val mPhoto: Photo?
                )
        }

}