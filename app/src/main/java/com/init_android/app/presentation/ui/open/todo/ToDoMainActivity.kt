package com.init_android.app.presentation.ui.open.todo

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.google.android.material.tabs.TabLayoutMediator
import com.init_android.R
import com.init_android.app.presentation.ui.mypage.MyPageSettingActivity
import com.init_android.app.presentation.ui.open.partner.*
import com.init_android.app.presentation.ui.open.partner.adapter.PartnerTabAdapter
import com.init_android.app.presentation.ui.open.todo.adapter.TodoTabAdapter
import com.init_android.databinding.ActivityToDoMainBinding
import com.playtogether_android.app.presentation.base.BaseActivity

class ToDoMainActivity : BaseActivity<ActivityToDoMainBinding>(R.layout.activity_to_do_main) {

    private lateinit var todoTabAdapter: TodoTabAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        initAdapter()
        backBtnClickListener()
        writeBtnListener()
    }

    //뒤로가기 버튼 클릭 리스너
    private fun backBtnClickListener() {
        binding.ivOpenProjectBack.setOnClickListener {
            finish()
        }
    }

    private fun writeBtnListener() {
        binding.btnTodoAdd.setOnClickListener {
            val intent = Intent(this, WriteToDoActivity::class.java)
            startActivity(intent)
        }
    }

    private fun initAdapter() {
        val fragmentList = listOf(
            PlanFragment(),
            DesignFragment(),
            WebFragment(),
            AosFragment(),
            IosFragment(),
            GameFragment(),
            ServerFragment()
        )
        todoTabAdapter = TodoTabAdapter(this)
        todoTabAdapter.fragments.addAll(fragmentList)

        binding.vpTodoTab.adapter = todoTabAdapter
    }

    private fun initTab() {
        val tabLabel = listOf("기획", "디자인", "웹", "AOS", "IOS", "게임", "서버")
        TabLayoutMediator(binding.tlPartnerTab, binding.vpTodoTab) { tab, position ->
            tab.text = tabLabel[position]
        }.attach()
    }
}