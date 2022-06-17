package com.init_android.app.presentation.ui.open.todo

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import com.init_android.R
import com.init_android.app.data.request.project.RequestProjectMember
import com.init_android.app.data.response.todo.ResponseAllToDo
import com.init_android.app.presentation.ui.open.todo.adapter.ToDoPlanAdapter
import com.init_android.app.presentation.ui.open.todo.viewmodel.ToDoViewModel
import com.init_android.databinding.FragmentIosToDoBinding
import com.playtogether_android.app.presentation.base.BaseFragment


class IosTodoFragment : BaseFragment<FragmentIosToDoBinding>(R.layout.fragment_ios_to_do) {

    private val todoViewModel : ToDoViewModel by viewModels()
    private lateinit var toDoPlanAdapter: ToDoPlanAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }

    override fun onResume() {
        super.onResume()
        initNetwork()
    }

    private fun initNetwork() {
        val requestProjectMember = RequestProjectMember(pNum = 1)
        todoViewModel.postReadIosToDo(requestProjectMember)
        toDoPlanAdapter = ToDoPlanAdapter()
        binding.rvIos.adapter = toDoPlanAdapter
        todoViewModel.readAllToDo.observe(viewLifecycleOwner) {
            toDoPlanAdapter.setQuestionPost((it.todoList) as MutableList<ResponseAllToDo.Todo>)
        }
    }
}