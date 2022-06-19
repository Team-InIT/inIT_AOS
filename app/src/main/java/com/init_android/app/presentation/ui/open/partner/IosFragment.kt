package com.init_android.app.presentation.ui.open.partner

import android.os.Bundle
import android.view.View
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import com.init_android.R
import com.init_android.app.data.request.project.RequestProjectMember
import com.init_android.app.data.response.project.ready.ResponseReadyIos
import com.init_android.app.presentation.ui.main.MainViewModel
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
        projectViewModel.postMyCrewIos(requestProjectMember)
        partnerIosAdapter = PartnerIosAdapter(1)
        binding.rvAos.adapter = partnerIosAdapter
        projectViewModel.myCrewIos.observe(viewLifecycleOwner) {
            partnerIosAdapter.setQuestionPost((it.approvedIos) as MutableList<ResponseReadyIos.ApprovedIos>)
        }
    }
}