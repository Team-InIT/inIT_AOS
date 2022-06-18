package com.init_android.app.presentation.ui.mypage

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import com.init_android.R
import com.init_android.app.data.request.mypage.RequestWaitingApproval
import com.init_android.app.data.request.project.RequestProjectMember
import com.init_android.app.data.response.ResponseRateData
import com.init_android.app.data.response.mypage.ResponseEvaluation
import com.init_android.app.data.response.project.approve.ResponseProjectMember
import com.init_android.app.presentation.ui.main.MainViewModel
import com.init_android.app.presentation.ui.mypage.adapter.RatingAdapter
import com.init_android.app.presentation.ui.mypage.viewmodel.MyPageViewModel
import com.init_android.app.presentation.ui.open.partner.adapter.PartnerAosAdapter
import com.init_android.databinding.FragmentMyPageRateBinding
import com.playtogether_android.app.presentation.base.BaseFragment

class MyPageRateFragment : BaseFragment<FragmentMyPageRateBinding>(R.layout.fragment_my_page_rate) {
    private lateinit var ratingAdapter: RatingAdapter
    private val myPageViewModel : MyPageViewModel by viewModels()
    private val mainViewModel: MainViewModel by activityViewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initNetwork()
    }

    private fun initNetwork() {
        val mNum = mainViewModel.otherNum.value ?: 1
        val requestWaitingApproval = RequestWaitingApproval(mNum = mNum)
        myPageViewModel.postEvalMem(requestWaitingApproval)
        ratingAdapter = RatingAdapter()
        binding.rvRating.adapter = ratingAdapter
        myPageViewModel.myEvaluation.observe(viewLifecycleOwner) {
            ratingAdapter.setQuestionPost((listOf(it)) as MutableList<ResponseEvaluation.MyEvaluation>)
        }
    }


}