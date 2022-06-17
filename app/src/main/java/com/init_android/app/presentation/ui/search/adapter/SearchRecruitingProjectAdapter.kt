package com.init_android.app.presentation.ui.search.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.init_android.app.data.model.ProjectItemData
import com.init_android.databinding.ItemRvProjectBinding

class SearchRecruitingProjectAdapter(val context:Context) :
    ListAdapter<ProjectItemData, SearchRecruitingProjectAdapter.ProjectItemRVViewHolder>(SearchRecruitingProjectAdapter.recruitingItemDiffUtil){

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): SearchRecruitingProjectAdapter.ProjectItemRVViewHolder {
        val binding = ItemRvProjectBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return SearchRecruitingProjectAdapter.ProjectItemRVViewHolder(binding)
    }

    override fun onBindViewHolder(
        holder: SearchRecruitingProjectAdapter.ProjectItemRVViewHolder,
        position: Int
    ) {
        holder.onBind(getItem(position))

        // 프로젝트 정보 보기 화면으로 이동
        /*holder.binding.root.setOnClickListener {
            val nextIntent = Intent(context, ProjectDetailActivity::class.java)
            nextIntent.putExtra("pNum",getProjectList()[holder.adapterPosition].pNum)
            nextIntent.putExtra("mNum",getProjectList()[holder.adapterPosition].mNum)
            // readIntent.putExtra("noticeId",itemList[holder.adapterPosition].noticeId)
            (context as Activity).startActivity(nextIntent)
        }*/
    }

    class ProjectItemRVViewHolder(val binding: ItemRvProjectBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun onBind(data: ProjectItemData) {
            binding.projectItem = data
        }
    }

    companion object {
        private val recruitingItemDiffUtil = object : DiffUtil.ItemCallback<ProjectItemData>() {
            override fun areItemsTheSame(
                oldItem: ProjectItemData,
                newItem: ProjectItemData
            ): Boolean =
                oldItem.pTitle == newItem.pTitle

            override fun areContentsTheSame(
                oldItem: ProjectItemData,
                newItem: ProjectItemData
            ): Boolean =
                oldItem == newItem
        }
    }
}