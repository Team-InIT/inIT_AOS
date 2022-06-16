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
import com.init_android.app.presentation.ui.open.partner.adapter.PartnerDesignAdapter
import com.init_android.app.presentation.ui.open.partner.adapter.PartnerPlanAdapter
import com.init_android.app.presentation.ui.open.viewmodel.ProjectViewModel
import com.init_android.databinding.FragmentDesignBinding
import com.playtogether_android.app.presentation.base.BaseFragment


class DesignFragment : BaseFragment<FragmentDesignBinding>(R.layout.fragment_design) {

    private val projectViewModel: ProjectViewModel by viewModels()
    private lateinit var partnerDesignAdapter: PartnerDesignAdapter

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
        partnerDesignAdapter = PartnerDesignAdapter(1)
        binding.rvAos.adapter = partnerDesignAdapter
        projectViewModel.projectMember.observe(viewLifecycleOwner) {
            partnerDesignAdapter.setQuestionPost((it.approvedDesign) as MutableList<ResponseProjectMember.ApprovedDesign>)
        }
    }
}