package com.init_android.app.presentation.ui.mypage

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.activity.viewModels
import com.init_android.R
import com.init_android.app.data.request.mypage.RequestModifyBasicInfo
import com.init_android.app.presentation.ui.mypage.viewmodel.MyPageViewModel
import com.init_android.databinding.ActivityMyPageModifyBasicInfoBinding
import com.playtogether_android.app.presentation.base.BaseActivity

class MyPageModifyBasicInfoActivity : BaseActivity<ActivityMyPageModifyBasicInfoBinding>(R.layout.activity_my_page_modify_basic_info) {

    private val myPageViewModel : MyPageViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        backBtnListener()
        initSetting()
        initNetwork()

        with(binding) {
            makeRadioButtonBelong(btnInSchool,btnRestSchool, btnGraduate)
            makeRadioButtonGender(btnMan,btnWoman,btnEtc)
        }
    }


    //intent로 넘어온 값 세팅
    private fun initSetting() {
        val email = intent.getStringExtra("email") ?: ""
        val belong = intent.getStringExtra("belong") ?: ""
        val academic = intent.getStringExtra("academic") ?: ""
        val gender = intent.getStringExtra("gender") ?: ""

        Log.d("test", email)

        binding.etMyPageBasicInfoEmail.setText(email)
        binding.etMyPageBasicInfoBelong.setText(belong)

        if(academic == "재학") {
            binding.btnInSchool.isSelected = true
        } else if (academic == "휴학") {
            binding.btnRestSchool.isSelected = true
        } else if (academic == "졸업") {
            binding.btnGraduate.isSelected = true
        }

        if(gender == "남") {
            binding.btnMan.isSelected = true
        } else if(gender == "여") {
            binding.btnWoman.isSelected = true
        } else if(gender == "기타") {
            binding.btnEtc.isSelected = true
        }

    }

    //버튼 라디오 버튼으로 전환 -> 소속
    private fun makeRadioButtonBelong(view1 : View, view2 : View, view3 : View) {
        view1.setOnClickListener {
            if (!view1.isSelected) {
                view1.isSelected = true
                view2.isSelected = false
                view3.isSelected = false
            }
        }
        view2.setOnClickListener {
            if (!view2.isSelected) {
                view1.isSelected = false
                view2.isSelected = true
                view3.isSelected = false
            }
        }
        view3.setOnClickListener {
            if (!view3.isSelected) {
                view1.isSelected = false
                view2.isSelected = false
                view3.isSelected = true
            }
        }
    }

    //버튼 라디오 버튼으로 전환 -> 성별
    private fun makeRadioButtonGender(view1 : View, view2 : View, view3 : View) {
        view1.setOnClickListener {
            if (!view1.isSelected) {
                view1.isSelected = true
                view2.isSelected = false
                view3.isSelected = false
            }
        }
        view2.setOnClickListener {
            if (!view2.isSelected) {
                view1.isSelected = false
                view2.isSelected = true
                view3.isSelected = false
            }
        }
        view3.setOnClickListener {
            if (!view3.isSelected) {
                view1.isSelected = false
                view2.isSelected = false
                view3.isSelected = true
            }
        }
    }

    //뒤로가기 버튼 클릭 리스너
    private fun backBtnListener() {
        binding.ivMyPageBasicInfoBack.setOnClickListener {
            finish()
        }
    }


    //정보 수정 클릭 리스너
    private fun initNetwork() {
        var mAcademic = 0
        var gender = 0
        binding.tvFinish.setOnClickListener {
            if(binding.btnInSchool.isSelected) {
                mAcademic = 0
            } else if (binding.btnRestSchool.isSelected) {
                mAcademic = 1
            } else if (binding.btnGraduate.isSelected) {
                mAcademic =2
            }

            if(binding.btnMan.isSelected) {
                gender = 0
            } else if(binding.btnWoman.isSelected) {
                gender = 1
            } else if(binding.btnEtc.isSelected) {
                gender = 2
            }
            val requestModifyBasicInfo = RequestModifyBasicInfo(
                mNum = 1,
                mEmail = binding.etMyPageBasicInfoEmail.text.toString(),
                mDept = binding.etMyPageBasicInfoBelong.text.toString(),
                mAcademic = mAcademic,
                mGender = gender
            )
            myPageViewModel.postModifyBasicInfo(requestModifyBasicInfo)

            Toast.makeText(this, "기본 정보 수정이 완료되었습니다.", Toast.LENGTH_SHORT).show()
            finish()
        }
    }
}