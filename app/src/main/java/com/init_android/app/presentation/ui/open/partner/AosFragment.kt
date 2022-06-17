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
import com.init_android.app.presentation.ui.open.partner.adapter.PartnerAosAdapter
import com.init_android.app.presentation.ui.open.partner.adapter.PartnerDesignAdapter
import com.init_android.app.presentation.ui.open.viewmodel.ProjectViewModel
import com.init_android.databinding.FragmentAosBinding
import com.playtogether_android.app.presentation.base.BaseFragment

class AosFragment : BaseFragment<FragmentAosBinding>(R.layout.fragment_aos) {

    private val projectViewModel: ProjectViewModel by viewModels()
    private lateinit var partnerAosAdapter: PartnerAosAdapter

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
        partnerAosAdapter = PartnerAosAdapter(1)
        binding.rvAos.adapter = partnerAosAdapter
        projectViewModel.projectMember.observe(viewLifecycleOwner) {
            partnerAosAdapter.setQuestionPost((it.approvedAos) as MutableList<ResponseProjectMember.ApprovedAo>)
        }
    }
}