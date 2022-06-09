package com.init_android.app.presentation.ui.alarm

import android.os.Bundle
import android.view.View
import com.init_android.R
import com.init_android.app.data.model.AlarmData
import com.init_android.app.presentation.ui.alarm.adapter.AlarmAdapter
import com.init_android.databinding.FragmentAlarmBinding
import com.playtogether_android.app.presentation.base.BaseFragment


class AlarmFragment : BaseFragment<FragmentAlarmBinding>(R.layout.fragment_alarm) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initAdapter()
    }

    private fun initAdapter() {
        val alarmAdapter = AlarmAdapter(requireContext())
        binding.rvAlarm.adapter = alarmAdapter
        alarmAdapter.submitList(
            listOf(
                AlarmData(R.drawable.ic_alarm_message, "정지연님께서 쪽지를 보내셨습니다.\n 정지연님께서 쪽지를 보냈다구욧", "2022년 4월 28일 오후 6:01",1),
                AlarmData(R.drawable.ic_alarm_project, "장윤정님께서 내 프로젝트 inIT에 지원하셨습니다.", "2022년 4월 28일 오후 6:01",1),
                AlarmData(R.drawable.ic_alarm_to_do, "inIT 프로젝트 TODO가 완료되지 않았습니다..", "2022년 4월 28일 오후 6:01",0),
                AlarmData(R.drawable.ic_alarm_message, "정지연님께서 쪽지를 보내셨습니다.", "2022년 4월 28일 오후 6:01",0),
                AlarmData(R.drawable.ic_alarm_message, "정지연님께서 쪽지를 보내셨습니다.", "2022년 4월 28일 오후 6:01",0)
            )
        )
    }
}