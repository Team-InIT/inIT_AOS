package com.init_android.app.presentation.ui.search.adapter

import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter

class SearchViewPagerAdapter(fragment: Fragment): FragmentStateAdapter(fragment){

    val fragments = mutableListOf<Fragment>()

    override fun getItemCount(): Int = fragments.size

    override fun createFragment(position: Int): Fragment = fragments[position]

}