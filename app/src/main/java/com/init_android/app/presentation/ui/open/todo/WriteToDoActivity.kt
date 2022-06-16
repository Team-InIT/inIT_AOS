package com.init_android.app.presentation.ui.open.todo

import android.app.DatePickerDialog
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.content.ContextCompat
import com.init_android.R
import com.init_android.databinding.ActivityWriteToDoBinding
import com.playtogether_android.app.presentation.base.BaseActivity
import java.util.*

class WriteToDoActivity : BaseActivity<ActivityWriteToDoBinding>(R.layout.activity_write_to_do) {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        initDatePickerDialogToDo()
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
}