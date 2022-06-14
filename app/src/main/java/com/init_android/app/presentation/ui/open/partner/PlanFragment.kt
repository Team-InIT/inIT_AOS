package com.init_android.app.presentation.ui.open.partner

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.fragment.app.viewModels
import com.init_android.R
import com.init_android.app.data.request.project.RequestProjectMember
import com.init_android.app.data.response.project.approve.ResponseProjectMember
import com.init_android.app.presentation.ui.open.partner.adapter.PartnerAdapter
import com.init_android.app.presentation.ui.open.viewmodel.ProjectViewModel
import com.init_android.databinding.FragmentPlanBinding
import com.playtogether_android.app.presentation.base.BaseFragment


class PlanFragment : BaseFragment<FragmentPlanBinding>(R.layout.fragment_plan) {

    private val projectViewModel: ProjectViewModel by viewModels()
    private lateinit var partnerAdapter: PartnerAdapter


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initNetwork()

    }

    override fun onResume() {
        super.onResume()
        initNetwork()
    }

    private fun initNetwork() {
        Log.d("TEst", "TEST")
        val requestProjectMember = RequestProjectMember(pNum = 1)
        projectViewModel.postProjectMember(requestProjectMember)
        partnerAdapter = PartnerAdapter(1)
        binding.rvPlan.adapter = partnerAdapter
        projectViewModel.projectMember.observe(viewLifecycleOwner) {
            partnerAdapter.setQuestionPost((it.approvedPlan) as MutableList<ResponseProjectMember.ApprovedPlan>)
        }
    }
}