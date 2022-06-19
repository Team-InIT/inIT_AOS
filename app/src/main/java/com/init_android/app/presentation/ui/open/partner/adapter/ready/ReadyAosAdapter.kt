package com.init_android.app.presentation.ui.open.partner.adapter.ready

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.init_android.app.data.response.project.ready.ResponseReadyAos
import com.init_android.app.presentation.ui.mypage.PartnerPageActivity
import com.init_android.databinding.ItemAosApproveListBinding

class ReadyAosAdapter(var userId : Int) : RecyclerView.Adapter<ReadyAosAdapter.PartnerListViewHolder>() {
    var partnerData = mutableListOf<ResponseReadyAos.WaitingAos>()

    private lateinit var mlistener : onItemClickListener

    interface onItemClickListener {
        fun onItemClick(user: Int, position: Int)
    }

    fun setOnItemClickListener(listener: onItemClickListener) {
        mlistener = listener
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): PartnerListViewHolder {
        val binding = ItemAosApproveListBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return PartnerListViewHolder(binding, mlistener)
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
        val binding: ItemAosApproveListBinding, listener: onItemClickListener
    ) : RecyclerView.ViewHolder(binding.root) {
        fun onBind(partnerData: ResponseReadyAos.WaitingAos) {
            binding.apply {
                ready = partnerData
                executePendingBindings()
            }
        }

        val retwit = binding.tvPartnerApprove
        val reject = binding.tvPartnerUnapprove

        init {
            retwit.setOnClickListener {
                listener.onItemClick(binding.ready!!.mNum,adapterPosition)
            }
            reject.setOnClickListener {
                listener.onItemClick(binding.ready!!.mNum, adapterPosition)
            }
        }
    }

    fun setQuestionPost(partnerData: MutableList<ResponseReadyAos.WaitingAos>) {
        this.partnerData = partnerData
        notifyDataSetChanged()

    }
}