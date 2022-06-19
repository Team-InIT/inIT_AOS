package com.init_android.app.data.response.mypage

data class teset(
    val code: Int,
    val myEvaluations: List<MyEvaluation>,
    val writer: List<Writer>
) {
    data class MyEvaluation(
        val projectInfo: ProjectInfo,
        val eComment: String,
        val eNum: Int,
        val mNum: Int
    ) {
        data class ProjectInfo(
            val pNum: Int,
            val pTitle: String
        )
    }

    data class Writer(
        val mName: String,
        val mNum: Int,
        val mPhoto: String
    )
}