package com.init_android.app.presentation.ui.home.signup

import android.os.Bundle
import android.view.View
import com.init_android.R
import com.init_android.databinding.FragmentBaseInfoOneBinding
import com.playtogether_android.app.presentation.base.BaseFragment

// 기본정보(1)
class BaseInfoOneFragment:BaseFragment<FragmentBaseInfoOneBinding>(R.layout.fragment_base_info_one){

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initTransFragmentEvent()

        (activity as SignUpActivity).binding.pbSignup.progress = 50
    }

    // Fragment 초기화
    private fun initTransFragmentEvent() {
        binding.btnNext.setOnClickListener {
            val baseInfoTwoFragment = BaseInfoTwoFragment()

            parentFragmentManager.beginTransaction().replace(R.id.fragment_sign_up, baseInfoTwoFragment)
                .commit()
        }
    }
}