package com.init_android.app.presentation.ui.home

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.fragment.app.viewModels
import com.init_android.R
import com.init_android.app.data.model.ProjectItemData
import com.init_android.app.data.request.RequestHome
import com.init_android.app.data.response.ProjectData
import com.init_android.app.presentation.ui.home.adapter.ProjectItemRVAdapter
import com.init_android.app.presentation.ui.home.adapter.ProjectItemVPAdapter
import com.init_android.app.presentation.ui.home.viewmodel.HomeViewModel
import com.init_android.app.util.DateUtil
import com.init_android.databinding.FragmentHomeBinding
import com.playtogether_android.app.presentation.base.BaseFragment


class HomeFragment : BaseFragment<FragmentHomeBinding>(R.layout.fragment_home) {

    val includeList = mutableListOf<ProjectItemData>()
    val recoList = mutableListOf<ProjectItemData>()

    private val homeViewModel: HomeViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        tryGetHomeProject()
    }

    // 서버 통신 - 데이터 존재 여부 검사
    private fun tryGetHomeProject() {

        val requestHome = RequestHome(
            mNum = 1,
            mPosition = 3,
            mLevel = 0
        )

        homeViewModel.postHomeData(requestHome)


        homeViewModel.homeData.observe(viewLifecycleOwner) {
            val include_data = it.list_belong
            val includeList_writer = it.writerInfo_belong
            val recommend_data = it.list_recommend
            val recommendList_writer = it.writerInfo_recommend

            // 소속 프로젝트
            if (include_data != null) {
                for (i in 0 until includeList.size) {
                    val totalNum =
                        include_data[i].pPlan!! + include_data[i].pDesign!! + include_data[i].pAos!! + include_data[i].pIos!! + include_data[i].pWeb!! + include_data[i].pGame!! + include_data[i].pServer!!
                    includeList.add(
                        ProjectItemData(
                            include_data[i].pType!!,
                            include_data[i].pTitle!!,
                            include_data[i].pOnOff!!,
                            totalNum,
                            DateUtil().dateToString(include_data[i].pStart!!).replace("-","."),
                            DateUtil().dateToString(include_data[i].pDue!!).replace("-","."),
                            includeList_writer?.get(0)?.get(i)!!.mName!!,
                            include_data[i].pState!!
                        )
                    )
                }
            }


            Log.d("helpme",includeList.toString())
            // 추천 프로젝트
        }

        //recoList.add(ProjectItemData("웹","서울여대 해커톤 나가실 분","노원구","1/4","2022.06.11","2022.06.24","장윤정","D-DAY"))
        //recoList.add(ProjectItemData("웹","서울여대 해커톤 나가실 분","노원구","1/4","2022.06.11","2022.06.24","장윤정","D-DAY"))

        checkUI()
    }

    // 데이터 존재 여부 검사
    private fun checkUI() {
        // 소속 프로젝트
        initIncludeProject()

        // 추천 프로젝트
        initRecoProject()
    }

    // 소속 프로젝트 초기화 - (viewpager2)
    private fun initIncludeProject() {
        if (includeList.size < 1) { // 리스트 한 개 미만
            binding.tvIncludeShowAll.visibility = View.GONE // 전체보기 gone

            binding.cardViewNoInclude.visibility = View.VISIBLE // cardview on
            binding.vpIncludeProject.visibility = View.GONE // vp gone
            binding.indicatorInclude.visibility = View.GONE // indicator gone
        } else { //리스트 한 개 이상
            binding.tvIncludeShowAll.visibility = View.VISIBLE // 전체보기 on

            binding.cardViewNoInclude.visibility = View.GONE // cardview gone
            binding.vpIncludeProject.visibility = View.VISIBLE // vp on
            binding.indicatorInclude.visibility = View.VISIBLE // indicator on

            initViewPager() // 뷰페이저 연결하기
        }
    }

    // 추천 프로젝트 초기화 - (recyclerview)
    private fun initRecoProject() {
        // 리스트 한 개 미만 -> cardview On

        // 리스트 한 개 이상 -> RecyclerView On
        initRecyclerView()
    }

    private fun initViewPager() {
        val vpAdapter = ProjectItemVPAdapter(includeList)
        binding.vpIncludeProject.adapter = vpAdapter
        binding.indicatorInclude.attachTo(binding.vpIncludeProject)
    }

    private fun initRecyclerView() {
        val rvAdapter = ProjectItemRVAdapter()
        rvAdapter.updateProjectList(recoList)
        binding.rvRecommendProject.adapter = rvAdapter
    }
}