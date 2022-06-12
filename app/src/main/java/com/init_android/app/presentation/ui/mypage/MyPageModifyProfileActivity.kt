package com.init_android.app.presentation.ui.mypage

import android.Manifest
import android.app.Activity
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.activity.viewModels
import androidx.core.content.ContextCompat
import com.bumptech.glide.Glide
import com.init_android.R
import com.init_android.app.presentation.ui.mypage.viewmodel.MyPageViewModel
import com.init_android.app.util.MultiPartUtil
import com.init_android.databinding.ActivityMyPageModifyProfileBinding
import com.playtogether_android.app.presentation.base.BaseActivity
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.RequestBody.Companion.toRequestBody
import retrofit2.http.Part

class MyPageModifyProfileActivity :
    BaseActivity<ActivityMyPageModifyProfileBinding>(R.layout.activity_my_page_modify_profile) {

    private var getResult: ActivityResultLauncher<Intent>? = null
    var fileUri: Uri? = null
    private val myPageViewModel: MyPageViewModel by viewModels()

    // 갤러리 접근 권한 런처
    private val requestPermissionLauncher =
        registerForActivityResult(ActivityResultContracts.RequestPermission())
        { isGranted: Boolean ->
            if (isGranted) { // 권한 획득 성공시
                // 갤러리 열기
                openGallery()
            } else { // 권한 획득 거부 시
                Toast.makeText(this, "갤러리에 접근하기 위해서는 권한이 필요합니다.", Toast.LENGTH_SHORT).show()
            }
        }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        initPhotoPick()
        getPhotoUri()
        backBtnClick()
        initSetting()
        initNetwork()

        with(binding) {
            makeRadioButtonLevel(btnTop, btnMiddle, btnBottom)
        }
    }

    // 이미지 선택
    private fun initPhotoPick() {
        binding.tvMyPageEditProfileImage.setOnClickListener {
            requestPermission()
        }
    }

    // 권한 체크 함수
    private fun requestPermission() {
        when {
            ContextCompat.checkSelfPermission(
                this,
                Manifest.permission.READ_EXTERNAL_STORAGE
            ) == PackageManager.PERMISSION_GRANTED -> {  // 이미 권한부여를 받았기에 권한이 필요한 작업 수행
                // 갤러리 열기
                openGallery()
            }

            else -> {
                requestPermissionLauncher.launch(Manifest.permission.READ_EXTERNAL_STORAGE)   // 바로 권한요청하는 경우
            }
        }
    }

    // 갤러리 열기
    private fun openGallery() {
        val profileIntent = Intent()
        profileIntent.type = "image/*"
        profileIntent.action = Intent.ACTION_GET_CONTENT
        getResult?.launch(profileIntent)
    }

    // 이미지 받아오기
    private fun getPhotoUri() {
        getResult =
            registerForActivityResult(ActivityResultContracts.StartActivityForResult()) {
                if (it.resultCode == Activity.RESULT_OK) {
                    try {
                        val uri: Uri? = (it.data)?.data
                        Glide.with(this).load(uri).into(binding.ivMyPageProfile)
                        fileUri = uri
                    } catch (e: Exception) {

                    }
                } else if (it.resultCode == Activity.RESULT_CANCELED) {
                    Toast.makeText(this, "선택 취소", Toast.LENGTH_SHORT).show()
                }
            }
    }


    private fun backBtnClick() {
        binding.ivMyPageEditProfileBack.setOnClickListener {
            finish()
        }
    }

    private fun initSetting() {
        val name = intent.getStringExtra("name") ?: ""
        val position = intent.getStringExtra("position") ?: ""
        val level = intent.getStringExtra("level") ?: ""

        //추후에 introduction 서버통신으로 받아오기
        val introduction = intent.getStringExtra("introduction") ?: ""

        binding.etMyPageEditProfileName.setText(name)
        binding.etMyPageIntroduction.setText(introduction)

        if (level == "상") {
            binding.btnTop.isSelected = true
        } else if (level == "중") {
            binding.btnMiddle.isSelected = true
        } else if (level == "하") {
            binding.btnBottom.isSelected = true
        }
    }

    //라디오 버튼 속성 추가
    private fun makeRadioButtonLevel(view1: View, view2: View, view3: View) {
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


    private fun initNetwork() {
        binding.tvFinish.setOnClickListener {
            val file = MultiPartUtil(this).uriToFilePath(fileUri)
            val mNum = 1.toString().toRequestBody("text/plain".toMediaTypeOrNull())
            val mName = binding.etMyPageEditProfileName.text.toString().toRequestBody("text/plain".toMediaTypeOrNull())
            val mPosition = 1.toString().toRequestBody("text/plain".toMediaTypeOrNull())
            val mLevel = 1.toString().toRequestBody("text/plain".toMediaTypeOrNull())
            val mIntroduction = binding.etMyPageIntroduction.text.toString().toRequestBody("text/plain".toMediaTypeOrNull())


            myPageViewModel.postUpdateProfile(file, mNum, mName, mPosition, mLevel, mIntroduction)

            myPageViewModel.updateProfile.observe(this) {
                if(it.code == 201) {
                    finish()
                }
            }
        }
    }

}