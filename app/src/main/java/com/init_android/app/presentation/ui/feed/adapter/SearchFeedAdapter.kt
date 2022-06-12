package com.init_android.app.presentation.ui.feed.adapter

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.init_android.app.data.model.FeedListData
import com.init_android.app.data.response.ResponseFeedData
import com.init_android.app.presentation.ui.feed.detail.FeedDetailActivity
import com.init_android.databinding.ItemFeedListBinding

class SearchFeedAdapter(val context:Context) :
    ListAdapter<FeedListData, SearchFeedAdapter.SearchFeedViewHolder>(projectItemDiffUtil) {

    var itemList = listOf<FeedListData>()

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): SearchFeedViewHolder {
        val binding = ItemFeedListBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return SearchFeedViewHolder(binding)
    }

    override fun onBindViewHolder(
        holder: SearchFeedViewHolder,
        position: Int
    ) {
        holder.onBind(getItem(position))

        // 프로젝트 정보 보기 화면으로 이동
        holder.binding.root.setOnClickListener {
            val nextIntent = Intent(context, FeedDetailActivity::class.java)
            nextIntent.putExtra("fNum", getFeedList()[holder.adapterPosition].fNum)
            (context as Activity).startActivity(nextIntent)
        }
    }

    fun updateFeedList(feedList: List<FeedListData>) {
        submitList(feedList)
    }

    fun setFeedList(feedList: List<FeedListData>){
        itemList = feedList
    }

    fun getFeedList(): List<FeedListData> {
        return itemList
    }

    class SearchFeedViewHolder(val binding: ItemFeedListBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun onBind(data: FeedListData) {
            binding.feedData = data
            Glide.with(binding.root).load(data.fImage).into(binding.ivIcon)
        }
    }

    companion object {
        private val projectItemDiffUtil = object : DiffUtil.ItemCallback<FeedListData>() {
            override fun areItemsTheSame(
                oldItem: FeedListData,
                newItem: FeedListData
            ): Boolean =
                oldItem.fTitle == newItem.fTitle

            override fun areContentsTheSame(
                oldItem: FeedListData,
                newItem: FeedListData
            ): Boolean =
                oldItem == newItem
        }
    }
}