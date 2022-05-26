package com.init_android.app.presentation.ui.mypage.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.init_android.app.data.response.ResponseFeedData
import com.init_android.app.data.response.ResponseRateData
import com.init_android.databinding.ItemFeedListBinding
import com.init_android.databinding.ItemRatingListBinding

class FeedAdapter : RecyclerView.Adapter<FeedAdapter.FeedViewHolder>() {
    val rateList = mutableListOf<ResponseFeedData>()

    class FeedViewHolder(val binding : ItemFeedListBinding) : RecyclerView.ViewHolder(binding.root){
        fun onBind(data: ResponseFeedData) {
            binding.apply {
                //feed = data
                executePendingBindings()
            }
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FeedViewHolder {
        val binding = ItemFeedListBinding.inflate(LayoutInflater.from(parent.context),parent,false)

        return FeedViewHolder(binding)
    }

    override fun onBindViewHolder(holder: FeedViewHolder, position: Int) {
        holder.onBind(rateList[position])
    }

    override fun getItemCount(): Int = rateList.size


}