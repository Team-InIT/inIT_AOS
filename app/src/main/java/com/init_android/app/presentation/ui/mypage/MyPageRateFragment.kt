package com.init_android.app.presentation.ui.mypage

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.init_android.R
import com.init_android.app.data.response.ResponseRateData
import com.init_android.app.presentation.ui.mypage.adapter.RatingAdapter
import com.init_android.databinding.FragmentMyPageRateBinding
import com.playtogether_android.app.presentation.base.BaseFragment

class MyPageRateFragment : BaseFragment<FragmentMyPageRateBinding>(R.layout.fragment_my_page_rate) {
    private lateinit var ratingAdapter: RatingAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initAdapter()
    }

    private fun initAdapter() {
        ratingAdapter = RatingAdapter()
        binding.rvRating.adapter = ratingAdapter

        ratingAdapter.rateList.addAll(
            listOf(
                ResponseRateData(R.drawable.ic_launcher_background,"이름","안드로이드 프로젝트", "성실하고 좋은 팀원이었습니다"),
                ResponseRateData(R.drawable.ic_launcher_background,"이름","안드로이드 프로젝트", "성실하고 좋은 팀원이었습니다"),
                ResponseRateData(R.drawable.ic_launcher_background,"이름","안드로이드 프로젝트", "성실하고 좋은 팀원이었습니다")
            )
        )

        ratingAdapter.notifyDataSetChanged()
    }


}