package com.init_android.app.data.response

data class HomeTestData(
    val code: Int,
    val list_belong: List<Belong>,
    val list_recommend: List<Recommend>,
    val writerInfo_belong: List<List<WriterInfoBelong>>,
    val writerInfo_recommend: List<List<WriterInfoRecommend>>
)