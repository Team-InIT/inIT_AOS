package com.init_android.app.presentation.ui.open.partner.adapter

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.init_android.app.data.response.project.approve.ResponseProjectMember
import com.init_android.app.presentation.ui.mypage.PartnerPageActivity
import com.init_android.databinding.ItemPartnerDesignListBinding
import com.init_android.databinding.ItemPartnerListBinding

class PartnerDesignAdapter(var userId : Int) : RecyclerView.Adapter<PartnerDesignAdapter.PartnerListViewHolder>() {
    var partnerData = mutableListOf<ResponseProjectMember.ApprovedDesign>()

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): PartnerListViewHolder {
        val binding = ItemPartnerDesignListBinding.inflate(
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
        holder.binding.root.setOnClickListener {

            val context = holder.itemView.context

            val intent = Intent(context, PartnerPageActivity::class.java)
            val userId = partnerData[position].mNum
            intent.putExtra("userId", userId)
            ContextCompat.startActivity(holder.itemView.context,intent, null)

        }
    }

    override fun getItemCount(): Int = partnerData.size

    inner class PartnerListViewHolder(
        val binding: ItemPartnerDesignListBinding
    ) : RecyclerView.ViewHolder(binding.root) {
        fun onBind(partnerData: ResponseProjectMember.ApprovedDesign) {
            binding.apply {
                member = partnerData
                executePendingBindings()
            }
        }
    }

    fun setQuestionPost(partnerData: MutableList<ResponseProjectMember.ApprovedDesign>) {
        this.partnerData = partnerData
        notifyDataSetChanged()

    }
}