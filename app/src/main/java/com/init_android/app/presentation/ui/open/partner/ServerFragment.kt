package com.init_android.app.presentation.ui.open.partner

import android.os.Bundle
import android.view.View
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import com.init_android.R
import com.init_android.app.data.request.project.RequestProjectMember
import com.init_android.app.data.response.project.ready.ResponseReadyServer
import com.init_android.app.presentation.ui.main.MainViewModel
import com.init_android.app.presentation.ui.open.partner.adapter.PartnerServerAdapter
import com.init_android.app.presentation.ui.open.partner.adapter.ready.ReadyServerAdapter
import com.init_android.app.presentation.ui.open.viewmodel.ProjectViewModel
import com.init_android.databinding.FragmentServerBinding
import com.playtogether_android.app.presentation.base.BaseFragment


class ServerFragment : BaseFragment<FragmentServerBinding>(R.layout.fragment_server) {

    private val mainViewModel: MainViewModel by activityViewModels()
    private val projectViewModel: ProjectViewModel by viewModels()
    private lateinit var partnerServerAdapter: PartnerServerAdapter
    private lateinit var readyServerAdapter: ReadyServerAdapter

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
        projectViewModel.postMyCrewServer(requestProjectMember)
        readyServerAdapter = ReadyServerAdapter(1)
        binding.rvApproveAos.adapter = readyServerAdapter
        projectViewModel.myCrewServer.observe(viewLifecycleOwner) {
            readyServerAdapter.setQuestionPost((it.waitingServer) as MutableList<ResponseReadyServer.WaitingServer>)
        }
    }


    private fun initNetwork() {
        val pNum = mainViewModel.projectNum.value ?: 1
        val requestProjectMember = RequestProjectMember(pNum = pNum)
        projectViewModel.postMyCrewServer(requestProjectMember)
        partnerServerAdapter = PartnerServerAdapter(1)
        binding.rvAos.adapter = partnerServerAdapter
        projectViewModel.myCrewServer.observe(viewLifecycleOwner) {
            partnerServerAdapter.setQuestionPost((it.approvedServer) as MutableList<ResponseReadyServer.ApprovedServer>)
        }
    }
}