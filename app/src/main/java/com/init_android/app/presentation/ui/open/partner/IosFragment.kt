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
import com.init_android.app.presentation.ui.main.MainViewModel
import com.init_android.app.presentation.ui.open.partner.adapter.PartnerDesignAdapter
import com.init_android.app.presentation.ui.open.partner.adapter.PartnerIosAdapter
import com.init_android.app.presentation.ui.open.viewmodel.ProjectViewModel
import com.init_android.databinding.FragmentIosBinding
import com.playtogether_android.app.presentation.base.BaseFragment

class IosFragment : BaseFragment<FragmentIosBinding>(R.layout.fragment_ios){

    private val mainViewModel: MainViewModel by activityViewModels()
    private val projectViewModel: ProjectViewModel by viewModels()
    private lateinit var partnerIosAdapter: PartnerIosAdapter

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
        partnerIosAdapter = PartnerIosAdapter(1)
        binding.rvAos.adapter = partnerIosAdapter
        projectViewModel.projectMember.observe(viewLifecycleOwner) {
            partnerIosAdapter.setQuestionPost((it.approvedIos) as MutableList<ResponseProjectMember.ApprovedIo>)
        }
    }
}