package com.init_android.app.presentation.ui.feed.write

import android.Manifest
import android.app.Activity
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.activity.viewModels
import androidx.core.content.ContextCompat
import androidx.fragment.app.viewModels
import com.bumptech.glide.Glide
import com.init_android.R
import com.init_android.app.data.request.RequestAddFeed
import com.init_android.app.presentation.ui.feed.FeedViewModel
import com.init_android.app.presentation.ui.open.project.SpinnerAdapter
import com.init_android.databinding.ActivityFeedWritingBinding
import com.playtogether_android.app.presentation.base.BaseActivity

class FeedWritingActivity :
    BaseActivity<ActivityFeedWritingBinding>(R.layout.activity_feed_writing) {

    private var getResult: ActivityResultLauncher<Intent>? = null
    private val feedViewModel : FeedViewModel by viewModels()

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
        initSpinner()
    }

    private fun initSpinner() {
        val list = listOf("선택해주세요.", "웹", "모바일", "게임")
        val spinnerAdapter = SpinnerAdapter(this, R.layout.spinner_item, list)
        binding.spinnerProjectType.adapter = spinnerAdapter
        binding.spinnerProjectType.dropDownVerticalOffset = binding.spinnerProjectType.bottom

        val adapter: ArrayAdapter<String> =
            ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, list)
        binding.spinnerProjectType.adapter = adapter
        binding.spinnerProjectType.setSelection(0)
    }

    // 이미지 선택
    private fun initPhotoPick() {
        binding.cardViewSelectPhoto.setOnClickListener {
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
                        Glide.with(this).load(uri).into(binding.ivProjectImg)
                    } catch (e: Exception) {

                    }
                } else if (it.resultCode == Activity.RESULT_CANCELED) {
                    Toast.makeText(this, "선택 취소", Toast.LENGTH_SHORT).show()
                }
            }
    }

    // 피드 등록
    private fun tryPostFeed(){
        binding.btnPost.setOnClickListener {

            /*val requestAddFeed = RequestAddFeed(
                binding.tvFeedTitle.text.toString(),
                0,
                binding.etvDetail.text.toString(),
                binding.etvLink.text.toString(),

            )*/

            //feedViewModel.postAddFeed()
        }
    }
}