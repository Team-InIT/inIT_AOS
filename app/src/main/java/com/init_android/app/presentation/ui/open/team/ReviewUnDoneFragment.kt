package com.init_android.app.presentation.ui.open.team

import android.app.Dialog
import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.Window
import android.view.WindowManager
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.fragment.app.viewModels
import com.init_android.R
import com.init_android.app.presentation.ui.open.team.adapter.TeamReviewAdapter
import com.init_android.databinding.FragmentReviewUndoneBinding
import com.playtogether_android.app.presentation.base.BaseFragment

class ReviewUnDoneFragment :
    BaseFragment<FragmentReviewUndoneBinding>(R.layout.fragment_review_undone) {

    private val teamReviewViewModel: TeamReviewViewModel by viewModels()
    val itemList = mutableListOf<TeamData>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initAdapter()
        initBtnEvent()
        initLikeEvent()
    }

    private fun initAdapter() {
        val undoneAdapter = TeamReviewAdapter(requireContext())
        itemList.addAll(
            listOf(
                TeamData("정지연", "안드로이드", true),
                TeamData("이혜빈", "안드로이드", false),
                TeamData("장윤정", "서버", false)
            )
        )
        undoneAdapter.submitList(itemList)
        binding.rvTeamList.adapter = undoneAdapter
        teamReviewViewModel.selectedName.value = undoneAdapter.currentList[0].name

        // 팀원 선택 클릭 이벤트
        undoneAdapter.setItemClickListener(object : TeamReviewAdapter.OnItemClickListener {
            override fun onClick(v: View, position: Int) {

                val teamList = undoneAdapter.currentList
                // 선택 이벤트
                if (teamList[position].checkState) { // 아이템 선택됨

                    // position 값을 제외하여 선택된 게 아무것도 없다면 -> break
                    for (i in 0 until teamList.size) {

                        if (i == position) {
                            // 선택한 팀원의 이름 데이터 넘겨주기
                            teamReviewViewModel.selectedName.value = teamList[position].name
                            continue
                        }

                        if (teamList[i].checkState) {
                            teamList[position].checkState = false // 선택 off
                        }
                    }
                } else { // 아이템 선택x
                    for (i in 0 until teamList.size) { // 모든 선택 off
                        teamList[i].checkState = false
                    }
                    teamList[position].checkState = true // 클릭 요소만  선택 on

                    // 선택한 팀원의 이름 데이터 넘겨주기
                    teamReviewViewModel.selectedName.value = teamList[position].name
                }

                resetReview() // 평가 여부 초기화

            }

        })
    }

    private fun initBtnEvent() {
        binding.registerBtn.setOnClickListener {
            // 여기서 서버에 데이터 넘겨주기
            Log.d("data",binding.etvTeamReview.text.toString())
            Log.d("data",binding.layoutRecommend.isSelected.toString())
            showAddDialog() // 완료 다이얼로그
        }
    }

    // 리뷰 초기화
    private fun resetReview() {
        binding.etvTeamReview.text.clear() // 내용 초기화
        binding.layoutRecommend.isSelected = false // 평가 여부 초기화

    }

    // 추천 이벤트 생성
    private fun initLikeEvent() {
        binding.layoutRecommend.setOnClickListener {
            binding.layoutRecommend.isSelected = !binding.layoutRecommend.isSelected
        }
    }

    // 완료 알럿 띄우기
    private fun showAddDialog(){
        val doneDialog = Dialog(requireContext())
        doneDialog.setContentView(R.layout.dialog_done)
        doneDialog.window?.setLayout(
            WindowManager.LayoutParams.MATCH_PARENT,
            WindowManager.LayoutParams.WRAP_CONTENT
        )
        doneDialog.findViewById<ConstraintLayout>(R.id.constraintLayout2_done).setOnClickListener {
            doneDialog.dismiss()
        }
        doneDialog.show()
    }

    // OnResume에서 리스트 다시 받아오기
}