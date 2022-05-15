package com.init_android.app.presentation.oepn.project

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.init_android.R
import com.init_android.databinding.ActivityOpenProjectSecondBinding

class OpenProjectSecondActivity : AppCompatActivity() {
    private lateinit var binding : ActivityOpenProjectSecondBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityOpenProjectSecondBinding.inflate(layoutInflater)

        with(binding){
            makeRadioButton2(tvOnline, tvOffline)
            makeRadioButton2(tvMale, tvFemale)
            makeRadioButton3(tvEnroll, tvLeave, tvGraduate)
            makeRadioButton3(tvPlanHigh, tvPlanMid, tvPlanLow)
            makeRadioButton3(tvDesignHigh, tvDesignMid, tvDesignLow)
            makeRadioButton3(tvServerHigh, tvServerMid, tvServerLow)
        }

    }

    private fun makeRadioButton3(view1 : View, view2 : View, view3 : View){
        view1.setOnClickListener{
            if(!view1.isSelected){
                view1.isSelected=true
                view2.isSelected=false
                view3.isSelected=false
            }
        }
        view2.setOnClickListener{
            if(!view2.isSelected){
                view1.isSelected=false
                view2.isSelected=true
                view3.isSelected=false
            }
        }
        view3.setOnClickListener{
            if(!view3.isSelected){
                view1.isSelected=false
                view2.isSelected=false
                view3.isSelected=true
            }
        }
    }

    private fun makeRadioButton2(view1 : View, view2 : View){
        view1.setOnClickListener{
            if(!view1.isSelected){
                view1.isSelected=true
                view2.isSelected=false
            }
        }
        view2.setOnClickListener{
            if(!view2.isSelected){
                view1.isSelected=false
                view2.isSelected=true
            }
        }
    }
}