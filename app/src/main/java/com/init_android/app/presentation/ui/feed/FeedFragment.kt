package com.init_android.app.presentation.ui.feed

import android.content.Intent
import android.graphics.Bitmap
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.fragment.app.viewModels
import com.init_android.R
import com.init_android.app.data.model.FeedListData
import com.init_android.app.presentation.ui.feed.adapter.SearchFeedAdapter
import com.init_android.app.presentation.ui.feed.write.FeedWritingActivity
import com.init_android.app.util.BitmapUtil
import com.init_android.databinding.FragmentFeedBinding
import com.playtogether_android.app.presentation.base.BaseFragment
import java.nio.Buffer

class FeedFragment : BaseFragment<FragmentFeedBinding>(R.layout.fragment_feed) {

    private val feedViewModel : FeedViewModel by viewModels()
    private var feedList = mutableListOf<FeedListData>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initBtn()
        initAdapter()
    }

    private fun getBitmap(buffer: Buffer, width: Int, height: Int): Bitmap? {
        buffer.rewind()
        val bitmap = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888)
        bitmap.copyPixelsFromBuffer(buffer)
        return bitmap
    }

    private fun initAdapter(){
        val adapter = SearchFeedAdapter(requireContext())
        binding.rvFeed.adapter = adapter

        feedViewModel.getAllFeedList()

        feedViewModel.feedList.observe(viewLifecycleOwner) {
            for (i in 0 until it.feeds.size) {

                Log.d("testing",it.feeds.toString())
                val img = it.feeds[i].fPhoto?.let { it1 -> BitmapUtil().getImg(it.feeds[i].fPhoto) }
                //val img = it.feeds[i].fPhoto?.let { it1 -> bitmap.copyPixelsToBuffer(it1) }
                feedList.add(FeedListData(it.feeds[i].fTitle, img, it.feeds[i].fNum))

            }
            adapter.submitList(feedList)
        }

        adapter
    }

    private fun initBtn(){
        binding.ivFeed.setOnClickListener {
            startActivity(Intent(requireContext(),FeedWritingActivity::class.java))
        }
    }
}