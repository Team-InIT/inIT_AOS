package com.init_android.app.presentation.ui.search.recruiting

import android.graphics.Color
import android.os.Bundle
import android.util.Log
import android.view.View
import com.init_android.R
import com.init_android.app.data.model.ProjectItemData
import com.init_android.app.data.model.SelectableData
import com.init_android.app.presentation.ui.home.adapter.ProjectItemRVAdapter
import com.init_android.app.util.CustomBottomSheetDialog
import com.init_android.databinding.FragmentSearchRecruitingBinding
import com.playtogether_android.app.presentation.base.BaseFragment

class SearchRecruitingFragment:BaseFragment<FragmentSearchRecruitingBinding>(R.layout.fragment_search_recruiting) {


    private val partBottomSheetDialog = CustomBottomSheetDialog("프로젝트 타입")

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initAdapter()
        clickListener()
        initRefreshEvent()

    }

    private fun initAdapter(){
        val adapter = ProjectItemRVAdapter(requireContext())
        binding.rvProject.adapter = adapter
        adapter.submitList(
            listOf(
                ProjectItemData(0,"졸업프로젝트 같이 할 사람",0,3,"2021.03.05","2021.04.05",
                "이혜빈",0,-1,-1)
            )
        )
    }

    private fun initRefreshEvent(){
        binding.ivRefresh.setOnClickListener {
            binding.ivRefresh.visibility = View.GONE // 초기화 버튼 활성화
            binding.layoutProjectType.background =
                resources.getDrawable(R.drawable.rectangle_stroke_gray_radius_16, null)
        }
    }

    // 바텀 시트 이벤트
    private fun clickListener() {
        //더미 데이터 넣는 부분
        var partData = mutableListOf(
            SelectableData(1, "기획", false),
            SelectableData(2, "디자인", false),
            SelectableData(3, "웹", false),
            SelectableData(4, "안드로이드", false),
            SelectableData(5, "IOS", false),
            SelectableData(6, "게임", false),
            SelectableData(7, "서버", false),
        )
        partBottomSheetDialog.setDataList(partData)

        //버튼 클릭해서 바텀시트 생성되는 부분
        binding.layoutProjectType.setOnClickListener {
            partBottomSheetDialog.show(
                childFragmentManager,
                partBottomSheetDialog.tag
            )

            //클릭 완료되었을때 일어나는 리스너
            partBottomSheetDialog.setCompleteListener {
                val part = partBottomSheetDialog.getSelectedData()
                //뷰모델에 넣어주는 코드
                // mainViewModel.part.value = part?.name
                // mainViewModel.partNum.value = part?.id
                partBottomSheetDialog.binding.btnBottomsheetCancel
                //알럿
                // initAlert()
                binding.ivRefresh.visibility = View.VISIBLE // 초기화 버튼 활성화
                binding.layoutProjectType.background = resources.getDrawable(R.drawable.rectangle_stroke_main_radius_16,null)

                partBottomSheetDialog.binding.btnBottomsheetComplete.setOnClickListener {
                    Log.d("클릭", "완료")
                    // initAlert()
                }
            }
        }
    }
}
