package com.init_android.app.presentation.ui.open.partner

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.google.android.material.tabs.TabLayoutMediator
import com.init_android.R
import com.init_android.app.presentation.ui.open.partner.adapter.PartnerTabAdapter
import com.init_android.databinding.ActivityPartnerCheckBinding
import com.playtogether_android.app.presentation.base.BaseActivity

class PartnerCheckActivity : BaseActivity<ActivityPartnerCheckBinding>(R.layout.activity_partner_check) {

    private lateinit var partnerTabAdapter: PartnerTabAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        initAdapter()
        initTab()

    }

    private fun initAdapter() {
        val fragmentList = listOf(PlanFragment(), DesignFragment(), WebFragment(), AosFragment(), IosFragment(), GameFragment(), ServerFragment())
        partnerTabAdapter = PartnerTabAdapter(this)
        partnerTabAdapter.fragments.addAll(fragmentList)

        binding.vpHomeTab.adapter = partnerTabAdapter
    }

    private fun initTab(){
        val tabLabel = listOf("기획", "디자인", "웹", "AOS", "IOS", "게임", "서버")
        TabLayoutMediator(binding.tlHomeTab, binding.vpHomeTab) {tab, position ->
            tab.text = tabLabel[position]
        }.attach()
    }

}