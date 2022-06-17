package com.init_android.app.presentation.ui.mypage

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import com.google.android.material.tabs.TabLayoutMediator
import com.init_android.R
import com.init_android.app.data.request.mypage.RequestMyInfo
import com.init_android.app.presentation.ui.home.signin.viewmodel.SignViewModel
import com.init_android.app.presentation.ui.main.MainViewModel
import com.init_android.app.presentation.ui.mypage.adapter.MyPageTabAdapter
import com.init_android.app.presentation.ui.mypage.viewmodel.MyPageViewModel
import com.init_android.databinding.FragmentMyPageBinding
import com.playtogether_android.app.presentation.base.BaseFragment


class MyPageFragment : BaseFragment<FragmentMyPageBinding>(R.layout.fragment_my_page) {

    private val mainViewModel: MainViewModel by activityViewModels()
    private val myPageViewModel: MyPageViewModel by viewModels()

    private lateinit var myPageTabAdapter: MyPageTabAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initAdapter()
        initTab()
        settingBtnListener()
        editProfileListener()
        //initNetwork()
    }

    override fun onResume() {
        super.onResume()
        initNetwork()
    }

    private fun initNetwork() {


        mainViewModel.mNum.observe(viewLifecycleOwner) {
            val mNum = it
            val requestMyInfo = RequestMyInfo(
                mNum = mNum
            )
            myPageViewModel.postMyInfo(requestMyInfo)
            Log.d("Test", mainViewModel.mNum.toString())
        }


        myPageViewModel.myInfoData.observe(viewLifecycleOwner) {
            binding.user = it.mInfo
        }
    }

    //tab layout adapter
    private fun initAdapter() {
        val fragmentList = listOf(
            MyPageInfoFragment(),
            MyPageProjectFragment(),
            MyPageFeedFragment(),
            MyPageRateFragment()
        )
        myPageTabAdapter = MyPageTabAdapter(this)
        myPageTabAdapter.fragments.addAll(fragmentList)


        binding.vpHome.adapter = myPageTabAdapter

    }

    private fun initTab() {
        val tabLabel = listOf("정보", "프로젝트", "피드", "평가")
        TabLayoutMediator(binding.tabHome, binding.vpHome) { tab, position ->
            tab.text = tabLabel[position]
        }.attach()
    }

    //톱니바퀴 클릭 리스너
    private fun settingBtnListener() {
        binding.ivMypageSetting.setOnClickListener {
            val intentSetting = Intent(requireActivity(), MyPageSettingActivity::class.java)
            startActivity(intentSetting)
        }
    }

    //프로필 편집 클릭 리스너
    private fun editProfileListener() {
        binding.tvMyPageModifyProfile.setOnClickListener {
            mainViewModel.mNum.observe(viewLifecycleOwner) {
                val mNum = it
                Log.d("TestFramgnet", "" + mNum)
                val intentModifyProfile =
                    Intent(requireActivity(), MyPageModifyProfileActivity::class.java)
                intentModifyProfile.putExtra("name", binding.tvMyPageProfileName.text.toString())
                intentModifyProfile.putExtra("position", binding.tvMyPagePostion.text.toString())
                intentModifyProfile.putExtra("level", binding.tvMyPagePositionLevel.text.toString())
                intentModifyProfile.putExtra(
                    "introduction",
                    binding.readMoreTextView.text.toString()
                )
                intentModifyProfile.putExtra("mNum", mNum)
                startActivity(intentModifyProfile)
            }
        }
    }
}