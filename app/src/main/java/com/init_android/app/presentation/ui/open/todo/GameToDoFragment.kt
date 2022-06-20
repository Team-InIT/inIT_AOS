package com.init_android.app.presentation.ui.open.todo

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import com.init_android.R
import com.init_android.app.data.request.project.RequestProjectMember
import com.init_android.app.data.response.todo.ResponseAllToDo
import com.init_android.app.presentation.ui.open.todo.adapter.ToDoPlanAdapter
import com.init_android.app.presentation.ui.open.todo.viewmodel.ToDoViewModel
import com.init_android.databinding.FragmentGameToDoBinding
import com.playtogether_android.app.presentation.base.BaseFragment


class GameTodoFragment : BaseFragment<FragmentGameToDoBinding>(R.layout.fragment_game_to_do) {

    private val todoViewModel : ToDoViewModel by viewModels()
    private lateinit var toDoPlanAdapter: ToDoPlanAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }

    override fun onResume() {
        super.onResume()
        initNetwork()
        initCheckClick()
    }

    private fun initNetwork() {
        val requestProjectMember = RequestProjectMember(pNum = 1)
        todoViewModel.postReadGameToDo(requestProjectMember)
        toDoPlanAdapter = ToDoPlanAdapter()
        binding.rvGame.adapter = toDoPlanAdapter
        todoViewModel.readAllToDo.observe(viewLifecycleOwner) {
            toDoPlanAdapter.setQuestionPost((it.todoList) as MutableList<ResponseAllToDo.Todo>)
        }
    }

    private fun initCheckClick() {
        toDoPlanAdapter.setOnItemClickListener(object : ToDoPlanAdapter.onItemClickListener{
            override fun onItemClick(position: Int) {

            }

        })
    }
}