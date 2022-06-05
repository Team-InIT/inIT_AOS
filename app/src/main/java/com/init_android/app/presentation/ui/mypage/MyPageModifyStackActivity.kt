package com.init_android.app.presentation.ui.mypage

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.google.android.material.chip.Chip
import com.init_android.R
import com.init_android.databinding.ActivityMyPageModifyStackBinding
import com.playtogether_android.app.presentation.base.BaseActivity
import java.util.ArrayList

class MyPageModifyStackActivity : BaseActivity<ActivityMyPageModifyStackBinding>(R.layout.activity_my_page_modify_stack) {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        initChipGroup()

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

}