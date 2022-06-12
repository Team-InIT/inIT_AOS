package com.init_android.app.presentation.ui.search.recruiting

import android.os.Bundle
import android.view.View
import com.init_android.R
import com.init_android.app.data.model.ProjectItemData
import com.init_android.app.presentation.ui.home.adapter.ProjectItemRVAdapter
import com.init_android.databinding.FragmentSearchRecruitingBinding
import com.playtogether_android.app.presentation.base.BaseFragment

class SearchRecruitingFragment:BaseFragment<FragmentSearchRecruitingBinding>(R.layout.fragment_search_recruiting) {


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initAdapter()

    }

    private fun initAdapter(){
        val adapter = ProjectItemRVAdapter(requireContext())
        binding.rvProject.adapter = adapter
        adapter.submitList(
            listOf(
                ProjectItemData(0,"졸업프로젝트 같이 할 사람",0,3,"2021.03.05","2021.04.05",
                "이혜빈",0,-1,-1)
            )
        )
    }
}
