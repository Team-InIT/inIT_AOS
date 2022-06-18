package com.init_android.app.presentation.ui.open.team

import android.os.Bundle
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.tabs.TabLayoutMediator
import com.init_android.R
import com.init_android.app.presentation.ui.open.team.adapter.TeamViewPagerAdapter
import com.init_android.app.presentation.ui.search.recruitfinish.SearchRecruitFinishFragment
import com.init_android.app.presentation.ui.search.recruiting.SearchRecruitingFragment
import com.init_android.databinding.ActivityTeamReviewBinding
import com.playtogether_android.app.presentation.base.BaseActivity

class TeamReviewActivity:BaseActivity<ActivityTeamReviewBinding>(R.layout.activity_team_review) {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        initAdapter()
        initTabLayout()
        backBtnListener()
    }

    // viewpager2 초기화
    private fun initAdapter(){
        val searchViewPagerAdapter = TeamViewPagerAdapter(this)
        val fragmentList = listOf(
            ReviewUnDoneFragment(),
            ReviewDoneFragment(),
        )
        searchViewPagerAdapter.fragments.addAll(fragmentList)

        binding.vpTeam.adapter = searchViewPagerAdapter

        binding.vpTeam.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                super.onPageSelected(position)
            }
        })
    }

    // tablayout 초기화
    private fun initTabLayout(){
        TabLayoutMediator(binding.tbTeam, binding.vpTeam) { tab, position ->
            when(position){
                0 -> {tab.text = "미평가"}
                1 -> {tab.text = "평가"}
            }
        }.attach()
    }

    private fun backBtnListener() {
        binding.ivBack.setOnClickListener {
            finish()
        }
    }
}