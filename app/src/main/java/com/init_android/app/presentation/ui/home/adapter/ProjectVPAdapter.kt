package com.init_android.app.presentation.ui.home.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.init_android.app.data.model.ProjectItemData
import com.init_android.databinding.ItemVpProjectBinding

class ProjectVPAdapter(private val itemList:MutableList<ProjectItemData>):RecyclerView.Adapter<ProjectVPAdapter.ProjectVPHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProjectVPHolder {
        val binding = ItemVpProjectBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return ProjectVPHolder(binding)
    }

    override fun onBindViewHolder(holder: ProjectVPHolder, position: Int) {
        holder.bind(itemList[position])
    }

    override fun getItemCount(): Int = itemList.size

    class ProjectVPHolder(var binding:ItemVpProjectBinding):RecyclerView.ViewHolder(binding.root){
        fun bind(data:ProjectItemData){
            binding.projectItem = data
        }
    }
}