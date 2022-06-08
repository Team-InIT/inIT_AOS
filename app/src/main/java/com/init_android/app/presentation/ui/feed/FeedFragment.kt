package com.init_android.app.presentation.ui.feed

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import com.init_android.R
import com.init_android.app.data.model.FeedListData
import com.init_android.app.data.response.ResponseFeedData
import com.init_android.app.presentation.ui.feed.adapter.SearchFeedAdapter
import com.init_android.app.presentation.ui.feed.write.FeedWritingActivity
import com.init_android.app.presentation.ui.mypage.adapter.FeedAdapter
import com.init_android.app.presentation.ui.mypage.viewmodel.MyPageViewModel
import com.init_android.databinding.FragmentFeedBinding
import com.playtogether_android.app.presentation.base.BaseFragment

class FeedFragment : BaseFragment<FragmentFeedBinding>(R.layout.fragment_feed) {

    private val feedViewModel : FeedViewModel by viewModels()
    private var feedList = mutableListOf<FeedListData>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initBtn()
        initNetWork()
        initAdapter()
    }

    private fun initNetWork(){


    }

    private fun initAdapter(){
        val adapter = SearchFeedAdapter(requireContext())
        binding.rvFeed.adapter = adapter



        feedViewModel.getAllFeedList()

        feedViewModel.feedList.observe(viewLifecycleOwner) {
            for (i in 0 until it.feeds.size)
            feedList.add(FeedListData(it.feeds[i].fTitle,"",it.feeds[i].fNum))

            adapter.submitList(feedList)
        }
    }

    private fun initBtn(){
        binding.ivFeed.setOnClickListener {
            startActivity(Intent(requireContext(),FeedWritingActivity::class.java))
        }
    }
}