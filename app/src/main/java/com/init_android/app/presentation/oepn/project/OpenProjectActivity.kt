package com.init_android.app.presentation.oepn.project

import android.app.DatePickerDialog
import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.AdapterView
import android.widget.SpinnerAdapter
import android.widget.Toast
import androidx.core.content.ContextCompat
import com.init_android.R
import com.init_android.app.ServiceCreator
import com.init_android.app.data.request.RequestAddProject
import com.init_android.app.data.request.RequestSignIn
import com.init_android.app.data.response.ResponseAddProject
import com.init_android.app.data.response.ResponseSignIn
import com.init_android.app.presentation.ui.MainActivity
import com.init_android.app.util.PixelRatio
import com.init_android.databinding.ActivityOpenProjectBinding
import com.playtogether_android.app.presentation.base.BaseActivity
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.lang.String
import java.util.*

class OpenProjectActivity : BaseActivity<ActivityOpenProjectBinding>(R.layout.activity_open_project) {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        initNextBtn()
        initDatePickerDialog()
        initDatePickerDialogGoingStart()
        initDatePickerDialogGoingEnd()
        setupSpinner()
        setupSpinnerHandler()

    }

    private fun initNextBtn() {
        binding.tvFinish.setOnClickListener {
            tryPostAddProject()
        }
    }

    // 서버통신 함수
    private fun tryPostAddProject(){
        val requestAddProject = RequestAddProject(
            pTitle = binding.etOpenProjectName.toString(),
            pType = 1,
            pRdateStart= "2022.05.17",
            pRdateDue = binding.etOpenProjectDateEnd.toString(),
            pPdateStart = binding.etOpenProjectDateWhenStart.toString(),
            pPdateDue=binding.etOpenProjectDateWhenEnd.toString(),
            pPlan=Integer.parseInt(binding.etOpenProjectPlan.text.toString()),
            pDesign=Integer.parseInt(binding.etOpenProjectDesign.text.toString()),
            pAndroid=Integer.parseInt(binding.etOpenProjectAos.text.toString()),
            pIos = Integer.parseInt(binding.etOpenProjectIos.text.toString()),
            pGame = Integer.parseInt(binding.etOpenProjectGame.text.toString()),
            pWeb=Integer.parseInt(binding.etOpenProjectWeb.text.toString()),
            pServer=Integer.parseInt(binding.etOpenProjectServer.text.toString()),
            mNum = 6
        )


        val call: Call<ResponseAddProject> = ServiceCreator.initService.postAddProject(requestAddProject)

        call.enqueue(object: Callback<ResponseAddProject> {
            override fun onResponse(
                call: Call<ResponseAddProject>,
                response: Response<ResponseAddProject>
            ) {
                if(response.body()?.code == 201){ // 로그인 성공
                    startActivity(Intent(this@OpenProjectActivity, OpenProjectSecondActivity::class.java))
                    finish()
                    Toast.makeText(this@OpenProjectActivity, "작성이 완료되었습니다", Toast.LENGTH_SHORT).show()
                }else{
                    Toast.makeText(this@OpenProjectActivity, "작성이 실패되었습니다", Toast.LENGTH_SHORT).show()
                }
            }

            override fun onFailure(call: Call<ResponseAddProject>, t: Throwable) { // 서버 통신에러
                Log.e("NetworkTest", "error:$t")
            }

        })

    }

    //데이트피커 1
    //데이트 피커
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

    private fun setupSpinner() {
        val list = listOf("웹", "모바일", "게임")
        val spinnerAdapter = SpinnerAdapter(this@OpenProjectActivity, R.layout.spinner_item, list)
        binding.spinnerOpenProjectType.adapter = spinnerAdapter
        binding.spinnerOpenProjectType.dropDownVerticalOffset = PixelRatio().dpToPx(52)
    }


    private fun setupSpinnerHandler() {
        binding.spinnerOpenProjectType.onItemSelectedListener =
            object : AdapterView.OnItemSelectedListener {
                override fun onItemSelected(
                    parent: AdapterView<*>?,
                    view: View?,
                    position: Int,
                    id: Long
                ) {

                }

                override fun onNothingSelected(p0: AdapterView<*>?) {
                }
            }
    }


}