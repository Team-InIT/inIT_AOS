package com.init_android.app.presentation.ui.open.project

import android.app.DatePickerDialog
import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Spinner
import android.widget.Toast
import androidx.activity.viewModels
import androidx.core.content.ContextCompat
import com.google.android.material.chip.Chip
import com.init_android.R
import com.init_android.app.data.request.RequestAddProject
import com.init_android.app.presentation.ui.open.viewmodel.ProjectViewModel
import com.init_android.app.util.PixelRatio
import com.init_android.databinding.ActivityOpenProjectBinding
import com.playtogether_android.app.presentation.base.BaseActivity
import java.text.SimpleDateFormat
import java.util.*


class OpenProjectActivity :
    BaseActivity<ActivityOpenProjectBinding>(R.layout.activity_open_project) {

    var value = 0
    var onOff = 0
    val formatter = SimpleDateFormat("yyyy-MM-dd")

    private val projectViewModel: ProjectViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        initDatePickerDialog()
        initDatePickerDialogGoingStart()
        initDatePickerDialogGoingEnd()
        setupSpinner()
        setupSpinnerHandler()
        initChipGroup()
        initOnOff()
        initNextBtn()

    }

    private fun initNextBtn() {
        binding.tvWriteFinish.setOnClickListener {
            val chipList = ArrayList<String>()
            for (i: Int in 1..binding.chipGroup.childCount) {
                val chip: Chip = binding.chipGroup.getChildAt(i - 1) as Chip
                chipList.add(chip.text.toString())
            }

            var output = ""
            for (i in chipList) {
                val lastList = chipList.get(chipList.size - 1)
                if ("$i" == lastList)
                    output += "$i"
                else {
                    output += "$i,"
                }
            }
            Log.d("stack", output)

            //type
            if (binding.spinnerOpenProjectType.toString() == "웹") {
                value = 0
            } else if (binding.spinnerOpenProjectType.toString() == "모바일") {
                value = 1
            } else {
                value = 2
            }

            if (binding.ivWriteOnline.isSelected) {
                onOff = 0
            } else {
                onOff = 1
            }

            val intent = Intent(this, OpenProjectSecondActivity::class.java)

            intent.putExtra("pTitle", binding.etOpenProjectName.text.toString())
            intent.putExtra("pType", value)
            intent.putExtra("pRecruitStart", "2022-06-13")
            intent.putExtra(
                "pRecruitDue", binding.etOpenProjectDateEnd.text.toString().replace(".", "-")
            )
            intent.putExtra(
                "pStart",
                binding.etOpenProjectDateWhenStart.text.toString().replace(".", "-")
            )
            intent.putExtra(
                "pDue",
                binding.etOpenProjectDateWhenEnd.text.toString().replace(".", "-")
            )
            intent.putExtra("pPlan", Integer.parseInt(binding.etOpenProjectPlan.text.toString()))
            intent.putExtra(
                "pDesign",
                Integer.parseInt(binding.etOpenProjectDesign.text.toString())
            )
            intent.putExtra("pIos", Integer.parseInt(binding.etOpenProjectIos.text.toString()))
            intent.putExtra("pAos", Integer.parseInt(binding.etOpenProjectAos.text.toString()))
            intent.putExtra("pGame", Integer.parseInt(binding.etOpenProjectGame.text.toString()))
            intent.putExtra("pWeb", Integer.parseInt(binding.etOpenProjectWeb.text.toString()))
            intent.putExtra(
                "pServer",
                Integer.parseInt(binding.etOpenProjectServer.text.toString())
            )
            intent.putExtra("pDescription", binding.etOpenProjectIntroduction.text.toString())
            intent.putExtra("pOnOff", onOff)
            intent.putExtra("pStack", output)

            startActivity(intent)
            finish()
        }
    }


    //데이트피커
    private fun initDatePickerDialog() {
        binding.etOpenProjectDateEnd.setOnClickListener {
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

                    binding.etOpenProjectDateEnd.setText(dateString)
                    binding.etOpenProjectDateEnd.setTextColor(Color.parseColor("#191919"))
                    binding.etOpenProjectDateEnd.isSelected = true

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

    //데이트 피커
    private fun initDatePickerDialogGoingStart() {
        binding.etOpenProjectDateWhenStart.setOnClickListener {
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

                    binding.etOpenProjectDateWhenStart.setText(dateString)
                    binding.etOpenProjectDateWhenStart.setTextColor(Color.parseColor("#191919"))
                    binding.etOpenProjectDateWhenStart.isSelected = true

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

    //데이트 피커
    private fun initDatePickerDialogGoingEnd() {
        binding.etOpenProjectDateWhenEnd.setOnClickListener {
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

                    binding.etOpenProjectDateWhenEnd.setText(dateString)
                    binding.etOpenProjectDateWhenEnd.setTextColor(Color.parseColor("#191919"))
                    binding.etOpenProjectDateWhenEnd.isSelected = true

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

    //스피너
    private fun setupSpinner() {
        val list = listOf("웹", "모바일", "게임")
        val spinnerAdapter = SpinnerAdapter(this@OpenProjectActivity, R.layout.spinner_item, list)
        binding.spinnerOpenProjectType.adapter = spinnerAdapter
        binding.spinnerOpenProjectType.dropDownVerticalOffset = PixelRatio().dpToPx(52)

        val adapter: ArrayAdapter<kotlin.String> =
            ArrayAdapter<kotlin.String>(this, android.R.layout.simple_spinner_dropdown_item, list)
        binding.spinnerOpenProjectType.setAdapter(adapter)
        binding.spinnerOpenProjectType.setSelection(0)


    }


    //스피너 핸들러
    private fun setupSpinnerHandler() {
        binding.spinnerOpenProjectType.onItemSelectedListener =
            object : AdapterView.OnItemSelectedListener {
                override fun onItemSelected(
                    parent: AdapterView<*>?,
                    view: View?,
                    position: Int,
                    id: Long
                ) {
                    val spinner = findViewById<View>(R.id.spinner_open_project_type) as Spinner
                    val text = spinner.selectedItem.toString()
                    if (text == "웹") {
                        value = 0
                    } else if (text == "모바일") {
                        value = 1
                    } else {
                        value = 2
                    }
                }

                override fun onNothingSelected(p0: AdapterView<*>?) {
                }
            }
    }

    //칩그룹
    private fun initChipGroup() {
        binding.tvMyPageAdd.setOnClickListener {
            val string = binding.etMyPageStack.text
            if (string.isNullOrEmpty()) {
                Toast.makeText(this, "stack을 입력해주세요", Toast.LENGTH_SHORT).show()
            } else {
                binding.chipGroup.addView(Chip(this).apply {
                    text = string
                    isCloseIconVisible = true
                    binding.etMyPageStack.setText("")
                    setOnCloseIconClickListener { binding.chipGroup.removeView(this) }
                })
            }
        }
    }


    //온,오프라인 selector
    private fun initOnOff() {
        binding.ivWriteOnline.isSelected = false
        binding.ivWriteOffline.isSelected = false

        binding.ivWriteOnline.setOnClickListener {
            binding.ivWriteOnline.isSelected = true
            binding.ivWriteOffline.isSelected = false
        }

        binding.ivWriteOffline.setOnClickListener {
            binding.ivWriteOnline.isSelected = false
            binding.ivWriteOffline.isSelected = true
        }
    }
}


