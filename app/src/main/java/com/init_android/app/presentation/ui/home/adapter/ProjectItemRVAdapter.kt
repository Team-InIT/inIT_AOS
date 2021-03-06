package com.init_android.app.presentation.ui.home.adapter

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.init_android.app.data.model.ProjectItemData
import com.init_android.app.presentation.ui.open.project.ProjectDetailActivity
import com.init_android.databinding.ItemRvProjectBinding

class ProjectItemRVAdapter(val context:Context) :
    ListAdapter<ProjectItemData, ProjectItemRVAdapter.ProjectItemRVViewHolder>(projectItemDiffUtil) {

    var itemList = listOf<ProjectItemData>()

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ProjectItemRVViewHolder {

        val binding = ItemRvProjectBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return ProjectItemRVViewHolder(binding)
    }

    override fun onBindViewHolder(
        holder: ProjectItemRVViewHolder,
        position: Int
    ) {
        holder.onBind(getItem(position))

        // 프로젝트 정보 보기 화면으로 이동
        holder.binding.root.setOnClickListener {
            val nextIntent = Intent(context, ProjectDetailActivity::class.java)
            nextIntent.putExtra("pNum",getProjectList()[holder.adapterPosition].pNum)
            nextIntent.putExtra("mNum",getProjectList()[holder.adapterPosition].mNum)
            // readIntent.putExtra("noticeId",itemList[holder.adapterPosition].noticeId)
            (context as Activity).startActivity(nextIntent)
        }
    }

    fun updateProjectList(project: List<ProjectItemData>) {
        submitList(project)
    }

    fun setProjectList(project: List<ProjectItemData>){
        itemList = project
    }

    fun getProjectList(): List<ProjectItemData> {
        return itemList
    }

    class ProjectItemRVViewHolder(val binding: ItemRvProjectBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun onBind(data: ProjectItemData) {
            binding.projectItem = data
        }
    }

    companion object {
        private val projectItemDiffUtil = object : DiffUtil.ItemCallback<ProjectItemData>() {
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