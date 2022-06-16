package com.init_android.app.presentation.ui.open.team.adapter

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.init_android.R
import com.init_android.app.data.model.FeedListData
import com.init_android.app.presentation.ui.home.adapter.PositionAdapter
import com.init_android.app.presentation.ui.open.team.TeamData
import com.init_android.databinding.ItemTeamListBinding

class TeamReviewAdapter(context: Context) :
    ListAdapter<TeamData, TeamReviewAdapter.TeamReviewViewHolder>(projectItemDiffUtil) {

    val context = context

    // 아이템 클릭 이벤트
    private lateinit var itemClickListener : OnItemClickListener

    interface OnItemClickListener { // 리스너 클릭 인터페이스
        fun onClick(v: View, position: Int)
    }

    fun setItemClickListener(onItemClickListener: OnItemClickListener) { // 외부 클릭시 이벤트 설정
        this.itemClickListener = onItemClickListener
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): TeamReviewViewHolder {
        val binding = ItemTeamListBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return TeamReviewViewHolder(binding)
    }

    override fun onBindViewHolder(
        holder: TeamReviewViewHolder,
        position: Int
    ) {
        holder.onBind(getItem(position))

        holder.itemView.setOnClickListener {
            itemClickListener.onClick(it,position)
            notifyDataSetChanged()
        }

        /*holder.binding.root.setOnClickListener {

            if (!getItem(holder.adapterPosition).isChecked){
                getItem(holder.adapterPosition).isChecked = true
                holder.binding.root.background = ContextCompat.getDrawable(context, R.drawable.rectangle_fill_gray_radius_10)
            }else if (getItem(holder.adapterPosition).isChecked){
                getItem(holder.adapterPosition).isChecked = false
                holder.binding.root.background = ContextCompat.getDrawable(context, R.drawable.rectangle_stroke_main_radius_10)
            }

            Log.d("hear",getItem(holder.adapterPosition).isChecked.toString())
        }*/

    }

    inner class TeamReviewViewHolder(val binding: ItemTeamListBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun onBind(data: TeamData) {
            binding.tvTeamName.text = data.name
            binding.tvTeamPosition.text = data.position

            binding.root.isSelected = data.checkState != false
            /*if (data.isChecked) {
                binding.root.background = ContextCompat.getDrawable(context, R.drawable.rectangle_fill_gray_radius_10)
            }else{
                binding.root.background = ContextCompat.getDrawable(context, R.drawable.rectangle_stroke_main_radius_10)
            }*/
        }


    }


    companion object {
        private val projectItemDiffUtil = object : DiffUtil.ItemCallback<TeamData>() {
            override fun areItemsTheSame(
                oldItem: TeamData,
                newItem: TeamData
            ): Boolean =
                oldItem.name == newItem.name

            override fun areContentsTheSame(
                oldItem: TeamData,
                newItem: TeamData
            ): Boolean =
                oldItem == newItem
        }
    }
}