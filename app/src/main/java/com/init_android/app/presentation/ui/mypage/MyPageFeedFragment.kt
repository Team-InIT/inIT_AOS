package com.init_android.app.presentation.ui.mypage

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.init_android.R
import com.init_android.app.data.response.ResponseRateData
import com.init_android.app.presentation.ui.mypage.adapter.FeedAdapter
import com.init_android.app.presentation.ui.mypage.adapter.RatingAdapter
import com.init_android.databinding.FragmentFeedBinding
import com.init_android.databinding.FragmentMyPageFeedBinding
import com.playtogether_android.app.presentation.base.BaseFragment


class MyPageFeedFragment : BaseFragment<FragmentMyPageFeedBinding>(R.layout.fragment_my_page_feed) {

    private lateinit var feedAdapter: FeedAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initAdapter()
    }

    private fun initAdapter() {
        feedAdapter = FeedAdapter()
        binding.rvFeed.adapter = feedAdapter

        feedAdapter.rateList.addAll(
            listOf(

            )
        )

        feedAdapter.notifyDataSetChanged()
    }
}