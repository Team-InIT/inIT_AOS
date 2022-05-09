package com.init_android.app.presentation.ui.home.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.init_android.databinding.ItemRvPositionBinding

class PositionAdapter : RecyclerView.Adapter<PositionAdapter.PositionViewHolder>() {
    var positionList = mutableListOf<PositionData>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PositionViewHolder {
        val binding=ItemRvPositionBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return PositionViewHolder(binding)
    }

    override fun onBindViewHolder(holder: PositionViewHolder, position: Int) {
        holder.onBind(positionList[position])
    }

    override fun getItemCount(): Int = positionList.size

    class PositionViewHolder(
        private val binding : ItemRvPositionBinding
    ) : RecyclerView.ViewHolder(binding.root){
        fun onBind(data : PositionData){
            binding.tvPosition.text = data.positionType
        }

    }

}

data class PositionData(val positionType:String)