package com.init_android.app.presentation.ui.mypage

import android.content.Intent
import android.os.Bundle
import android.view.View
import com.google.android.material.tabs.TabLayoutMediator
import com.init_android.R
import com.init_android.app.presentation.ui.mypage.adapter.MyPageTabAdapter
import com.init_android.databinding.FragmentMyPageBinding
import com.playtogether_android.app.presentation.base.BaseFragment


class MyPageFragment : BaseFragment<FragmentMyPageBinding>(R.layout.fragment_my_page) {
    private lateinit var myPageTabAdapter: MyPageTabAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initAdapter()
        initTab()
        settingBtnListener()
    }

    private fun initAdapter() {
        val fragmentList = listOf(MyPageInfoFragment(), MyPageProjectFragment(),MyPageFeedFragment(),MyPageRateFragment())
        myPageTabAdapter = MyPageTabAdapter(this)
        myPageTabAdapter.fragments.addAll(fragmentList)


        binding.vpHome.adapter = myPageTabAdapter

    }

    private fun initTab(){
        val tabLabel = listOf("정보", "프로젝트", "피드", "평가")
        TabLayoutMediator(binding.tabHome, binding.vpHome) {tab, position ->
            tab.text = tabLabel[position]
        }.attach()
    }

    private fun settingBtnListener() {
        binding.ivMypageSetting.setOnClickListener {
            val intentSetting = Intent(requireActivity(), MyPageSettingActivity::class.java)
            startActivity(intentSetting)
        }

        binding.tvMyPageModifyProfile.setOnClickListener {
            val intentModifyProfile = Intent(requireActivity(), MyPageModifyProfileActivity::class.java)
            startActivity(intentModifyProfile)
        }

    }
}