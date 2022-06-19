package com.init_android.app.presentation.ui.mypage.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.init_android.app.data.response.ResponseRateData
import com.init_android.app.data.response.mypage.ResponseEvaluation
import com.init_android.app.data.response.project.approve.ResponseProjectMember
import com.init_android.databinding.ItemRatingListBinding

class RatingAdapter() : RecyclerView.Adapter<RatingAdapter.PartnerListViewHolder>() {
    var partnerData = mutableListOf<ResponseEvaluation.MyEvaluation>()

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): PartnerListViewHolder {
        val binding = ItemRatingListBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return PartnerListViewHolder(binding)
    }

    override fun onBindViewHolder(holder: RatingAdapter.PartnerListViewHolder, position: Int) {
        holder.onBind(partnerData[position])
    }

    override fun getItemCount(): Int = partnerData.size

    inner class PartnerListViewHolder(
        val binding: ItemRatingListBinding
    ) : RecyclerView.ViewHolder(binding.root) {
        fun onBind(partnerData: ResponseEvaluation.MyEvaluation) {
            binding.apply {
                rate = partnerData
                executePendingBindings()
            }
        }
    }

    fun setQuestionPost(partnerData: MutableList<ResponseEvaluation.MyEvaluation>) {
        this.partnerData = partnerData
        notifyDataSetChanged()

    }


}