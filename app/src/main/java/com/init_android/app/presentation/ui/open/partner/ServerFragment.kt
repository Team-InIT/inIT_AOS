package com.init_android.app.presentation.ui.open.partner

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.fragment.app.viewModels
import com.init_android.R
import com.init_android.app.data.request.project.RequestProjectMember
import com.init_android.app.data.response.project.approve.ResponseProjectMember
import com.init_android.app.presentation.ui.open.partner.adapter.PartnerServerAdapter
import com.init_android.app.presentation.ui.open.viewmodel.ProjectViewModel
import com.init_android.databinding.FragmentServerBinding
import com.playtogether_android.app.presentation.base.BaseFragment


class ServerFragment : BaseFragment<FragmentServerBinding>(R.layout.fragment_server) {

    private val projectViewModel: ProjectViewModel by viewModels()
    private lateinit var partnerServerAdapter: PartnerServerAdapter

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
        partnerServerAdapter = PartnerServerAdapter(1)
        binding.rvAos.adapter = partnerServerAdapter
        projectViewModel.projectMember.observe(viewLifecycleOwner) {
            partnerServerAdapter.setQuestionPost((it.approvedServer) as MutableList<ResponseProjectMember.ApprovedServer>)
        }
    }
}