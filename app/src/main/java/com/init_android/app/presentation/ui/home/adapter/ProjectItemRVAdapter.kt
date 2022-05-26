package com.init_android.app.presentation.ui.home.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.init_android.app.data.model.ProjectItemData
import com.init_android.databinding.ItemRvProjectBinding

class ProjectItemRVAdapter :
    ListAdapter<ProjectItemData, ProjectItemRVAdapter.ProjectItemRVViewHolder>(projectItemDiffUtil) {

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
    }

    fun updateProjectList(project: List<ProjectItemData>) {
        submitList(project)
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