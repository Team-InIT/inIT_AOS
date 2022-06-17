package com.init_android.app.data.request.todo

import java.util.*

data class RequestWriteToDo(
    val mNums: Int,
    val pNum: Int,
    val tDday: String,
    val tPart: Int,
    val tTodo: String
)