package com.init_android.app.presentation.ui.open.partner

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.init_android.R
import com.init_android.app.data.request.project.RequestProjectMember
import com.init_android.app.data.response.project.approve.ResponseProjectMember
import com.init_android.app.presentation.ui.open.partner.adapter.PartnerIosAdapter
import com.init_android.app.presentation.ui.open.partner.adapter.PartnerWebAdapter
import com.init_android.app.presentation.ui.open.viewmodel.ProjectViewModel
import com.init_android.databinding.FragmentWebBinding
import com.playtogether_android.app.presentation.base.BaseFragment


class WebFragment : BaseFragment<FragmentWebBinding>(R.layout.fragment_web) {

    private val projectViewModel: ProjectViewModel by viewModels()
    private lateinit var partnerWebAdapter: PartnerWebAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

    }

    override fun onResume() {
        super.onResume()
        initNetwork()
    }

    private fun initNetwork() {
        Log.d("TEst", "TEST")
        val requestProjectMember = RequestProjectMember(pNum = 1)
        projectViewModel.postProjectMember(requestProjectMember)
        partnerWebAdapter = PartnerWebAdapter(1)
        binding.rvAos.adapter = partnerWebAdapter
        projectViewModel.projectMember.observe(viewLifecycleOwner) {
            partnerWebAdapter.setQuestionPost((it.approvedWeb) as MutableList<ResponseProjectMember.ApprovedWeb>)
        }
    }
}