package com.init_android.app.presentation.ui.open.partner

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import com.init_android.R
import com.init_android.app.data.request.project.RequestProjectMember
import com.init_android.app.data.response.project.approve.ResponseProjectMember
import com.init_android.app.presentation.ui.main.MainViewModel
import com.init_android.app.presentation.ui.open.partner.adapter.PartnerPlanAdapter
import com.init_android.app.presentation.ui.open.viewmodel.ProjectViewModel
import com.init_android.databinding.FragmentPlanBinding
import com.playtogether_android.app.presentation.base.BaseFragment


class PlanFragment : BaseFragment<FragmentPlanBinding>(R.layout.fragment_plan) {

    private val mainViewModel: MainViewModel by activityViewModels()
    private val projectViewModel: ProjectViewModel by viewModels()
    private lateinit var partnerPlanAdapter: PartnerPlanAdapter


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


    }

    override fun onResume() {
        super.onResume()
        initNetwork()
    }

    private fun initNetwork() {
        val pNum = mainViewModel.projectNum.value ?: 1
        val requestProjectMember = RequestProjectMember(pNum = pNum)
        projectViewModel.postProjectMember(requestProjectMember)
        partnerPlanAdapter = PartnerPlanAdapter(1)
        binding.rvPlan.adapter = partnerPlanAdapter
        projectViewModel.projectMember.observe(viewLifecycleOwner) {
            partnerPlanAdapter.setQuestionPost((it.approvedPlan) as MutableList<ResponseProjectMember.ApprovedPlan>)
        }
    }
}