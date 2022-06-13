package com.init_android.app.presentation.ui.mypage

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.viewModels
import com.init_android.R
import com.init_android.app.data.request.mypage.RequestModifyLink
import com.init_android.app.data.request.mypage.RequestQuit
import com.init_android.app.presentation.ui.home.signin.SignInActivity
import com.init_android.app.presentation.ui.mypage.viewmodel.MyPageViewModel
import com.init_android.app.util.CustomDialog
import com.init_android.databinding.ActivityMyPageSettingBinding
import com.playtogether_android.app.presentation.base.BaseActivity

class MyPageSettingActivity :
    BaseActivity<ActivityMyPageSettingBinding>(R.layout.activity_my_page_setting) {

    private val myPageViewModel : MyPageViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        movePage()
        networkQuit()
        backBtnListener()

    }

    //뒤로가기 버튼 클릭 리스너
    private fun movePage() {
        binding.ivMypageAlarm.setOnClickListener {
            val intent = Intent(this, NotificationSettingActivity::class.java)
            startActivity(intent)
        }
    }


    private fun backBtnListener() {
        binding.imgMypageSettingMovePage.setOnClickListener {
            finish()
        }
    }

    //탈퇴 서버 통신
    private fun networkQuit() {
        binding.tvMypageQuit.setOnClickListener {
            val title = "정말 탈퇴 하시겠습니까?"
            val dialog = CustomDialog(this, title)
            dialog.showChoiceDialog(R.layout.dialog_yes_no)
            dialog.setOnClickedListener(object : CustomDialog.ButtonClickListener {
                override fun onClicked(num: Int) {
                    if (num == 1) {
                        val requestQuit = RequestQuit(
                            is_checked = true,
                            mNum = 3
                        )
                        myPageViewModel.postQuit(requestQuit)
                        val intent = Intent(this@MyPageSettingActivity, SignInActivity::class.java)
                        startActivity(intent)
                    } else {
                        Log.d("탈퇴", "실패")
                        Toast.makeText(this@MyPageSettingActivity, "탈퇴가 취소되었습니다", Toast.LENGTH_SHORT).show()
                    }
                }
            })
        }
    }
}
