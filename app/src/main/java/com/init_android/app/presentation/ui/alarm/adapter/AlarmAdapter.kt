package com.init_android.app.presentation.ui.alarm.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.init_android.app.data.model.AlarmData
import com.init_android.databinding.ItemAlarmListBinding

class AlarmAdapter(val context: Context) :
    ListAdapter<AlarmData, AlarmAdapter.AlarmViewHolder>(alarmItemDiffUtil) {

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): AlarmViewHolder {
        val binding = ItemAlarmListBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return AlarmViewHolder(binding)
    }

    override fun onBindViewHolder(
        holder: AlarmViewHolder,
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

    class AlarmViewHolder(val binding: ItemAlarmListBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun onBind(data: AlarmData) {
            binding.alarmData = data
            binding.layoutAlarm.isSelected = data.readState != 0
            Glide.with(binding.root).load(data.img).into(binding.ivAlarmIcon)
        }
    }

    companion object {
        private val alarmItemDiffUtil = object : DiffUtil.ItemCallback<AlarmData>() {
            override fun areItemsTheSame(
                oldItem: AlarmData,
                newItem: AlarmData
            ): Boolean =
                oldItem.content == newItem.content

            override fun areContentsTheSame(
                oldItem: AlarmData,
                newItem: AlarmData
            ): Boolean =
                oldItem == newItem
        }
    }
}