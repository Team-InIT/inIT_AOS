package com.init_android.app.presentation.ui.open.todo.adapter

import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.init_android.app.presentation.ui.open.partner.PartnerCheckActivity
import com.init_android.app.presentation.ui.open.todo.ToDoMainActivity

class TodoTabAdapter(activity: ToDoMainActivity) : FragmentStateAdapter(activity){
    val fragments = mutableListOf<Fragment>()

    override fun getItemCount(): Int = fragments.size

    override fun createFragment(position: Int): Fragment = fragments[position]

}