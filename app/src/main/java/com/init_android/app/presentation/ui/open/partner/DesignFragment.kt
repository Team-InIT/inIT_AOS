package com.init_android.app.presentation.ui.open.partner

import android.os.Bundle
import android.view.View
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import com.init_android.R
import com.init_android.app.data.request.project.RequestProjectMember
import com.init_android.app.data.response.project.ready.ResponseReadyDesign
import com.init_android.app.presentation.ui.main.MainViewModel
import com.init_android.app.presentation.ui.open.partner.adapter.PartnerDesignAdapter
import com.init_android.app.presentation.ui.open.partner.adapter.ready.ReadyDesignAdapter
import com.init_android.app.presentation.ui.open.viewmodel.ProjectViewModel
import com.init_android.databinding.FragmentDesignBinding
import com.playtogether_android.app.presentation.base.BaseFragment


class DesignFragment : BaseFragment<FragmentDesignBinding>(R.layout.fragment_design) {

    private val projectViewModel: ProjectViewModel by viewModels()
    private val mainViewModel: MainViewModel by activityViewModels()
    private lateinit var partnerDesignAdapter: PartnerDesignAdapter
    private lateinit var readyDesignAdapter : ReadyDesignAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

    }

    override fun onResume() {
        super.onResume()
        initNetwork()
        initApprove()
        initSetting()
    }

    private fun initSetting() {
        val pNum = mainViewModel.projectNum.value ?: 1
        if(pNum != 1) {
            binding.tvPartnerApprove.visibility = View.GONE
            binding.tvPartnerApproveNum.visibility = View.GONE
            binding.rvApproveAos.visibility = View.GONE
        }
        else {
            binding.tvPartnerApprove.visibility = View.VISIBLE
            binding.tvPartnerApproveNum.visibility = View.VISIBLE
            binding.rvApproveAos.visibility = View.VISIBLE
        }
    }

    //승인 대기 중
    private fun initApprove() {
        val pNum = mainViewModel.projectNum.value ?: 1
        val requestProjectMember = RequestProjectMember(pNum = pNum)
        projectViewModel.postMyCrewDesign(requestProjectMember)
        readyDesignAdapter = ReadyDesignAdapter(1)
        binding.rvApproveAos.adapter = readyDesignAdapter
        projectViewModel.myCrewDesign.observe(viewLifecycleOwner) {
            readyDesignAdapter.setQuestionPost((it.waitingDesign) as MutableList<ResponseReadyDesign.WaitingDesign>)
        }
    }

    private fun initNetwork() {
        val pNum = mainViewModel.projectNum.value ?: 1
        val requestProjectMember = RequestProjectMember(pNum = pNum)
        projectViewModel.postMyCrewDesign(requestProjectMember)
        partnerDesignAdapter = PartnerDesignAdapter(1)
        binding.rvAos.adapter = partnerDesignAdapter
        projectViewModel.myCrewDesign.observe(viewLifecycleOwner) {
            partnerDesignAdapter.setQuestionPost((it.approvedDesign) as MutableList<ResponseReadyDesign.ApprovedDesign>)
        }
    }
}