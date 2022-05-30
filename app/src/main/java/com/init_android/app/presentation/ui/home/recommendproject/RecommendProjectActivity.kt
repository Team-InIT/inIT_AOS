package com.init_android.app.presentation.ui.home.recommendproject

import android.os.Bundle
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
            mPosition = 3,
            mLevel = 0
        )

        recoProjectViewModel.postRecoProjectData(requestRecoProject)

        recoProjectViewModel.recoProjectData.observe(this) {

            val recommend_data = it.pInfo
            val recommendList_writer = it.writerInfo

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
                            recommend_data[i].pState!!
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
    }

    private fun initBackBtn() {
        binding.btnBack.setOnClickListener {
            finish()
        }
    }
}