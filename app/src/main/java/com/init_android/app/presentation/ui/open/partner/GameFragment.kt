package com.init_android.app.presentation.ui.open.partner

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.fragment.app.viewModels
import com.init_android.R
import com.init_android.app.data.request.project.RequestProjectMember
import com.init_android.app.data.response.project.approve.ResponseProjectMember
import com.init_android.app.presentation.ui.open.partner.adapter.PartnerGameAdapter
import com.init_android.app.presentation.ui.open.partner.adapter.PartnerIosAdapter
import com.init_android.app.presentation.ui.open.viewmodel.ProjectViewModel
import com.init_android.databinding.FragmentGameBinding
import com.playtogether_android.app.presentation.base.BaseFragment


class GameFragment : BaseFragment<FragmentGameBinding>(R.layout.fragment_game) {

    private val projectViewModel: ProjectViewModel by viewModels()
    private lateinit var partnerGameAdapter: PartnerGameAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        Log.d("testing","game")
    }

    override fun onResume() {
        super.onResume()
        initNetwork()
    }

    private fun initNetwork() {
        Log.d("TEst", "TEST")
        val requestProjectMember = RequestProjectMember(pNum = 1)
        projectViewModel.postProjectMember(requestProjectMember)
        partnerGameAdapter = PartnerGameAdapter(1)
        binding.rvAos.adapter = partnerGameAdapter
        projectViewModel.projectMember.observe(viewLifecycleOwner) {
            partnerGameAdapter.setQuestionPost((it.approvedIos) as MutableList<ResponseProjectMember.ApprovedGame>)
        }
    }
}