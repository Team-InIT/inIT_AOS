package com.init_android.app.presentation.ui.mypage.adapter

import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.init_android.app.presentation.ui.mypage.MyPageFragment
import com.init_android.app.presentation.ui.mypage.PartnerPageActivity

class OtherPageTabAdapter(activity: PartnerPageActivity) : FragmentStateAdapter(activity){
    val fragments = mutableListOf<Fragment>()

    override fun getItemCount(): Int = fragments.size

    override fun createFragment(position: Int): Fragment = fragments[position]

}