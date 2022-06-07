package com.init_android.app.presentation.ui.open.partner.adapter

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView

class PartnerAdapter() : RecyclerView.Adapter<PartnerAdapter.MyPageLikeQuestionInfoViewHolder>() {
    var myPageLikeQuestionData = mutableListOf<MyPageLikeQuestionData.Data.LikePost>()

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): MyPageLikeQuestionInfoViewHolder {
        val binding = ItemMypageLikeQuestionBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return MyPageLikeQuestionInfoViewHolder(binding)
    }

    override fun onBindViewHolder(
        holder: MyPageLikeQuestionInfoViewHolder,
        position: Int
    ) {
        holder.onBind(myPageLikeQuestionData[position])
        holder.binding.root.setOnClickListener {

        }
    }

    override fun getItemCount(): Int = myPageLikeQuestionData.size

    inner class MyPageLikeQuestionInfoViewHolder(
        val binding: ItemMypageLikeQuestionBinding
    ) : RecyclerView.ViewHolder(binding.root) {
        fun onBind(myPageLikeQuestionData: MyPageLikeQuestionData.Data.LikePost) {
            binding.apply {
                myPageLikeQuestion = myPageLikeQuestionData
                executePendingBindings()
            }
        }
    }

    fun setQuestionPost(myPageLikeQuestionData: MutableList<MyPageLikeQuestionData.Data.LikePost>) {
        this.myPageLikeQuestionData = myPageLikeQuestionData
        notifyDataSetChanged()

    }
}
