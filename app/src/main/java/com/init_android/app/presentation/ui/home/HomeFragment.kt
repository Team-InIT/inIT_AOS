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
        mainViewModel.mNum.observe(viewLifecycleOwner) {
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
            val include_data = it.list_join
            val recommend_data = it.list_recommend
            // val recommendList_writer = it.writerInfo_recommend

            // 소속 프로젝트
            if (include_data != null) {
                for (i in 0 until include_data.size) {
                    val totalNum =
                        include_data[i].list_belong.pPlan + include_data[i].list_belong.pDesign + include_data[i].list_belong.pAos + include_data[i].list_belong.pIos + include_data[i].list_belong.pWeb + include_data[i].list_belong.pGame + include_data[i].list_belong.pServer
                    includeList.add(
                        ProjectItemData(
                            include_data[i].list_belong.pType!!,
                            include_data[i].list_belong.pTitle!!,
                            include_data[i].list_belong.pOnOff!!,
                            totalNum,
                            DateUtil().dateToString(include_data[i].list_belong.pStart!!).replace("-","."),
                            DateUtil().dateToString(include_data[i].list_belong.pDue!!).replace("-","."),
                            include_data[i].list_belong.Member.mName,
                            include_data[i].list_belong.pState!!,
                            include_data[i].list_belong.pNum!!,
                            include_data[i].list_belong.mNum!!,
                            include_data[i].projectState
                        )
                    )
                }
            }

            // 추천 프로젝트
            if (recommend_data != null) {
                for (i in recommend_data.indices) {
                    val totalNum =
                        recommend_data[i].listRecommend.pPlan + recommend_data[i].listRecommend.pDesign + recommend_data[i].listRecommend.pAos + recommend_data[i].listRecommend.pIos + recommend_data[i].listRecommend.pWeb + recommend_data[i].listRecommend.pGame + recommend_data[i].listRecommend.pServer
                    recoList.add(
                        ProjectItemData(
                            recommend_data[i].listRecommend.pType!!,
                            recommend_data[i].listRecommend.pTitle!!,
                            recommend_data[i].listRecommend.pOnOff!!,
                            totalNum,
                            DateUtil().dateToString(recommend_data[i].listRecommend.pStart!!).replace("-","."),
                            DateUtil().dateToString(recommend_data[i].listRecommend.pDue).replace("-","."),
                            recommend_data[i].listRecommend.Member.mName,
                            recommend_data[i].listRecommend.pState!!,
                            recommend_data[i].listRecommend.pNum!!,
                            recommend_data[i].listRecommend.mNum!!,
                            recommend_data[i].projectState2
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
            intent.putExtra("userId", mainViewModel.mNum.value)
            startActivity(intent)
        }
    }

}