package com.init_android.app.presentation.ui.mypage

import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.fragment.app.viewModels
import com.init_android.R
import com.init_android.app.data.request.mypage.RequestModifyLink
import com.init_android.app.data.request.mypage.RequestMyInfo
import com.init_android.app.presentation.ui.mypage.viewmodel.MyPageViewModel
import com.init_android.databinding.ActivityMyPageModifyLinkBinding
import com.playtogether_android.app.presentation.base.BaseActivity

class MyPageModifyLinkActivity : BaseActivity<ActivityMyPageModifyLinkBinding>(R.layout.activity_my_page_modify_link) {

    private val myPageViewModel : MyPageViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        backBtnListener()
        initSetting()
        linkModifyNetwork()

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



    //링크 정보 수정 서버 통신 성공
    private fun linkModifyNetwork() {
        binding.tvFinish.setOnClickListener {
            val requestModifyLink = RequestModifyLink(
                mNum = 1,
                mBlog = binding.etMyPageLinkBlog.text.toString(),
                mGit = binding.etMyPageLinkGithub.text.toString(),
                mNotion = binding.etMyPageLinkNotion.text.toString()
            )

            myPageViewModel.postModifyLink(requestModifyLink)

            Toast.makeText(this, "링크 정보 수정이 완료되었습니다.", Toast.LENGTH_SHORT).show()
            finish()

        }
    }
}