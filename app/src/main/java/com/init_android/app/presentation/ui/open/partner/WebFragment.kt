package com.init_android.app.presentation.ui.open.partner

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import com.init_android.R
import com.init_android.app.data.request.project.RequestProjectMember
import com.init_android.app.data.response.project.approve.ResponseProjectMember
import com.init_android.app.data.response.project.ready.ResponseReadyWeb
import com.init_android.app.presentation.ui.main.MainViewModel
import com.init_android.app.presentation.ui.open.partner.adapter.PartnerIosAdapter
import com.init_android.app.presentation.ui.open.partner.adapter.PartnerWebAdapter
import com.init_android.app.presentation.ui.open.viewmodel.ProjectViewModel
import com.init_android.databinding.FragmentWebBinding
import com.playtogether_android.app.presentation.base.BaseFragment


class WebFragment : BaseFragment<FragmentWebBinding>(R.layout.fragment_web) {

    private val mainViewModel: MainViewModel by activityViewModels()
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