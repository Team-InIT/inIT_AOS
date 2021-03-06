package com.init_android.app.presentation.ui.search.recruiting

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.fragment.app.viewModels
import com.init_android.R
import com.init_android.app.data.model.ProjectItemData
import com.init_android.app.data.model.SelectableData
import com.init_android.app.presentation.ui.home.adapter.ProjectItemRVAdapter
import com.init_android.app.presentation.ui.search.SearchViewModel
import com.init_android.app.util.CustomBottomSheetDialog
import com.init_android.app.util.DateUtil
import com.init_android.databinding.FragmentSearchRecruitingBinding
import com.playtogether_android.app.presentation.base.BaseFragment

class SearchRecruitingFragment:BaseFragment<FragmentSearchRecruitingBinding>(R.layout.fragment_search_recruiting) {

    private val searchViewModel : SearchViewModel by viewModels()
    private val partBottomSheetDialog = CustomBottomSheetDialog("프로젝트 타입","완료")
    private val myProjectItemDataList = mutableListOf<ProjectItemData>()
    private val searchResultList = mutableListOf<ProjectItemData>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // initAdapter()
        clickListener()
        initRefreshEvent()
        initSearchEvent()

    }

    private fun initAdapter(){
        val adapter = ProjectItemRVAdapter(requireContext())
        binding.rvProject.adapter = adapter

        searchViewModel.getRecruitingProject()

        searchViewModel.recruitingData.observe(viewLifecycleOwner){
            myProjectItemDataList.clear()

            val projectItemDataList = it.projectInfo?.toMutableList()
            for (i in projectItemDataList!!.indices){
                val data = projectItemDataList[i]

                val totalNum = data.recruitingProject.pPlan + data.recruitingProject.pDesign + data.recruitingProject.pAos + data.recruitingProject.pIos + data.recruitingProject.pWeb + data.recruitingProject.pGame + data.recruitingProject.pServer
                val pStartDate =  DateUtil().dateToString(data.recruitingProject.pStart!!).replace("-",".")
                val pEndDate = DateUtil().dateToString(data.recruitingProject.pDue).replace("-",".")

                myProjectItemDataList.add(ProjectItemData(data.recruitingProject.pType!!,data.recruitingProject.pTitle!!,data.recruitingProject.pOnOff!!,totalNum,pStartDate,pEndDate,data.recruitingProject.Member.mName,
                    data.recruitingProject.pState!!,data.recruitingProject.pNum!!,data.recruitingProject.mNum!!,data.projectState))
            }


            myProjectItemDataList.distinct()
            /*if (searchViewModel.searchFlags.value == true){
                adapter.submitList(null)
            }else{
                adapter.setProjectList(myProjectItemDataList)
                adapter.submitList(myProjectItemDataList)
            }*/
            adapter.setProjectList(myProjectItemDataList)
            adapter.submitList(myProjectItemDataList)
        }

    }

    override fun onResume() {
        super.onResume()
        initAdapter()
    }

    private fun initRefreshEvent(){
        binding.ivRefresh.setOnClickListener {
            binding.ivRefresh.visibility = View.GONE // 초기화 버튼 활성화
            binding.layoutProjectType.background =
                resources.getDrawable(R.drawable.rectangle_stroke_gray_radius_16, null)
        }
    }

    // 키워드로 검색하기
    private fun initSearchEvent(){
        binding.btnSearch.setOnClickListener {

            searchViewModel.searchResultData.removeObservers(this)
            searchViewModel.recruitingData.removeObservers(this)

            val adapter = ProjectItemRVAdapter(requireContext())
            binding.rvProject.adapter = adapter

            searchViewModel.recruitingData.removeObservers(this)
            // 서버통신
            searchViewModel.postSearchIng(binding.etvSearch.text.toString())

            // 데이터 받아오기
            searchViewModel.searchResultData.observe(viewLifecycleOwner){
                searchResultList.clear()
                val projectItemDataList = it.projectInfo?.toMutableList()
                for (i in projectItemDataList!!.indices){
                    val data = projectItemDataList[i]

                    val totalNum = data.recruitingProject.pPlan + data.recruitingProject.pDesign + data.recruitingProject.pAos + data.recruitingProject.pIos + data.recruitingProject.pWeb + data.recruitingProject.pGame + data.recruitingProject.pServer
                    val pStartDate =  DateUtil().dateToString(data.recruitingProject.pStart!!).replace("-",".")
                    val pEndDate = DateUtil().dateToString(data.recruitingProject.pDue).replace("-",".")

                    searchResultList.add(ProjectItemData(data.recruitingProject.pType!!,data.recruitingProject.pTitle!!,data.recruitingProject.pOnOff!!,totalNum,pStartDate,pEndDate,data.recruitingProject.Member.mName,
                        data.recruitingProject.pState!!,data.recruitingProject.pNum!!,data.recruitingProject.mNum!!,data.projectState))
                }

                searchResultList.distinct()
                adapter.setProjectList(searchResultList)
                adapter.submitList(searchResultList)
            }

        }
    }

    // 바텀 시트 이벤트
    private fun clickListener() {
        //더미 데이터 넣는 부분
        var partData = mutableListOf(
            SelectableData(1, "웹", false),
            SelectableData(2, "모바일", false),
            SelectableData(3, "게임", false),
        )
        partBottomSheetDialog.setDataList(partData)

        //버튼 클릭해서 바텀시트 생성되는 부분
        binding.layoutProjectType.setOnClickListener {
            partBottomSheetDialog.show(
                childFragmentManager,
                partBottomSheetDialog.tag
            )

            //클릭 완료되었을때 일어나는 리스너
            partBottomSheetDialog.setCompleteListener {
                val part = partBottomSheetDialog.getSelectedData()
                //뷰모델에 넣어주는 코드
                // mainViewModel.part.value = part?.name
                // mainViewModel.partNum.value = part?.id
                partBottomSheetDialog.binding.btnBottomsheetCancel
                //알럿
                // initAlert()
                binding.ivRefresh.visibility = View.VISIBLE // 초기화 버튼 활성화
                binding.layoutProjectType.background = resources.getDrawable(R.drawable.rectangle_stroke_main_radius_16,null)

                partBottomSheetDialog.binding.btnBottomsheetComplete.setOnClickListener {
                    Log.d("클릭", "완료")
                    // initAlert()
                }
            }
        }
    }
}
