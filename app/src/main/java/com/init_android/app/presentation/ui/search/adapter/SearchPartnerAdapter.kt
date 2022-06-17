package com.init_android.app.presentation.ui.search.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.init_android.app.data.model.PartnerData
import com.init_android.app.data.response.UserList
import com.init_android.databinding.ItemSearchPartnerBinding

class SearchPartnerAdapter(val context:Context) :
    ListAdapter<UserList, SearchPartnerAdapter.SearchPartnerViewHolder>(projectItemDiffUtil) {

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): SearchPartnerViewHolder {
        val binding = ItemSearchPartnerBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return SearchPartnerViewHolder(binding)
    }

    override fun onBindViewHolder(
        holder: SearchPartnerViewHolder,
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

    fun updatePartnerList(project: List<UserList>) {
        submitList(project)
    }

    class SearchPartnerViewHolder(val binding: ItemSearchPartnerBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun onBind(data: UserList) {
            binding.partnerItem = data
        }
    }

    companion object {
        private val projectItemDiffUtil = object : DiffUtil.ItemCallback<UserList>() {
            override fun areItemsTheSame(
                oldItem: UserList,
                newItem: UserList
            ): Boolean =
                oldItem.mName == newItem.mName

            override fun areContentsTheSame(
                oldItem: UserList,
                newItem: UserList
            ): Boolean =
                oldItem == newItem
        }
    }
}