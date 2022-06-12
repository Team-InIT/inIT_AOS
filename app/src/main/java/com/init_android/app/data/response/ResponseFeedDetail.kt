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
                val Member: FMember,
                val img:Int,

                val fTest:String?,
                val fTimeStamp:String
        ){
                data class Photo(
                        val type:String,
                        val data: List<Int>
                )

                data class FMember(
                        val mNum: Int,
                        val mName: String,
                        val mPhoto: String?
                )
        }

}