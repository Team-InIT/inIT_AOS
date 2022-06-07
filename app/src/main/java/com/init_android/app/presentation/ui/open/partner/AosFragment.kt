package com.init_android.app.presentation.ui.open.partner

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.init_android.R
import com.init_android.databinding.FragmentAosBinding
import com.playtogether_android.app.presentation.base.BaseFragment

class AosFragment : BaseFragment<FragmentAosBinding>(R.layout.fragment_aos) {

    //private lateinit var myPageLikeQuestionAdapter: MyPageLikeQuestionAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

    }

    private fun initPartnerAdapter() {
        //val userId = intent.getIntExtra("userId", 0)
//        myPageLikeReviewAdapter = MyPageLikeReviewAdapter(userId)
//        binding.rvMypageLike.adapter = myPageLikeReviewAdapter
//
//        myPageViewModel.likeReview.observe(this) {
//            initReviewEmpty(it.data.likePostList.size)
//            myPageLikeReviewAdapter.setReviewListData((it.data.likePostList) as MutableList<MyPageLikeReviewData.Data.LikePost>)
//        }
    }
}