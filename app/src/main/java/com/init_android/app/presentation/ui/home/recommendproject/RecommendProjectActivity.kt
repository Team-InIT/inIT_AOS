package com.init_android.app.presentation.ui.home.recommendproject

import android.os.Bundle
import com.init_android.R
import com.init_android.app.data.model.ProjectItemData
import com.init_android.app.presentation.ui.home.adapter.ProjectItemRVAdapter
import com.init_android.databinding.ActivityRecommendProjectBinding
import com.playtogether_android.app.presentation.base.BaseActivity

class RecommendProjectActivity:BaseActivity<ActivityRecommendProjectBinding>(R.layout.activity_recommend_project) {

    val recoList = mutableListOf<ProjectItemData>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        initBackBtn()
        tryGetRecommendProject()
    }

    private fun tryGetRecommendProject(){
        //recoList.add(ProjectItemData("웹","서울여대 해커톤 나가실 분","노원구","1/4","2022.06.11","2022.06.24","장윤정","D-DAY"))
        //initRecyclerView()
    }

    private fun initRecyclerView(){
        val rvAdapter = ProjectItemRVAdapter(this)
        rvAdapter.updateProjectList(recoList)
        binding.rvRecommendProject.adapter = rvAdapter
    }

    private fun initBackBtn(){
        binding.btnBack.setOnClickListener{
            finish()
        }
    }
}