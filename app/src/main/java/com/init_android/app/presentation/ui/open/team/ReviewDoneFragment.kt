package com.init_android.app.presentation.ui.open.team

import android.app.Dialog
import android.os.Bundle
import android.view.View
import android.view.WindowManager
import android.widget.TextView
import android.widget.Toast
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.fragment.app.viewModels
import com.init_android.R
import com.init_android.app.presentation.ui.open.team.adapter.TeamReviewAdapter
import com.init_android.databinding.FragmentReviewDoneBinding
import com.playtogether_android.app.presentation.base.BaseFragment

class ReviewDoneFragment:BaseFragment<FragmentReviewDoneBinding>(R.layout.fragment_review_done) {

    private val teamReviewViewModel: TeamReviewViewModel by viewModels()
    private val itemList = mutableListOf<TeamData>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initAdapter()
        initBtnEvent()
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
            }

        })
    }

    // 삭제 이벤트
    private fun initBtnEvent(){
        binding.btnDelete.setOnClickListener {
            showDeleteDialog()
        }
    }

    // 알럿 창 생성
    private fun showDeleteDialog(){
        val doneDialog = Dialog(requireContext())
        doneDialog.setContentView(R.layout.dialog_yes_no)
        doneDialog.window?.setLayout(
            WindowManager.LayoutParams.MATCH_PARENT,
            WindowManager.LayoutParams.WRAP_CONTENT
        )
        doneDialog.findViewById<TextView>(R.id.tv_dialog_title).text = "정말 삭제하시겠습니까?"
        doneDialog.findViewById<TextView>(R.id.tv_dialog_yes).setOnClickListener {
            // 삭제 yes ~~~ 서버통신 고고링 ~
            doneDialog.dismiss()
        }
        doneDialog.findViewById<TextView>(R.id.tv_dialog_no).setOnClickListener {
            // 삭제 no
            doneDialog.dismiss()
        }

        doneDialog.show()
    }

    // resume 말고 완료했을 때 리스트 부르는 함수를 호출해야 겠다.
    override fun onResume() {
        super.onResume()

        Toast.makeText(requireContext(), "완료 화면 resume 테스뚜 헤헤", Toast.LENGTH_SHORT).show()
    }

}