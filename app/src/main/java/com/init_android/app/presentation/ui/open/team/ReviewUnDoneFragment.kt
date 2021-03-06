package com.init_android.app.presentation.ui.open.team

import android.app.Dialog
import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.Window
import android.view.WindowManager
import android.widget.TextView
import android.widget.Toast
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.fragment.app.viewModels
import com.init_android.R
import com.init_android.app.data.request.RequestAddEvaluate
import com.init_android.app.data.request.RequestNotEveluate
import com.init_android.app.presentation.ui.open.team.adapter.TeamReviewAdapter
import com.init_android.databinding.FragmentReviewUndoneBinding
import com.playtogether_android.app.presentation.base.BaseFragment

class ReviewUnDoneFragment :
    BaseFragment<FragmentReviewUndoneBinding>(R.layout.fragment_review_undone) {

    private val teamReviewViewModel: TeamReviewViewModel by viewModels()
    private val itemList = mutableListOf<TeamData>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initBtnEvent()
        initLikeEvent()
    }

    private fun initAdapter() {
        val undoneAdapter = TeamReviewAdapter(requireContext())

        val requestNotEveluate = RequestNotEveluate(
            mNum = 1,
            pNum = 1
        )

        teamReviewViewModel.postNotEveluate(requestNotEveluate)

        teamReviewViewModel.notEveluate.observe(viewLifecycleOwner) {
            itemList.clear()
            val data = it.memberToEvaluate?.toMutableList()
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


            undoneAdapter.submitList(itemList)
            binding.rvTeamList.adapter = undoneAdapter
            teamReviewViewModel.selectedPersonNum.value = undoneAdapter.currentList[0].personNum

        }


        // ?????? ?????? ?????? ?????????
        undoneAdapter.setItemClickListener(object : TeamReviewAdapter.OnItemClickListener {
            override fun onClick(v: View, position: Int) {

                val teamList = undoneAdapter.currentList
                // ?????? ?????????
                if (teamList[position].checkState) { // ????????? ?????????

                    // position ?????? ???????????? ????????? ??? ???????????? ????????? -> break
                    for (i in 0 until teamList.size) {

                        if (i == position) {
                            // ????????? ????????? ?????? ????????? ????????????
                            teamReviewViewModel.selectedPersonNum.value =
                                teamList[position].personNum
                            continue
                        }

                        if (teamList[i].checkState) {
                            teamList[position].checkState = false // ?????? off
                        }
                    }
                } else { // ????????? ??????x
                    for (i in 0 until teamList.size) { // ?????? ?????? off
                        teamList[i].checkState = false
                    }
                    teamList[position].checkState = true // ?????? ?????????  ?????? on

                    // ????????? ????????? ???????????? ????????? ????????????
                    teamReviewViewModel.selectedPersonNum.value = teamList[position].personNum
                }

                resetReview() // ?????? ?????? ?????????

            }

        })
    }

    private fun initBtnEvent() {
        binding.registerBtn.setOnClickListener {
            // ????????? ????????? ????????? ????????????
            var recoBoolean = binding.layoutRecommend.isSelected
            var recoState = if (recoBoolean) {
                1
            } else {
                0
            }

            val requestAddEvaluate = RequestAddEvaluate(
                mNum = 1,
                pNum = 1,
                ePerson = teamReviewViewModel.selectedPersonNum.value!!.toInt(),
                eRecommend = recoState,
                eComment =  binding.etvTeamReview.text.toString()
            )

            teamReviewViewModel.postAddEvaluate(requestAddEvaluate)

            showAddDialog() // ?????? ???????????????
        }
    }

    // ?????? ?????????
    private fun resetReview() {
        binding.etvTeamReview.text.clear() // ?????? ?????????
        binding.layoutRecommend.isSelected = false // ?????? ?????? ?????????

    }

    // ?????? ????????? ??????
    private fun initLikeEvent() {
        binding.layoutRecommend.setOnClickListener {
            binding.layoutRecommend.isSelected = !binding.layoutRecommend.isSelected
        }
    }

    // ?????? ?????? ?????????
    private fun showAddDialog() {
        val doneDialog = Dialog(requireContext())
        doneDialog.setContentView(R.layout.dialog_done)
        doneDialog.window?.setLayout(
            WindowManager.LayoutParams.MATCH_PARENT,
            WindowManager.LayoutParams.WRAP_CONTENT
        )
        doneDialog.findViewById<ConstraintLayout>(R.id.constraintLayout2_done).setOnClickListener {
            doneDialog.dismiss()
            resetReview()
            initAdapter()
        }
        doneDialog.show()
    }

    // OnResume?????? ????????? ?????? ????????????
    override fun onResume() {
        super.onResume()
        initAdapter()
    }

}