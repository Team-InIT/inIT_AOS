package com.init_android.app.presentation.ui

import android.content.Intent
import android.os.Bundle
import com.init_android.R
import com.init_android.app.presentation.ui.alarm.AlarmFragment
import com.init_android.app.presentation.ui.home.HomeFragment
import com.init_android.app.presentation.ui.mypage.MyPageFragment
import com.init_android.app.presentation.ui.oepn.project.OpenProjectActivity
import com.init_android.app.presentation.ui.search.SearchFragment
import com.init_android.databinding.ActivityMainBinding
import com.playtogether_android.app.presentation.base.BaseActivity

class MainActivity : BaseActivity<ActivityMainBinding>(R.layout.activity_main) {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initBottomNavi()
        settingId()
    }

    private fun initBottomNavi() {
        supportFragmentManager.beginTransaction().add(R.id.fragment_main, HomeFragment()).commit()

        binding.btnMain.setOnItemSelectedListener {
            when (it.itemId) {
                R.id.menu_home -> {
                    supportFragmentManager.beginTransaction()
                        .replace(R.id.fragment_main, HomeFragment()).commit()
                }
                R.id.menu_search -> {
                    supportFragmentManager.beginTransaction()
                        .replace(R.id.fragment_main, SearchFragment()).commit()
                }
                R.id.menu_alarm -> {
                    supportFragmentManager.beginTransaction()
                        .replace(R.id.fragment_main, AlarmFragment()).commit()
                }
                R.id.menu_mypage -> {
                    supportFragmentManager.beginTransaction()
                        .replace(R.id.fragment_main, MyPageFragment()).commit()
                }
            }
            return@setOnItemSelectedListener true
        }
    }

    //userId저장
    private fun settingId() {
        val userId = intent.getIntExtra("userId", 0)
        binding.fabWriting.setOnClickListener {
            val intent = Intent(this@MainActivity, OpenProjectActivity::class.java)
            intent.putExtra("userId", userId)
            startActivity(intent)
        }
    }
}