package com.init_android.app.presentation.ui.home

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.activity.viewModels
import androidx.fragment.app.FragmentActivity
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import com.init_android.R
import com.init_android.app.data.model.ProjectItemData
import com.init_android.app.data.request.RequestHome
import com.init_android.app.data.response.ResponseSignIn
import com.init_android.app.presentation.ui.home.adapter.ProjectItemRVAdapter
import com.init_android.app.presentation.ui.home.adapter.ProjectItemVPAdapter
import com.init_android.app.presentation.ui.home.recommendproject.RecommendProjectActivity
import com.init_android.app.presentation.ui.home.signin.viewmodel.SignViewModel
import com.init_android.app.presentation.ui.home.viewmodel.HomeViewModel
import com.init_android.app.presentation.ui.main.MainViewModel
import com.init_android.app.presentation.ui.open.project.OpenProjectActivity
import com.init_android.app.util.DateUtil

import com.init_android.databinding.FragmentHomeBinding
import com.playtogether_android.app.presentation.base.BaseFragment
import kotlin.math.sign


class HomeFragment : BaseFragment<FragmentHomeBinding>(R.layout.fragment_home) {

    val includeList = mutableListOf<ProjectItemData>()
    val recoList = mutableListOf<ProjectItemData>()

    private val mainViewModel : MainViewModel by activityViewModels()
    private val homeViewModel: HomeViewModel by viewModels()


    override fun onResume() {
        super.onResume()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initShowAllEvent()
        tryGetHomeProject()
        writeBtnClick()
    }

    // 서버 통신 - 데이터 존재 여부 검사
    private fun tryGetHomeProject() {
        mainViewModel.mId.observe(viewLifecycleOwner) {
            val mNum = it

            mainViewModel.mPosition.observe(viewLifecycleOwner) {
                val mPosition = it

                mainViewModel.mLevel.observe(viewLifecycleOwner) {
                    val mLevel = it

                    val requestHome = RequestHome(
                        mNum = mNum,
                        mPosition = mPosition,
                        mLevel = mLevel
                    )
                    homeViewModel.postHomeData(requestHome)
                }
            }
        }


        homeViewModel.homeData.observe(viewLifecycleOwner) {
            val include_data = it.list_belong
            val includeList_writer = it.writerInfo_belong
            val recommend_data = it.list_recommend
            val recommendList_writer = it.writerInfo_recommend

            // 소속 프로젝트
            if (include_data != null) {
                for (i in 0 until include_data.size) {
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
                            includeList_writer?.get(i)?.get(0)!!.mName!!,
                            include_data[i].pState!!,
                            include_data[i].pNum!!,
                            include_data[i].mNum!!
                        )
                    )
                }
            }

            // 추천 프로젝트
            if (recommend_data != null) {
                for (i in 0 until recommend_data.size) {
                    val totalNum =
                        recommend_data[i].pPlan!! + recommend_data[i].pDesign!! + recommend_data[i].pAos!! + recommend_data[i].pIos!! + recommend_data[i].pWeb!! + recommend_data[i].pGame!! + recommend_data[i].pServer!!
                    recoList.add(
                        ProjectItemData(
                            recommend_data[i].pType!!,
                            recommend_data[i].pTitle!!,
                            recommend_data[i].pOnOff!!,
                            totalNum,
                            DateUtil().dateToString(recommend_data[i].pStart!!).replace("-","."),
                            DateUtil().dateToString(recommend_data[i].pDue!!).replace("-","."),
                            recommendList_writer?.get(i)?.get(0)!!.mName!!,
                            recommend_data[i].pState!!,
                            recommend_data[i].pNum!!,
                            recommend_data[i].mNum!!
                        )
                    )
                }
            }

            checkUI()
        }

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
            binding.cardViewNoInclude.visibility = View.VISIBLE // cardview on
            binding.vpIncludeProject.visibility = View.GONE // vp gone
            binding.indicatorInclude.visibility = View.GONE // indicator gone
        } else { //리스트 한 개 이상

            binding.cardViewNoInclude.visibility = View.GONE // cardview gone
            binding.vpIncludeProject.visibility = View.VISIBLE // vp on
            binding.indicatorInclude.visibility = View.VISIBLE // indicator on

            initViewPager() // 뷰페이저 연결하기
        }
    }

    // 추천 프로젝트 초기화 - (recyclerview)
    private fun initRecoProject() {
        if (recoList.size < 1) { // 리스트 한 개 미만
            binding.tvRecoShowAll.visibility = View.GONE // 전체보기 gone

            binding.cardViewNoRecommend.visibility = View.VISIBLE // cardview on
            binding.rvRecommendProject.visibility = View.GONE // vp gone
        } else { //리스트 한 개 이상
            binding.tvRecoShowAll.visibility = View.VISIBLE // 전체보기 on

            binding.cardViewNoRecommend.visibility = View.GONE // cardview gone
            binding.rvRecommendProject.visibility = View.VISIBLE // vp on

            initRecyclerView()
        }
    }

    private fun initViewPager() {
        val vpAdapter = ProjectItemVPAdapter(includeList,requireContext())
        binding.vpIncludeProject.adapter = vpAdapter
        binding.indicatorInclude.attachTo(binding.vpIncludeProject)
    }

    private fun initRecyclerView() {
        val rvAdapter = ProjectItemRVAdapter(requireContext())
        rvAdapter.updateProjectList(recoList)
        rvAdapter.setProjectList(recoList)
        binding.rvRecommendProject.adapter = rvAdapter
    }

    // 전체보기 클릭 이벤트 -> 추천 프로젝트 화면으로 이동
    private fun initShowAllEvent(){
        binding.tvRecoShowAll.setOnClickListener {
            startActivity(Intent(requireContext(), RecommendProjectActivity::class.java))
        }
    }

    //글쓰기 뷰 도입
    private fun writeBtnClick() {
        binding.fabWriting.setOnClickListener {
            val intent = Intent(requireActivity(), OpenProjectActivity::class.java)
            intent.putExtra("userId", mainViewModel.mId.value)
            startActivity(intent)
        }
    }

}