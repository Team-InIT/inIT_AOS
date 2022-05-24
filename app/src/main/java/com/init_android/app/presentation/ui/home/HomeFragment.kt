package com.init_android.app.presentation.ui.home

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.init_android.R
import com.init_android.app.util.DateUtil
import com.init_android.app.util.PreferenceUtil
import com.init_android.databinding.FragmentHomeBinding
import com.playtogether_android.app.presentation.base.BaseFragment


class HomeFragment : BaseFragment<FragmentHomeBinding>(R.layout.fragment_home) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
            val date = DateUtil().stringToDate(binding.tvHoho.text.toString())
            val text = DateUtil().dateToString(date)

        Toast.makeText(requireContext(), text, Toast.LENGTH_SHORT).show()
    }
}