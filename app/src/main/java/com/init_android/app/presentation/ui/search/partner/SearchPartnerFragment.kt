package com.init_android.app.presentation.ui.search.partner

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import com.init_android.R
import com.init_android.app.data.response.UserList
import com.init_android.app.presentation.ui.search.SearchViewModel
import com.init_android.app.presentation.ui.search.adapter.SearchPartnerAdapter
import com.init_android.databinding.FragmentSearchPartnerBinding
import com.playtogether_android.app.presentation.base.BaseFragment

class SearchPartnerFragment:BaseFragment<FragmentSearchPartnerBinding>(R.layout.fragment_search_partner) {

    private val searchViewModel : SearchViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initAdapter()
    }

    private fun initAdapter(){
        val adapter = SearchPartnerAdapter(requireContext())
        binding.rvPartner.adapter = adapter

        // 서버 통신
        searchViewModel.getUserAll()

        // 리스트 불러오기
        searchViewModel.partnerData.observe(viewLifecycleOwner){
            val data = it.userList
            adapter.submitList(data.toMutableList())
        }
    }
}