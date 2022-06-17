package com.init_android.app.presentation.ui.open.team

import android.app.Dialog
import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.WindowManager
import android.widget.TextView
import android.widget.Toast
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.fragment.app.viewModels
import com.init_android.R
import com.init_android.app.data.request.RequestAlreadyEvaluate
import com.init_android.app.data.request.RequestCheckEvaluation
import com.init_android.app.data.request.RequestDeleteEvaluation
import com.init_android.app.data.request.RequestNotEveluate
import com.init_android.app.presentation.ui.open.team.adapter.TeamReviewAdapter
import com.init_android.databinding.FragmentReviewDoneBinding
import com.playtogether_android.app.presentation.base.BaseFragment

class ReviewDoneFragment : BaseFragment<FragmentReviewDoneBinding>(R.layout.fragment_review_done) {

    private val teamReviewViewModel: TeamReviewViewModel by viewModels()
    private val itemList = mutableListOf<TeamData>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        // initAdapter()
        initBtnEvent()
    }

    private fun initAdapter() {
        val undoneAdapter = TeamReviewAdapter(requireContext())

        Log.d("test", "initAdapter")

        val requestAlreadyEvaluate = RequestAlreadyEvaluate(
            mNum = 1,
            pNum = 1
        )

        teamReviewViewModel.postAlreadyEveluate(requestAlreadyEvaluate)

        teamReviewViewModel.evaluate.observe(viewLifecycleOwner) {
            itemList.clear()
            val data = it.members?.toMutableList()
            for (i in data!!.indices) {
                if (i == 0) {
                    itemList.add(
                        TeamData(
                            data[i].mNum,
                            data[i].mName,
                            data[i].mPosition,
                            data[i].mPhoto ?: "",
                            true
                        )
                    )
                } else {
                    itemList.add(
                        TeamData(
                            data[i].mNum,
                            data[i].mName,
                            data[i].mPosition,
                            data[i].mPhoto ?: "",
                            false
                        )
                    )
                }
            }


            Log.d("test", "viewModel1")
            undoneAdapter.submitList(itemList)
            binding.rvTeamList.adapter = undoneAdapter
            teamReviewViewModel.selectedPersonNum.value = undoneAdapter.currentList[0].personNum
            getTeamReview()


        }
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
                            teamReviewViewModel.selectedPersonNum.value =
                                teamList[position].personNum
                            getTeamReview()
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
                    teamReviewViewModel.selectedPersonNum.value = teamList[position].personNum
                    getTeamReview()
                }
            }

        })
    }

    // 삭제 이벤트
    private fun initBtnEvent() {
        binding.btnDelete.setOnClickListener {
            Log.d("test", "initBtnEvent")
            showDeleteDialog()
        }
    }

    // 알럿 창 생성
    private fun showDeleteDialog() {
        val doneDialog = Dialog(requireContext())
        doneDialog.setContentView(R.layout.dialog_yes_no)
        doneDialog.window?.setLayout(
            WindowManager.LayoutParams.MATCH_PARENT,
            WindowManager.LayoutParams.WRAP_CONTENT
        )
        doneDialog.findViewById<TextView>(R.id.tv_dialog_title).text = "정말 삭제하시겠습니까?"
        doneDialog.findViewById<TextView>(R.id.tv_dialog_yes).setOnClickListener {
            doneDialog.dismiss()
            // 삭제 yes ~~ 여기서 서버통신
            val requestDeleteEvaluation =
                RequestDeleteEvaluation(eNum = teamReviewViewModel.selectedENum.value!!.toInt())
            teamReviewViewModel.postDeleteEvaluate(requestDeleteEvaluation)
            Log.d("test", "viewModel3")
            initAdapter()
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
        Log.d("test", "onResume")
        initAdapter()

    }

    // 평가된 팀원 개별 요소 조회
    private fun getTeamReview() {
        val requestCheckEvaluation = RequestCheckEvaluation(
            mNum = 1,
            pNum = 1,
            ePerson = teamReviewViewModel.selectedPersonNum.value!!.toInt()
        )
        teamReviewViewModel.postCheckEvaluation(requestCheckEvaluation)

        teamReviewViewModel.checkEvaluateData.observe(viewLifecycleOwner) {
            teamReviewViewModel.selectedENum.value = it.evaluation.eNum
            binding.tvTeamReview.text = it.evaluation.eComment
            if (it.evaluation.eRecommend == 0) {
                binding.tvRecommend.text = ""
            } else {
                binding.tvRecommend.text = "해당 팀원을 추천했어요!"
            }

            Log.d("test", "viewModel2")
        }
    }

}