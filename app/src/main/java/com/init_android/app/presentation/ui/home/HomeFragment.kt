package com.init_android.app.presentation.ui.home

import android.os.Bundle
import android.view.View
import com.init_android.R
import com.init_android.databinding.FragmentHomeBinding
import com.playtogether_android.app.presentation.base.BaseFragment


class HomeFragment : BaseFragment<FragmentHomeBinding>(R.layout.fragment_home) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


    }

    // 데이터 존재 여부 검사

    // 소속 프로젝트 초기화 - (viewpager)
    private fun initIncludeProject(){

    }

    // 추천 프로젝트 초기화 - (recyclerview)
}