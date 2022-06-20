package com.init_android.app.presentation.ui.open.todo.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.init_android.app.data.response.todo.ResponseAllToDo
import com.init_android.databinding.ItemToDoListBinding

class ToDoPlanAdapter() : RecyclerView.Adapter<ToDoPlanAdapter.PartnerListViewHolder>() {
    var partnerData = mutableListOf<ResponseAllToDo.Todo>()

    private lateinit var mToDo : onItemClickListener

    interface onItemClickListener {
        fun onItemClick(position: Int)
    }

    fun setOnItemClickListener(listener: onItemClickListener) {
        mToDo = listener
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
        return PartnerListViewHolder(binding, mToDo)
    }

    override fun onBindViewHolder(
        holder: PartnerListViewHolder,
        position: Int
    ) {
        holder.onBind(partnerData[position])
    }

    override fun getItemCount(): Int = partnerData.size

    inner class PartnerListViewHolder(
        val binding: ItemToDoListBinding,
        mToDo: onItemClickListener
    ) : RecyclerView.ViewHolder(binding.root) {
        fun onBind(partnerData: ResponseAllToDo.Todo) {
            binding.apply {
                todo = partnerData
                executePendingBindings()
            }
        }

        val todo = binding.ivCheckBox

        init{
            todo.setOnClickListener {
                mToDo.onItemClick(adapterPosition)
                todo.isSelected = !todo.isSelected

            }
        }
    }



    fun setQuestionPost(partnerData: MutableList<ResponseAllToDo.Todo>) {
        this.partnerData = partnerData
        notifyDataSetChanged()

    }
}