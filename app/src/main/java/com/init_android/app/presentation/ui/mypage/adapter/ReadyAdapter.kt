package com.init_android.app.presentation.ui.mypage.adapter

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.init_android.app.data.response.project.approve.ResponseProjectMember
import com.init_android.app.data.response.project.approve.ResponsemyWaitingApproval
import com.init_android.app.presentation.ui.mypage.PartnerPageActivity
import com.init_android.app.presentation.ui.open.project.ProjectDetailActivity
import com.init_android.databinding.ItemPartnerListBinding
import com.init_android.databinding.ItemReadyProjectBinding

class ReadyAdapter(var userId : Int) : RecyclerView.Adapter<ReadyAdapter.ReadyApproveViewHolder>() {
    var partnerData = mutableListOf<ResponsemyWaitingApproval.ProjectInfo>()

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ReadyApproveViewHolder {
        val binding = ItemReadyProjectBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return ReadyApproveViewHolder(binding)
    }

    override fun onBindViewHolder(
        holder: ReadyApproveViewHolder,
        position: Int
    ) {
        holder.onBind(partnerData[position])
        holder.binding.root.setOnClickListener {

            val context = holder.itemView.context

            val intent = Intent(context, ProjectDetailActivity::class.java)
            val userId = partnerData[position].mNum
            intent.putExtra("userId", userId)
            ContextCompat.startActivity(holder.itemView.context,intent, null)

        }
    }

    override fun getItemCount(): Int = partnerData.size

    inner class ReadyApproveViewHolder(
        val binding: ItemReadyProjectBinding
    ) : RecyclerView.ViewHolder(binding.root) {
        fun onBind(partnerData: ResponsemyWaitingApproval.ProjectInfo) {
            binding.apply {
                readyProject = partnerData
                executePendingBindings()
            }
        }
    }

    fun setQuestionPost(partnerData: MutableList<ResponsemyWaitingApproval.ProjectInfo>) {
        this.partnerData = partnerData
        notifyDataSetChanged()

    }
}