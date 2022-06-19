package com.init_android.app.presentation.ui.open.partner.adapter.ready

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.init_android.app.data.response.project.ready.ResponseReadyPlan
import com.init_android.databinding.ItemPartnerApproveListBinding

class ReadyPlanAdapter(var userId : Int) : RecyclerView.Adapter<ReadyPlanAdapter.PartnerListViewHolder>() {
    var partnerData = mutableListOf<ResponseReadyPlan.WaitingPlan>()

    private lateinit var mlistener : onItemClickListener

    interface onItemClickListener {
        fun onItemClick(user: String, position: Int)
    }

    fun setOnItemClickListener(listener: onItemClickListener) {
        mlistener = listener
    }


    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): PartnerListViewHolder {
        val binding = ItemPartnerApproveListBinding.inflate(
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
        /*
        holder.binding.root.setOnClickListener {

            val context = holder.itemView.context

            val intent = Intent(context, PartnerPageActivity::class.java)
            val userId = partnerData[position].mNum
            intent.putExtra("userId", userId)
            ContextCompat.startActivity(holder.itemView.context,intent, null)
        }
         */
    }

    override fun getItemCount(): Int = partnerData.size

    inner class PartnerListViewHolder(
        val binding: ItemPartnerApproveListBinding, listener: onItemClickListener
    ) : RecyclerView.ViewHolder(binding.root) {
        fun onBind(partnerData: ResponseReadyPlan.WaitingPlan) {
            binding.apply {
                ready = partnerData
                executePendingBindings()
            }
        }

        val retwit = binding.tvPartnerApprove

        init {
            retwit.setOnClickListener {
                listener.onItemClick(binding.ready!!.mName,adapterPosition)
            }
        }
    }

    fun setQuestionPost(partnerData: MutableList<ResponseReadyPlan.WaitingPlan>) {
        this.partnerData = partnerData
        notifyDataSetChanged()

    }
}