package com.init_android.app.presentation.ui.mypage.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.init_android.app.data.response.ResponseRateData
import com.init_android.app.data.response.ResponseStackData
import com.init_android.databinding.ItemRatingListBinding
import com.init_android.databinding.ItemStackBinding

class StackAdapter : RecyclerView.Adapter<StackAdapter.StackViewHolder>() {
    val rateList = mutableListOf<ResponseStackData>()

    class StackViewHolder(val binding : ItemStackBinding) : RecyclerView.ViewHolder(binding.root){
        fun onBind(data: ResponseStackData) {
            binding.apply {
                //rate = data
                executePendingBindings()
            }
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): StackViewHolder {
        val binding = ItemStackBinding.inflate(LayoutInflater.from(parent.context),parent,false)

        return StackViewHolder(binding)
    }

    override fun onBindViewHolder(holder: StackViewHolder, position: Int) {
        holder.onBind(rateList[position])
    }

    override fun getItemCount(): Int = rateList.size


}