package com.init_android.app.presentation.ui.open.partner

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.activity.viewModels
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.tabs.TabLayoutMediator
import com.init_android.R
import com.init_android.app.presentation.ui.open.partner.adapter.PartnerTabAdapter
import com.init_android.app.presentation.ui.open.viewmodel.ProjectViewModel
import com.init_android.databinding.ActivityPartnerCheckBinding
import com.playtogether_android.app.presentation.base.BaseActivity

class PartnerCheckActivity :
    BaseActivity<ActivityPartnerCheckBinding>(R.layout.activity_partner_check) {

    private lateinit var partnerTabAdapter: PartnerTabAdapter
    private val projectViewModel: ProjectViewModel by viewModels()

    //val pNum = intent.getIntExtra("pNum", 1)
    val pNum = 1


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        initAdapter()
        initTab()
        backBtnClickListener()
        initSetting()

    }

    private fun initSetting() {
        projectViewModel.detailProject.observe(this) {

            if (it.isApproval == true) {
                binding.btnPartnerJoin.visibility = View.VISIBLE
            } else {
                binding.btnPartnerJoin.visibility = View.GONE
            }
        }
    }

    //뒤로가기 버튼 클릭 리스너
    private fun backBtnClickListener() {
        binding.ivOpenProjectBack.setOnClickListener {
            finish()
        }
    }

    private fun initAdapter() {
        val fragmentList = listOf(
            PlanFragment(),
            DesignFragment(),
            WebFragment(),
            AosFragment(),
            IosFragment(),
            GameFragment(),
            ServerFragment()
        )

        partnerTabAdapter = PartnerTabAdapter(this)
        partnerTabAdapter.fragments.addAll(fragmentList)

        Log.d("hello",fragmentList.toString())
        Log.d("hello",fragmentList.size.toString())
        binding.vpPartnerTab.offscreenPageLimit = partnerTabAdapter.itemCount
        binding.vpPartnerTab.adapter = partnerTabAdapter
    }

    private fun initTab() {
        val tabLabel = listOf("기획", "디자인", "웹", "AOS", "IOS", "게임", "서버")
        TabLayoutMediator(binding.tlPartnerTab, binding.vpPartnerTab) { tab, position ->
            tab.text = tabLabel[position]
        }.attach()

        Log.d("hello",tabLabel.toString())
        Log.d("hello",tabLabel.size.toString())
    }


}
