package com.init_android.app.presentation.ui.search.partner

import android.os.Bundle
import android.view.View
import com.init_android.R
import com.init_android.app.data.model.PartnerData
import com.init_android.app.presentation.ui.search.adapter.SearchPartnerAdapter
import com.init_android.databinding.FragmentSearchPartnerBinding
import com.playtogether_android.app.presentation.base.BaseFragment

class SearchPartnerFragment:BaseFragment<FragmentSearchPartnerBinding>(R.layout.fragment_search_partner) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initAdapter()
    }

    private fun initAdapter(){
        val adapter = SearchPartnerAdapter(requireContext())
        binding.rvPartner.adapter = adapter
        adapter.submitList(
            listOf(PartnerData("장윤정","어쩌궁",0,"yoon@gmail.com","서울여자대학교",))
        )
    }
}