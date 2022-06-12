package com.init_android.app.presentation.ui.mypage

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.init_android.R
import com.init_android.app.data.model.FeedListData
import com.init_android.app.data.request.RequestMyFeed
import com.init_android.app.data.response.ResponseRateData
import com.init_android.app.presentation.ui.feed.FeedViewModel
import com.init_android.app.presentation.ui.feed.adapter.SearchFeedAdapter
import com.init_android.app.presentation.ui.mypage.adapter.FeedAdapter
import com.init_android.app.presentation.ui.mypage.adapter.RatingAdapter
import com.init_android.app.presentation.ui.mypage.viewmodel.MyPageViewModel
import com.init_android.databinding.FragmentFeedBinding
import com.init_android.databinding.FragmentMyPageFeedBinding
import com.playtogether_android.app.presentation.base.BaseFragment


class MyPageFeedFragment : BaseFragment<FragmentMyPageFeedBinding>(R.layout.fragment_my_page_feed) {

    private val myPageViewModel : MyPageViewModel by viewModels()
    private var feedList = mutableListOf<FeedListData>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        tryPostMyFeeds()
    }

    private fun tryPostMyFeeds(){
        val adapter = SearchFeedAdapter(requireContext())
        binding.rvFeed.adapter = adapter

        myPageViewModel.postMyFeeds(requestMyFeed = RequestMyFeed(1))

        myPageViewModel.feedList.observe(viewLifecycleOwner) {
            for (i in 0 until it.Feeds.size) {
                val img_uri = it.Feeds[i].fTest
                feedList.add(FeedListData(it.Feeds[i].fTitle, img_uri, it.Feeds[i].fNum))

            }
            adapter.submitList(feedList)
            adapter.setFeedList(feedList)
        }
    }

    /*private fun initAdapter() {
        feedAdapter = FeedAdapter()
        binding.rvFeed.adapter = feedAdapter

        feedAdapter.rateList.addAll(
            listOf(

            )
        )

        feedAdapter.notifyDataSetChanged()
    }*/
}