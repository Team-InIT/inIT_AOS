package com.init_android.app.presentation.ui.open.todo

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.init_android.R
import com.init_android.app.data.request.project.RequestProjectMember
import com.init_android.app.data.response.todo.ResponseAllToDo
import com.init_android.app.presentation.ui.open.todo.adapter.ToDoPlanAdapter
import com.init_android.app.presentation.ui.open.todo.viewmodel.ToDoViewModel
import com.init_android.databinding.FragmentAosToDoBinding
import com.init_android.databinding.FragmentPlanToDoBinding
import com.playtogether_android.app.presentation.base.BaseFragment


class AosToDoFragment : BaseFragment<FragmentAosToDoBinding>(R.layout.fragment_aos_to_do) {

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
        todoViewModel.postReadAosToDo(requestProjectMember)
        toDoPlanAdapter = ToDoPlanAdapter()
        binding.rvAos.adapter = toDoPlanAdapter
        todoViewModel.readAllToDo.observe(viewLifecycleOwner) {
            toDoPlanAdapter.setQuestionPost((it.todoList) as MutableList<ResponseAllToDo.Todo>)
        }
    }
}