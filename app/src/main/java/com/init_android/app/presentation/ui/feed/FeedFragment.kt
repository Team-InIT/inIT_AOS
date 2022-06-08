package com.init_android.app.presentation.ui.feed

import android.os.Bundle
import android.view.View
import com.init_android.R
import com.init_android.app.presentation.ui.mypage.adapter.FeedAdapter
import com.init_android.databinding.FragmentFeedBinding
import com.playtogether_android.app.presentation.base.BaseFragment

class FeedFragment : BaseFragment<FragmentFeedBinding>(R.layout.fragment_feed) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

    }

    private fun initAdapter(){
        val adapter = FeedAdapter()
        binding.rvFeed.adapter = adapter
    }

}