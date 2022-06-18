package com.init_android.app.presentation.ui.mypage

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.LinearLayout
import android.widget.Toast
import androidx.activity.viewModels
import androidx.fragment.app.viewModels
import com.google.android.material.chip.Chip
import com.init_android.R
import com.init_android.app.data.request.mypage.RequestModifyStack
import com.init_android.app.data.request.mypage.RequestMyInfo
import com.init_android.app.presentation.ui.mypage.viewmodel.MyPageViewModel
import com.init_android.databinding.ActivityMyPageModifyStackBinding
import com.playtogether_android.app.presentation.base.BaseActivity
import java.util.ArrayList

class MyPageModifyStackActivity : BaseActivity<ActivityMyPageModifyStackBinding>(R.layout.activity_my_page_modify_stack) {

    private val myPageViewModel: MyPageViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        initChipGroup()
        //initNetwork()
        modifyStack()
        backBtnListener()

    }

    override fun onResume() {
        super.onResume()
        initNetwork()
    }


    private fun backBtnListener() {
        binding.ivMyPageStackBack.setOnClickListener {
            finish()
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

        binding.tvFinish.setOnClickListener {
            val chipList = ArrayList<String>()
            for (i: Int in 1..binding.chipGroup.childCount) {
                val chip: Chip = binding.chipGroup.getChildAt(i - 1) as Chip
                chipList.add(chip.text.toString())
            }

            var output = "count: ${chipList.size}\n"
            for (i in chipList) {
                val lastList = chipList.get(chipList.size-1)
                if ("$i" == lastList)
                    output += "$i"
                else {
                    output += "$i,"
                }
            }
            Log.d("test", output)
        }
    }

    private fun initNetwork() {
        val requestMyInfo = RequestMyInfo(
            mNum = 1
        )

        myPageViewModel.postMyInfo(requestMyInfo)

        myPageViewModel.myInfoData.observe(this) {
            val list = it.mInfo.mStacks

            Log.d("Test", "" + list?.size!!)

            if (list?.size != null) {
                binding.chipGroup.removeAllViews()
                for (i in 0 until list?.size!!) {
                    val chip = Chip(binding.chipGroup.getContext())
                    val layoutParams = LinearLayout.LayoutParams(
                        LinearLayout.LayoutParams.WRAP_CONTENT,
                        LinearLayout.LayoutParams.WRAP_CONTENT
                    )
                    layoutParams.setMargins(5, 0, 5, 0)
                    chip.layoutParams = layoutParams

                    chip.setText(list.get(i))
                    chip.closeIcon!!.isVisible
                    chip.isCloseIconEnabled = true
                    //chip.chipBackgroundColor = resources.getColorStateList(R.color.main_default)
                    //chip.setTextColor(resources.getColorStateList(R.color.white))
                    //chip.closeIconTint = resources.getColorStateList(R.color.white)
                    chip.isClickable = true
                    chip.isCheckable = false
                    binding.chipGroup.addView(chip)

                }
            }
        }
    }

    private fun modifyStack() {
        binding.tvFinish.setOnClickListener {

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

            val requestModifyStack = RequestModifyStack(
                mNum = 1,
                mStack = output

            )



            myPageViewModel.postModifyStack(requestModifyStack)
            myPageViewModel.modifyStack.observe(this) {
                if(it.code == 201) {
                    finish()
                } else {
                    myPageViewModel.postModifyStack(requestModifyStack)
                }
            }
        }
    }

}