package com.init_android.app.presentation.ui.home.recommendproject

import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import com.init_android.R
import com.init_android.app.data.model.ProjectItemData
import com.init_android.app.data.request.RequestRecoProject
import com.init_android.app.presentation.ui.home.adapter.ProjectItemRVAdapter
import com.init_android.app.presentation.ui.home.viewmodel.RecoProjectViewModel
import com.init_android.app.util.DateUtil
import com.init_android.databinding.ActivityRecommendProjectBinding
import com.playtogether_android.app.presentation.base.BaseActivity

class RecommendProjectActivity :
    BaseActivity<ActivityRecommendProjectBinding>(R.layout.activity_recommend_project) {

    val recoList = mutableListOf<ProjectItemData>()

    private val recoProjectViewModel: RecoProjectViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        initBackBtn()
        tryGetRecommendProject()
    }

    private fun tryGetRecommendProject() {
        val requestRecoProject = RequestRecoProject(
            mPosition = 6,
            mLevel = 0
        )

        recoProjectViewModel.postRecoProjectData(requestRecoProject)

        recoProjectViewModel.recoProjectData.observe(this) {

            val recommend_data = it.list_recommend

            // 추천 프로젝트
            if (recommend_data != null) {
                for (i in recommend_data.indices) {
                    val totalNum =
                        recommend_data[i].pInfo.pPlan + recommend_data[i].pInfo.pDesign + recommend_data[i].pInfo.pAos + recommend_data[i].pInfo.pIos + recommend_data[i].pInfo.pWeb + recommend_data[i].pInfo.pGame + recommend_data[i].pInfo.pServer
                    recoList.add(
                        ProjectItemData(
                            recommend_data[i].pInfo.pType!!,
                            recommend_data[i].pInfo.pTitle!!,
                            recommend_data[i].pInfo.pOnOff!!,
                            totalNum,
                            DateUtil().dateToString(recommend_data[i].pInfo.pStart!!).replace("-","."),
                            DateUtil().dateToString(recommend_data[i].pInfo.pDue!!).replace("-","."),
                            recommend_data[i].pInfo.Member.mName,
                            recommend_data[i].pInfo.pState!!,
                            recommend_data[i].pInfo.pNum!!,
                            recommend_data[i].pInfo.mNum!!,
                            recommend_data[i].projectState
                        )
                    )
                }
            }

            initRecyclerView()

        }

    }

    private fun initRecyclerView() {
        val rvAdapter = ProjectItemRVAdapter(this)
        rvAdapter.updateProjectList(recoList)
        binding.rvRecommendProject.adapter = rvAdapter
        rvAdapter.setProjectList(recoList)
    }

    private fun initBackBtn() {
        binding.btnBack.setOnClickListener {
            finish()
        }
    }
}