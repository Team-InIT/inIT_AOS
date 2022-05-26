package com.init_android.app.presentation.ui.mypage

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.init_android.R
import com.init_android.app.presentation.ui.mypage.adapter.MyPageTabAdapter
import com.init_android.app.presentation.ui.mypage.adapter.StackAdapter
import com.init_android.databinding.FragmentMyPageInfoBinding
import com.playtogether_android.app.presentation.base.BaseFragment


class MyPageInfoFragment : BaseFragment<FragmentMyPageInfoBinding>(R.layout.fragment_my_page_info) {
    private lateinit var stackAdapter: StackAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

    }


}