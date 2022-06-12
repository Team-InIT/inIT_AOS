package com.init_android.app.presentation.ui.feed.detail

import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import com.init_android.R
import com.init_android.app.data.request.RequestFeedDetail
import com.init_android.app.presentation.ui.feed.FeedViewModel
import com.init_android.databinding.ActivityFeedDetailBinding
import com.playtogether_android.app.presentation.base.BaseActivity

class FeedDetailActivity:BaseActivity<ActivityFeedDetailBinding>(R.layout.activity_feed_detail) {

    private val feedViewModel : FeedViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val fNum = intent.getIntExtra("fNum",-1)

        initBackEvent()
        tryGetFeedDetail(fNum)
    }

    // 상세정보 서버 통신
    private fun tryGetFeedDetail(fNum:Int){

        val requestFeedDetail = RequestFeedDetail(
            fNum = fNum
        )

        feedViewModel.postDetailFeed(requestFeedDetail)

        feedViewModel.feedDetail.observe(this){
            binding.feedDetailData = it
        }
    }

    // 뒤로가기 버튼
    private fun initBackEvent(){
        binding.ivBack.setOnClickListener {
            finish()
        }
    }


}