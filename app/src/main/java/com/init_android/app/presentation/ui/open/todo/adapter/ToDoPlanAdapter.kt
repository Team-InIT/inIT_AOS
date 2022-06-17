package com.init_android.app.presentation.ui.open.todo.adapter

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.init_android.app.data.response.project.approve.ResponseProjectMember
import com.init_android.app.data.response.todo.ResponseAllToDo
import com.init_android.app.presentation.ui.mypage.PartnerPageActivity
import com.init_android.databinding.ItemPartnerListBinding
import com.init_android.databinding.ItemToDoListBinding

class ToDoPlanAdapter() : RecyclerView.Adapter<ToDoPlanAdapter.PartnerListViewHolder>() {
    var partnerData = mutableListOf<ResponseAllToDo.Todo>()

    private lateinit var mlistener : onItemClickListener

    interface onItemClickListener {
        fun onItemClick(position: Int)
    }

    fun setOnItemClickListener(listener: onItemClickListener) {
        mlistener = listener
    }



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
        fun onBind(partnerData: ResponseAllToDo.Todo) {
            binding.apply {
                todo = partnerData
                executePendingBindings()
            }
        }
        val check = binding.ivCheckBox

        init {
            check.setOnClickListener {
                mlistener.onItemClick(adapterPosition)
                //check.isSelected = true
            }
        }
    }



    fun setQuestionPost(partnerData: MutableList<ResponseAllToDo.Todo>) {
        this.partnerData = partnerData
        notifyDataSetChanged()

    }
}