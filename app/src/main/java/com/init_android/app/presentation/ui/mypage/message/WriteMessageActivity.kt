package com.init_android.app.presentation.ui.mypage.message

import android.graphics.Color
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.viewModels
import com.init_android.R
import com.init_android.app.data.model.SelectableData
import com.init_android.app.data.request.RequestFinishProject
import com.init_android.app.data.request.mypage.RequestMyInfo
import com.init_android.app.data.request.project.RequestApplyProject
import com.init_android.app.presentation.ui.feed.FeedViewModel
import com.init_android.app.presentation.ui.main.MainViewModel
import com.init_android.app.presentation.ui.mypage.viewmodel.MyPageViewModel
import com.init_android.app.util.CustomBottomSheetDialog
import com.init_android.app.util.CustomDialog
import com.init_android.databinding.ActivityWriteMessageBinding
import com.playtogether_android.app.presentation.base.BaseActivity

class WriteMessageActivity :
    BaseActivity<ActivityWriteMessageBinding>(R.layout.activity_write_message) {
    private val mainViewModel: MainViewModel by viewModels()
    private val myPageViewModel: MyPageViewModel by viewModels()
    val partBottomSheetDialog = CustomBottomSheetDialog("제안 할 프로젝트", "완료")
    private val feedViewModel: FeedViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        backBtnListener()
        bottomSheetListener()
        initNetwork()
        finishBtnClick()
    }

    private fun backBtnListener() {
        binding.ivMessageMovePage.setOnClickListener {
            finish()
        }
    }


    private fun initNetwork() {
        val mNum = intent.getIntExtra("pNum", 2)
        //mainViewModel.otherNum.value = mNum

        val requestMyInfo = RequestMyInfo(
            //인텐트로 넘겨 온 mNum 세팅
            mNum = mNum
        )


        myPageViewModel.postMyInfo(requestMyInfo)

        myPageViewModel.myInfoData.observe(this) {
            binding.user = it.mInfo
        }
    }

    private fun bottomSheetListener() {
        //더미 데이터 넣는 부분
        // 원래 여기서 서버통신 받아옴
        val requestFinishProject = RequestFinishProject(1)
        feedViewModel.postFinishProject(requestFinishProject)
        feedViewModel.finishProject.observe(this) {
            Log.d("Test", "bottomSheet")
            val data = it.project.toMutableList()
            val dataList = mutableListOf<SelectableData>()
            for (i in data.indices) {
                dataList.add(SelectableData(data[i].pNum, data[i].pTitle, false))
            }
            partBottomSheetDialog.setDataList(dataList)
            //projectListBottomSheetDialog.setDataList(SelectableData(it.pNum ?: 1, it.pTitle ?: "test", false) as MutableList<SelectableData>)
        }


        //버튼 클릭해서 바텀시트 생성되는 부분
        binding.tvMessageProject.setOnClickListener {
            partBottomSheetDialog.show(
                supportFragmentManager,
                partBottomSheetDialog.tag
            )

            //클릭 완료되었을때 일어나는 리스너
            partBottomSheetDialog.setCompleteListener {
                val part = partBottomSheetDialog.getSelectedData()
                //뷰모델에 넣어주는 코드
                // mainViewModel.part.value = part?.name
                // mainViewModel.partNum.value = part?.id
                partBottomSheetDialog.binding.btnBottomsheetCancel

               binding.tvMessageProject.text = part?.name
                binding.tvMessageProject.setTextColor(Color.parseColor("#000000"))
                partBottomSheetDialog.binding.btnBottomsheetComplete.setOnClickListener {
                    Log.d("클릭", "완료")

                }
            }
        }

    }

    private fun finishBtnClick() {
        binding.tvFinish.setOnClickListener {
            val title = "메시지를 보내시겠습니까?\n 한번 보낸 메시지는 취소할 수 없습니다."
            val dialog = CustomDialog(this, title)
            dialog.showChoiceDialog(R.layout.dialog_yes_no)

            val pNum = intent.getIntExtra("pNum", 1)
            val mNum = 1

            Log.d("pNum", "" + pNum)

            dialog.setOnClickedListener(object : CustomDialog.ButtonClickListener {
                override fun onClicked(num: Int) {
                    if (num == 1) {
                        Toast.makeText(this@WriteMessageActivity, "메시지 전송이 완료되었습니다.", Toast.LENGTH_SHORT).show()
                        finish()
                    } else {
                        Toast.makeText(this@WriteMessageActivity, "메시지 전송이 취소되었습니다.", Toast.LENGTH_SHORT).show()
                    }
                }
            })
        }
    }
}