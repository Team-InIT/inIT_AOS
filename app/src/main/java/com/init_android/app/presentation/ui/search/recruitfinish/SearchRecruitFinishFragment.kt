package com.init_android.app.presentation.ui.search.recruitfinish

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import com.init_android.R
import com.init_android.app.data.model.ProjectItemData
import com.init_android.app.presentation.ui.home.adapter.ProjectItemRVAdapter
import com.init_android.app.presentation.ui.search.SearchViewModel
import com.init_android.app.util.DateUtil
import com.init_android.databinding.FragmentSearchRecruitFinishBinding
import com.playtogether_android.app.presentation.base.BaseFragment

class SearchRecruitFinishFragment:BaseFragment<FragmentSearchRecruitFinishBinding>(R.layout.fragment_search_recruit_finish) {

    private val searchViewModel : SearchViewModel by viewModels()
    private val myProjectItemDataList = mutableListOf<ProjectItemData>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initAdapter()

    }

    private fun initAdapter(){
        val adapter = ProjectItemRVAdapter(requireContext())
        binding.rvProject.adapter = adapter

        searchViewModel.getNotRecruitingProject()

        searchViewModel.notRecruitingProject.observe(viewLifecycleOwner){
            val projectItemDataList = it.notRecruitingProject?.toMutableList()
            val writerList = it.writer!!.toMutableList()
            for (i in projectItemDataList!!.indices){
                val data = projectItemDataList[i]

                val totalNum = data.pPlan + data.pDesign + data.pAos + data.pIos + data.pWeb + data.pGame + data.pServer
                val pStartDate =  DateUtil().dateToString(data.pStart).replace("-",".")
                val pEndDate = DateUtil().dateToString(data.pDue).replace("-",".")

                myProjectItemDataList.add(ProjectItemData(data.pType,data.pDescription,data.pOnOff,totalNum,pStartDate,pEndDate,writerList[i].mName,
                    data.pState!!,data.pNum,data.mNum,""))
            }

            adapter.setProjectList(myProjectItemDataList)
            adapter.submitList(myProjectItemDataList)
        }

    }
}