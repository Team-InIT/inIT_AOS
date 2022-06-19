package com.init_android.app.presentation.ui.open.partner.adapter.ready

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.init_android.app.data.response.project.ready.ResponseReadyDesign
import com.init_android.app.presentation.ui.mypage.PartnerPageActivity
import com.init_android.databinding.ItemDesignApproveListBinding

class ReadyDesignAdapter(var userId : Int) : RecyclerView.Adapter<ReadyDesignAdapter.PartnerListViewHolder>() {
    var partnerData = mutableListOf<ResponseReadyDesign.WaitingDesign>()

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): PartnerListViewHolder {
        val binding = ItemDesignApproveListBinding.inflate(
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
        val binding: ItemDesignApproveListBinding
    ) : RecyclerView.ViewHolder(binding.root) {
        fun onBind(partnerData: ResponseReadyDesign.WaitingDesign) {
            binding.apply {
                ready = partnerData
                executePendingBindings()
            }
        }
    }

    fun setQuestionPost(partnerData: MutableList<ResponseReadyDesign.WaitingDesign>) {
        this.partnerData = partnerData
        notifyDataSetChanged()

    }
}