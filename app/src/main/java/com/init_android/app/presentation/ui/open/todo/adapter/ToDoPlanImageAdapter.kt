package com.init_android.app.presentation.ui.open.todo.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.init_android.app.data.response.todo.ResponseAllToDo
import com.init_android.databinding.ItemToDoListBinding

class ToDoPlanImageAdapter() : RecyclerView.Adapter<ToDoPlanImageAdapter.PartnerListViewHolder>() {
    var partnerData = mutableListOf<ResponseAllToDo.Todo.Member>()



    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): PartnerListViewHolder {
        val binding = ItemToDoListBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return PartnerListViewHolder(binding)
    }

    override fun onBindViewHolder(
        holder: PartnerListViewHolder,
        position: Int
    ) {
        holder.onBind(partnerData[position])
    }

    override fun getItemCount(): Int = partnerData.size

    inner class PartnerListViewHolder(
        val binding: ItemToDoListBinding
    ) : RecyclerView.ViewHolder(binding.root) {
        fun onBind(partnerData: ResponseAllToDo.Todo.Member) {
            binding.apply {
                mem = partnerData
                executePendingBindings()
            }
        }

    }



    fun setQuestionPost(partnerData: MutableList<ResponseAllToDo.Todo.Member>) {
        this.partnerData = partnerData
        notifyDataSetChanged()

    }
}