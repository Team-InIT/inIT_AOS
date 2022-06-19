package com.init_android.app.presentation.ui.open.partner

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import com.init_android.R
import com.init_android.app.data.request.project.RequestApproveProject
import com.init_android.app.data.request.project.RequestProjectMember
import com.init_android.app.data.response.project.ready.ResponseReadyAos
import com.init_android.app.presentation.ui.main.MainViewModel
import com.init_android.app.presentation.ui.open.partner.adapter.PartnerAosAdapter
import com.init_android.app.presentation.ui.open.partner.adapter.ready.ReadyAosAdapter
import com.init_android.app.presentation.ui.open.partner.adapter.ready.ReadyPlanAdapter
import com.init_android.app.presentation.ui.open.viewmodel.ProjectViewModel
import com.init_android.app.util.CustomDialog
import com.init_android.databinding.FragmentAosBinding
import com.playtogether_android.app.presentation.base.BaseFragment

class AosFragment : BaseFragment<FragmentAosBinding>(R.layout.fragment_aos) {

    private val projectViewModel: ProjectViewModel by viewModels()
    private val mainViewModel: MainViewModel by activityViewModels()
    private lateinit var partnerAosAdapter: PartnerAosAdapter
    private lateinit var readyAosAdapter: ReadyAosAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

    }

    override fun onResume() {
        super.onResume()
        initNetwork()
        initApprove()
        initSetting()
    }

    private fun initSetting() {
        val pNum = mainViewModel.projectNum.value ?: 1
        if(pNum != 1) {
            binding.tvPartnerApprove.visibility = View.GONE
            binding.tvPartnerApproveNum.visibility = View.GONE
            binding.rvApproveAos.visibility = View.GONE
        }
        else {
            binding.tvPartnerApprove.visibility = View.VISIBLE
            binding.tvPartnerApproveNum.visibility = View.VISIBLE
            binding.rvApproveAos.visibility = View.VISIBLE
        }
    }

    //승인 대기 중
    private fun initApprove() {
        val pNum = mainViewModel.projectNum.value ?: 1
        val requestProjectMember = RequestProjectMember(pNum = pNum)

        projectViewModel.postMyCrewAos(requestProjectMember)
        readyAosAdapter = ReadyAosAdapter(1)
        binding.rvApproveAos.adapter = readyAosAdapter
        projectViewModel.myCrewAos.observe(viewLifecycleOwner) {
            readyAosAdapter.setQuestionPost((it.waitingAos) as MutableList<ResponseReadyAos.WaitingAos>)
        }
        initModelListener()
    }

    //승인 대기 완료
    private fun initNetwork() {
        val pNum = mainViewModel.projectNum.value ?: 1

        val requestProjectMember = RequestProjectMember(pNum = pNum)
        projectViewModel.postMyCrewAos(requestProjectMember)
        partnerAosAdapter = PartnerAosAdapter(1)
        binding.rvAos.adapter = partnerAosAdapter
        projectViewModel.myCrewAos.observe(viewLifecycleOwner) {
            partnerAosAdapter.setQuestionPost((it.approvedAos) as MutableList<ResponseReadyAos.ApprovedAos>)
        }
    }

    private fun initModelListener() {
        readyAosAdapter.setOnItemClickListener(object : ReadyAosAdapter.onItemClickListener {
            override fun onItemClick(user: Int, position: Int) {

                val title = "해당 팀원을 승인하시겠습니까?"
                val dialog = CustomDialog(requireContext(), title)
                dialog.showChoiceDialog(R.layout.dialog_yes_no)

                dialog.setOnClickedListener(object : CustomDialog.ButtonClickListener {
                    override fun onClicked(num: Int) {
                        if (num == 1) {
                            val userId = user
                            val requestApproveProject = RequestApproveProject(
                                mNum = 1,
                                pNum = 1,
                                apply = userId
                            )
                            Log.d("TestUSerId", userId.toString())

                            projectViewModel.postApprove(requestApproveProject)
                            projectViewModel.applyProject.observe(viewLifecycleOwner) {
                                if (it.code == 201) {
                                    Log.d("승인", "성공")
                                    Toast.makeText(
                                        requireContext(),
                                        "승인이 완료되었습니다.",
                                        Toast.LENGTH_SHORT
                                    ).show()
                                    initNetwork()
                                    initApprove()
                                } else {
                                    Log.d("승인", "거절")
                                    Toast.makeText(
                                        requireContext(),
                                        "메시지 전송이 취소되었습니다.",
                                        Toast.LENGTH_SHORT
                                    ).show()
                                    initNetwork()
                                    initApprove()
                                }
                            }


                        }
                    }
                })
            }
        })
    }

}
