package com.init_android.app.presentation.ui.feed.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.init_android.app.data.model.FeedListData
import com.init_android.app.data.response.ResponseFeedData
import com.init_android.databinding.ItemFeedListBinding

class SearchFeedAdapter(val context:Context) :
    ListAdapter<FeedListData, SearchFeedAdapter.SearchFeedViewHolder>(projectItemDiffUtil) {

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
        /*holder.binding.root.setOnClickListener {
            val nextIntent = Intent(context, IncludedProjectActivity::class.java)
            // readIntent.putExtra("noticeId",itemList[holder.adapterPosition].noticeId)
            (context as Activity).startActivity(nextIntent)
        }*/
    }

    fun updateFeedList(feedList: List<FeedListData>) {
        submitList(feedList)
    }

    class SearchFeedViewHolder(val binding: ItemFeedListBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun onBind(data: FeedListData) {
            binding.feedData = data
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