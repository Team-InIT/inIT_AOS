package com.init_android.app.data.response.todo

data class ResponseAllToDo(
    val code: Int,
    val todoList: List<Todo>
) {
    data class Todo(
        val members: List<Member>,
        val todoInfo: TodoInfo
    ) {
        data class Member(
            val mNum: Int,
            val mPhoto: String
        )

        data class TodoInfo(
            val mNums: String,
            val pNum: Int,
            val tDday: String,
            val tNum: Int,
            val tPart: Int,
            val tState: Int,
            val tTodo: String,
            val tWritedate: String
        )
    }
}