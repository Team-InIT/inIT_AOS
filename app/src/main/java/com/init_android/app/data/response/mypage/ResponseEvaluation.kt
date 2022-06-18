package com.init_android.app.data.response.mypage

data class ResponseEvaluation(
    val code: Int,
    val myEvaluation: List<MyEvaluation>
) {
    data class MyEvaluation(
        val evaluation: Evaluation,
        val writer: Writer
    ) {
        data class Evaluation(
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
}