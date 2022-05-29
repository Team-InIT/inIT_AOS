package com.init_android.app.presentation.ui.search

import android.os.Bundle
import android.view.View
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.tabs.TabLayoutMediator
import com.init_android.R
import com.init_android.app.presentation.ui.search.adapter.SearchViewPagerAdapter
import com.init_android.app.presentation.ui.search.partner.SearchPartnerFragment
import com.init_android.app.presentation.ui.search.recruitfinish.SearchRecruitFinishFragment
import com.init_android.app.presentation.ui.search.recruiting.SearchRecruitingFragment
import com.init_android.databinding.FragmentSearchBinding
import com.playtogether_android.app.presentation.base.BaseFragment


class SearchFragment : BaseFragment<FragmentSearchBinding>(R.layout.fragment_search) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initAdapter()
        initTabLayout()
    }

    // viewpager2 초기화
    private fun initAdapter(){
        val searchViewPagerAdapter = SearchViewPagerAdapter(this)
        val fragmentList = listOf(SearchRecruitingFragment(),SearchRecruitFinishFragment(),SearchPartnerFragment())
        searchViewPagerAdapter.fragments.addAll(fragmentList)

        binding.vpSearch.adapter = searchViewPagerAdapter

        binding.vpSearch.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                super.onPageSelected(position)
            }
        })
    }

    // tablayout 초기화
    private fun initTabLayout(){
        TabLayoutMediator(binding.tbSearch, binding.vpSearch) { tab, position ->
            when(position){
                0 -> {tab.text = "모집중"}
                1 -> {tab.text = "모집완료"}
                2 -> {tab.text = "파트원"}
            }
        }.attach()
    }
}