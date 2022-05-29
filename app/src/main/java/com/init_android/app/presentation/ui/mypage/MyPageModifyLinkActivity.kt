package com.init_android.app.presentation.ui.mypage

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.init_android.R
import com.init_android.databinding.ActivityMyPageModifyLinkBinding
import com.playtogether_android.app.presentation.base.BaseActivity

class MyPageModifyLinkActivity : BaseActivity<ActivityMyPageModifyLinkBinding>(R.layout.activity_my_page_modify_link) {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        backBtnListener()

    }

    //뒤로가기 버튼 클릭 리스너
    private fun backBtnListener() {
        binding.ivMyPageLinkBack.setOnClickListener {
            finish()
        }
    }
}