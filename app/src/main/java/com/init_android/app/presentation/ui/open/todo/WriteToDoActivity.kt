package com.init_android.app.presentation.ui.open.todo

import android.app.DatePickerDialog
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import androidx.core.content.ContextCompat
import com.init_android.R
import com.init_android.app.data.model.SelectableData
import com.init_android.app.data.request.todo.RequestToDoMember
import com.init_android.app.presentation.ui.main.MainViewModel
import com.init_android.app.presentation.ui.mypage.viewmodel.MyPageViewModel
import com.init_android.app.presentation.ui.open.todo.viewmodel.ToDoViewModel
import com.init_android.app.util.CustomBottomSheetDialog
import com.init_android.databinding.ActivityWriteToDoBinding
import com.playtogether_android.app.presentation.base.BaseActivity
import java.util.*

class WriteToDoActivity : BaseActivity<ActivityWriteToDoBinding>(R.layout.activity_write_to_do) {

    private val mainViewModel: MainViewModel by viewModels()
    private val todoViewModel: ToDoViewModel by viewModels()
    val partBottomSheetDialog = CustomBottomSheetDialog("팀원 파트", "완료")
    val memberBottomSheetDialog = CustomBottomSheetDialog("팀원", "완료")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        initDatePickerDialogToDo()
        initPart()
    }


    private fun initDatePickerDialogToDo() {
        binding.etOpenProjectName.setOnClickListener {
            val calendar: Calendar = Calendar.getInstance()

            val datePickerDialog = DatePickerDialog(
                this,
                R.style.MySpinnerDatePickerStyle,
                { _, year, month, day ->
                    calendar.set(Calendar.YEAR, year)
                    calendar.set(Calendar.MONTH, month)
                    calendar.set(Calendar.DAY_OF_YEAR, day)

                    val month = month + 1
                    val dateString = String.format("%d.%02d.%02d", year, month, day)

                    binding.etOpenProjectName.setText(dateString)
                    binding.etOpenProjectName.setTextColor(Color.parseColor("#191919"))
                    binding.etOpenProjectName.isSelected = true

                },
                calendar.get(Calendar.YEAR),
                calendar.get(Calendar.MONTH),
                calendar.get(Calendar.DAY_OF_MONTH),

                )
            val textColor = ContextCompat.getColor(
                this, R.color.main_default
            )
            datePickerDialog.show()
            datePickerDialog.getButton(
                DatePickerDialog.BUTTON_POSITIVE
            ).setTextColor(textColor)
            datePickerDialog.getButton(
                DatePickerDialog.BUTTON_NEGATIVE
            ).setTextColor(textColor)
        }

    }

    private fun initPart() {
        binding.etOpenProjectPart.setOnClickListener {
            //파트 바텀시트
            var partData = mutableListOf(
                SelectableData(0, "기획", false),
                SelectableData(1, "디자인", false),
                SelectableData(2, "웹", false),
                SelectableData(3, "안드로이드", false),
                SelectableData(4, "IOS", false),
                SelectableData(5, "게임", false),
                SelectableData(6, "서버", false),
            )
            partBottomSheetDialog.setDataList(partData)

            binding.etOpenProjectPart.setOnClickListener {
                partBottomSheetDialog.show(
                    supportFragmentManager,
                    partBottomSheetDialog.tag
                )

                val firstMajorPeriod = partBottomSheetDialog.getSelectedData()

                partBottomSheetDialog.setCompleteListener {
                    mainViewModel.part.value = firstMajorPeriod?.name
                    partBottomSheetDialog.binding.btnBottomsheetCancel

                    mainViewModel.part.observe(this) {
                        binding.etOpenProjectPart.setText(it)
                        binding.etOpenProjectPart.setTextColor(Color.parseColor("#FF000000"))

                    }
                }


                val requestToDoMember = RequestToDoMember(1, firstMajorPeriod?.id ?: 1)
                Log.d("partNum", firstMajorPeriod?.id.toString())
                todoViewModel.postToDoMember(requestToDoMember)
                todoViewModel.todoMember.observe(this) {
                    val data = it.members.toMutableList()
                    val dataList = mutableListOf<SelectableData>()
                    for (i in data.indices) {
                        dataList.add(SelectableData(data[i].mNum, data[i].mName, false))
                    }
                    memberBottomSheetDialog.setDataList(dataList)
                }
                //버튼 클릭해서 바텀시트 생성되는 부분
                binding.etOpenProjectPartner.setOnClickListener {
                    memberBottomSheetDialog.show(
                        supportFragmentManager,
                        memberBottomSheetDialog.tag
                    )

                    //클릭 완료되었을때 일어나는 리스너
                    memberBottomSheetDialog.setCompleteListener {
                        val part = memberBottomSheetDialog.getSelectedData()

                        memberBottomSheetDialog.binding.btnBottomsheetCancel



                    }
                }
            }
        }

    }

    private fun initPerson() {

    }
}