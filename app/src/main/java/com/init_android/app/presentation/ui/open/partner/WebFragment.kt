package com.init_android.app.presentation.ui.open.partner

import android.os.Bundle
import android.view.View
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import com.init_android.R
import com.init_android.app.data.request.project.RequestProjectMember
import com.init_android.app.data.response.project.ready.ResponseReadyWeb
import com.init_android.app.presentation.ui.main.MainViewModel
import com.init_android.app.presentation.ui.open.partner.adapter.PartnerWebAdapter
import com.init_android.app.presentation.ui.open.partner.adapter.ready.ReadyWebAdapter
import com.init_android.app.presentation.ui.open.viewmodel.ProjectViewModel
import com.init_android.databinding.FragmentWebBinding
import com.playtogether_android.app.presentation.base.BaseFragment


class WebFragment : BaseFragment<FragmentWebBinding>(R.layout.fragment_web) {

    private val mainViewModel: MainViewModel by activityViewModels()
    private val projectViewModel: ProjectViewModel by viewModels()
    private lateinit var partnerWebAdapter: PartnerWebAdapter
    private lateinit var readyWebAdapter: ReadyWebAdapter

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
        projectViewModel.postMyCrewWeb(requestProjectMember)
        readyWebAdapter = ReadyWebAdapter(1)
        binding.rvApproveAos.adapter = readyWebAdapter
        projectViewModel.myCrewWeb.observe(viewLifecycleOwner) {
            readyWebAdapter.setQuestionPost((it.waitingWeb) as MutableList<ResponseReadyWeb.WaitingWeb>)
        }
    }

    private fun initNetwork() {
        val pNum = mainViewModel.projectNum.value ?: 1
        val requestProjectMember = RequestProjectMember(pNum = pNum)
        projectViewModel.postMyCrewWeb(requestProjectMember)
        partnerWebAdapter = PartnerWebAdapter(1)
        binding.rvAos.adapter = partnerWebAdapter
        projectViewModel.myCrewWeb.observe(viewLifecycleOwner) {
            partnerWebAdapter.setQuestionPost((it.approvedWeb) as MutableList<ResponseReadyWeb.ApprovedWeb>)
        }
    }
}