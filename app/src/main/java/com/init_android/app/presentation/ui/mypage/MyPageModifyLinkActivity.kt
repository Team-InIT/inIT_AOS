package com.init_android.app.presentation.ui.mypage

import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.init_android.R
import com.init_android.databinding.ActivityMyPageModifyLinkBinding
import com.playtogether_android.app.presentation.base.BaseActivity

class MyPageModifyLinkActivity : BaseActivity<ActivityMyPageModifyLinkBinding>(R.layout.activity_my_page_modify_link) {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        backBtnListener()
        initSetting()

    }

    private fun initSetting() {
        val github = intent.getStringExtra("github") ?: ""
        val notion = intent.getStringExtra("notion") ?: ""
        val blog = intent.getStringExtra("github") ?: ""

        binding.etMyPageLinkGithub.setText(github)
        binding.etMyPageLinkNotion.setText(notion)
        binding.etMyPageLinkBlog.setText(blog)

        if(binding.etMyPageLinkGithub.text.toString() != "") {
            binding.etMyPageLinkGithub.setTextColor(Color.parseColor("#FF000000"))
        }
        if(binding.etMyPageLinkNotion.text.toString() != "") {
            binding.etMyPageLinkNotion.setTextColor(Color.parseColor("#FF000000"))
        }
        if(binding.etMyPageLinkBlog.text.toString() != "") {
            binding.etMyPageLinkBlog.setTextColor(Color.parseColor("#FF000000"))
        }
    }

    //뒤로가기 버튼 클릭 리스너
    private fun backBtnListener() {
        binding.ivMyPageLinkBack.setOnClickListener {
            finish()
        }
    }
}