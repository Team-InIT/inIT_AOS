package com.init_android.app.presentation.ui.main

import android.os.Bundle
import androidx.activity.viewModels
import com.init_android.R
import com.init_android.app.presentation.ui.alarm.AlarmFragment
import com.init_android.app.presentation.ui.feed.FeedFragment
import com.init_android.app.presentation.ui.home.HomeFragment
import com.init_android.app.presentation.ui.mypage.MyPageFragment
import com.init_android.app.presentation.ui.search.SearchFragment
import com.init_android.databinding.ActivityMainBinding
import com.playtogether_android.app.presentation.base.BaseActivity

class MainActivity : BaseActivity<ActivityMainBinding>(R.layout.activity_main) {

    private val mainViewModel : MainViewModel by viewModels()

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
                R.id.menu_feed -> {
                    supportFragmentManager.beginTransaction()
                        .replace(R.id.fragment_main, FeedFragment()).commit()
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
        val userId = intent.getIntExtra("userId", 1)
//        binding.fabWriting.setOnClickListener {
//            val intent = Intent(this@MainActivity, OpenProjectActivity::class.java)
//            intent.putExtra("userId", userId)
//            startActivity(intent)
//        }
        mainViewModel.signData.observe(this) {
            it.mNum = userId
        }
    }
}