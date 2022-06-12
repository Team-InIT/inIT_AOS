package com.init_android.app.presentation.ui.home.adapter

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.init_android.app.data.model.ProjectItemData
import com.init_android.app.presentation.ui.open.project.ProjectDetailActivity
import com.init_android.databinding.ItemVpProjectBinding

class ProjectItemVPAdapter(private val itemList:MutableList<ProjectItemData>,context: Context):RecyclerView.Adapter<ProjectItemVPAdapter.ProjectVPHolder>() {

    val context = context

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProjectVPHolder {
        val binding = ItemVpProjectBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return ProjectVPHolder(binding)
    }

    override fun onBindViewHolder(holder: ProjectVPHolder, position: Int) {
        holder.bind(itemList[position])

        // 프로젝트 정보 보기 화면으로 이동
        holder.binding.root.setOnClickListener {
            val nextIntent = Intent(context, ProjectDetailActivity::class.java)
            nextIntent.putExtra("pNum",itemList[holder.adapterPosition].pNum)
            nextIntent.putExtra("mNum",itemList[holder.adapterPosition].mNum)
            // readIntent.putExtra("noticeId",itemList[holder.adapterPosition].noticeId)
            (context as Activity).startActivity(nextIntent)
        }
    }

    override fun getItemCount(): Int = itemList.size

    class ProjectVPHolder(var binding:ItemVpProjectBinding):RecyclerView.ViewHolder(binding.root){
        fun bind(data:ProjectItemData){
            binding.projectItem = data
        }
    }
}