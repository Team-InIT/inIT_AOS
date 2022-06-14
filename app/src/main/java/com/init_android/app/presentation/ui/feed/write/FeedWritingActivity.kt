package com.init_android.app.presentation.ui.feed.write

import android.Manifest
import android.app.Activity
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.activity.viewModels
import androidx.core.content.ContextCompat
import androidx.fragment.app.viewModels
import com.bumptech.glide.Glide
import com.init_android.R
import com.init_android.app.data.model.SelectableData
import com.init_android.app.data.request.RequestAddFeed
import com.init_android.app.presentation.ui.feed.FeedViewModel
import com.init_android.app.presentation.ui.open.project.SpinnerAdapter
import com.init_android.app.util.CustomBottomSheetDialog
import com.init_android.app.util.MultiPartUtil
import com.init_android.databinding.ActivityFeedWritingBinding
import com.playtogether_android.app.presentation.base.BaseActivity
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.MultipartBody
import okhttp3.RequestBody.Companion.toRequestBody
import java.io.ByteArrayOutputStream
import java.io.File
import java.io.InputStream

class FeedWritingActivity :
    BaseActivity<ActivityFeedWritingBinding>(R.layout.activity_feed_writing) {

    private var getResult: ActivityResultLauncher<Intent>? = null
    private val feedViewModel: FeedViewModel by viewModels()
    val projectListBottomSheetDialog = CustomBottomSheetDialog("프로젝트 리스트", "완료")
    var fileUri: Uri? = null

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
        tryPostFeed()
        initBottomSheet()
    }

    private fun initSpinner() {
        val list = listOf("웹", "모바일", "게임")
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
                        fileUri = uri
                    } catch (e: Exception) {

                    }
                } else if (it.resultCode == Activity.RESULT_CANCELED) {
                    Toast.makeText(this, "선택 취소", Toast.LENGTH_SHORT).show()
                }
            }
    }

    // 프로젝트 리스트 받아오는 코드
    private fun tryGetProjectList(){

    }

    // 바텀 시트에서 정보 받아오기
    private fun initBottomSheet() {
        //더미 데이터 넣는 부분
        // 원래 여기서 서버통신 받아옴

        var partData = mutableListOf(
            SelectableData(1, "인잇", false),
            SelectableData(2, "플투", false),
            SelectableData(3, "알바집", false),
            SelectableData(4, "나도선배", false),
        )
        projectListBottomSheetDialog.setDataList(partData)

        //버튼 클릭해서 바텀시트 생성되는 부분
        binding.tvSelectProjectList.setOnClickListener {
            projectListBottomSheetDialog.show(
                supportFragmentManager,
                projectListBottomSheetDialog.tag
            )

            //클릭 완료되었을때 일어나는 리스너
            projectListBottomSheetDialog.setCompleteListener {
                val part = projectListBottomSheetDialog.getSelectedData()
                //뷰모델에 넣어주는 코드
                // mainViewModel.part.value = part?.name
                // mainViewModel.partNum.value = part?.id
                projectListBottomSheetDialog.binding.btnBottomsheetCancel
                //알럿
                // initAlert()
                binding.tvSelectProjectList.text = "인잇"
                projectListBottomSheetDialog.binding.btnBottomsheetComplete.setOnClickListener {
                    Log.d("클릭", "완료")
                    // initAlert()
                }
            }
        }
    }

    // 피드 등록
    private fun tryPostFeed() {
        binding.btnPost.setOnClickListener {
            val file = MultiPartUtil(this).uriToFilePath(fileUri)
            // val file = MultiPartUtil(this).createMultiPart(fileUri!!)
            val fTitle = "안드로이드 프로젝트입니다.".toRequestBody("text/plain".toMediaTypeOrNull())
            val fDescription = binding.etvDetail.text.toString().toRequestBody("text/plain".toMediaTypeOrNull())
            val fLink = binding.etvLink.text.toString().toRequestBody("text/plain".toMediaTypeOrNull())
            val mNum = 1.toString().toRequestBody("text/plain".toMediaTypeOrNull())
            val pNum = 4.toString().toRequestBody("text/plain".toMediaTypeOrNull())
            val fType = 1.toString().toRequestBody("text/plain".toMediaTypeOrNull())


            feedViewModel.postAddFeed(file,fTitle,fDescription,fLink,mNum,pNum,fType)

            feedViewModel.addFeed.observe(this){
                if(it.code == 201) {
                    Log.d("글쓰기","성공")
                }else{
                    Log.d("글쓰기",it.message)
                }

            }

            //feedViewModel.postAddFeed()
        }
    }
}