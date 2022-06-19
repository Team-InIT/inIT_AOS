package com.init_android.app.presentation.ui.open.partner

import android.os.Bundle
import android.view.View
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import com.init_android.R
import com.init_android.app.data.request.project.RequestProjectMember
import com.init_android.app.data.response.project.ready.ResponseReadyGame
import com.init_android.app.presentation.ui.main.MainViewModel
import com.init_android.app.presentation.ui.open.partner.adapter.PartnerGameAdapter
import com.init_android.app.presentation.ui.open.partner.adapter.ready.ReadyGameAdapter
import com.init_android.app.presentation.ui.open.viewmodel.ProjectViewModel
import com.init_android.databinding.FragmentGameBinding
import com.playtogether_android.app.presentation.base.BaseFragment


class GameFragment : BaseFragment<FragmentGameBinding>(R.layout.fragment_game) {

    private val mainViewModel: MainViewModel by activityViewModels()
    private val projectViewModel: ProjectViewModel by viewModels()
    private lateinit var partnerGameAdapter: PartnerGameAdapter
    private lateinit var readyGameAdapter : ReadyGameAdapter

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
        projectViewModel.postMyCrewGame(requestProjectMember)
        readyGameAdapter = ReadyGameAdapter(1)
        binding.rvApproveAos.adapter = readyGameAdapter
        projectViewModel.myCrewGame.observe(viewLifecycleOwner) {
            readyGameAdapter.setQuestionPost((it.waitingGame) as MutableList<ResponseReadyGame.WaitingGame>)
        }
    }

    private fun initNetwork() {
        val pNum = mainViewModel.projectNum.value ?: 1
        val requestProjectMember = RequestProjectMember(pNum = pNum)
        projectViewModel.postMyCrewGame(requestProjectMember)
        partnerGameAdapter = PartnerGameAdapter(1)
        binding.rvAos.adapter = partnerGameAdapter
        projectViewModel.myCrewGame.observe(viewLifecycleOwner) {
            partnerGameAdapter.setQuestionPost((it.approvedGame) as MutableList<ResponseReadyGame.ApprovedGame>)
        }
    }
}