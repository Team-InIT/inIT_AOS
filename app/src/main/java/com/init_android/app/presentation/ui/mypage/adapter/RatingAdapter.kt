package com.init_android.app.presentation.ui.mypage.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.init_android.app.data.response.ResponseRateData
import com.init_android.databinding.ItemRatingListBinding

class RatingAdapter : RecyclerView.Adapter<RatingAdapter.RateViewHolder>() {
    val rateList = mutableListOf<ResponseRateData>()

    class RateViewHolder(val binding : ItemRatingListBinding) : RecyclerView.ViewHolder(binding.root){
        fun onBind(data: ResponseRateData) {
            binding.apply {
                rate = data
                executePendingBindings()
            }
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RateViewHolder {
        val binding = ItemRatingListBinding.inflate(LayoutInflater.from(parent.context),parent,false)

        return RateViewHolder(binding)
    }

    override fun onBindViewHolder(holder: RateViewHolder, position: Int) {
        holder.onBind(rateList[position])
    }

    override fun getItemCount(): Int = rateList.size


}