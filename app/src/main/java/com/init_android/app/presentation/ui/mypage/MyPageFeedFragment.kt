package com.init_android.app.presentation.ui.mypage

import android.os.Bundle
import android.view.View
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import com.init_android.R
import com.init_android.app.data.model.FeedListData
import com.init_android.app.data.request.RequestMyFeed
import com.init_android.app.presentation.ui.feed.adapter.SearchFeedAdapter
import com.init_android.app.presentation.ui.main.MainViewModel
import com.init_android.app.presentation.ui.mypage.viewmodel.MyPageViewModel
import com.init_android.databinding.FragmentMyPageFeedBinding
import com.playtogether_android.app.presentation.base.BaseFragment


class MyPageFeedFragment : BaseFragment<FragmentMyPageFeedBinding>(R.layout.fragment_my_page_feed) {

    private val mainViewModel: MainViewModel by activityViewModels()
    private val myPageViewModel : MyPageViewModel by viewModels()
    private var feedList = mutableListOf<FeedListData>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        //tryPostMyFeeds()
    }

    private fun tryPostMyFeeds(){
        val adapter = SearchFeedAdapter(requireContext())
        binding.rvFeed.adapter = adapter
        val mNum = mainViewModel.otherNum.value ?: 1
        myPageViewModel.postMyFeeds(requestMyFeed = RequestMyFeed(mNum))

        myPageViewModel.feedList.observe(viewLifecycleOwner) {
            for (i in 0 until it.Feeds?.size!!) {
                val img_uri = it.Feeds[i].fTest
                feedList.add(FeedListData(it.Feeds[i].fTitle ?: "", img_uri, it.Feeds[i].fNum ?: 1))

            }
            adapter.submitList(feedList)
            adapter.setFeedList(feedList)
        }
    }

}