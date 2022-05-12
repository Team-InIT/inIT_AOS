package com.init_android.app.presentation.ui.home.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.init_android.databinding.ItemRvPositionBinding

class PositionAdapter : RecyclerView.Adapter<PositionAdapter.PositionViewHolder>() {
    var positionList = mutableListOf<PositionData>()

    // 아이템 클릭 이벤트
    private lateinit var itemClickListener : OnItemClickListener

    interface OnItemClickListener { // 리스너 클릭 인터페이스
        fun onClick(v: View, position: Int)
    }

    fun setItemClickListener(onItemClickListener: OnItemClickListener) { // 외부 클릭시 이벤트 설정
        this.itemClickListener = onItemClickListener
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PositionViewHolder {
        val binding=ItemRvPositionBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return PositionViewHolder(binding)
    }

    override fun onBindViewHolder(holder: PositionViewHolder, position: Int) {
        holder.onBind(positionList[position])
        holder.itemView.setOnClickListener {
            itemClickListener.onClick(it,position)
        }
    }

    override fun getItemCount(): Int = positionList.size

    inner class PositionViewHolder(
        private val binding : ItemRvPositionBinding
    ) : RecyclerView.ViewHolder(binding.root){

        fun onBind(data : PositionData){
            binding.tvPosition.text = data.positionType
            Glide.with(binding.root).load(data.icon).into(binding.ivIcon)

            binding.root.isSelected = data.checkState != false
        }
    }

}

data class PositionData(val positionType:String, var icon:Int, var checkState:Boolean)