package com.init_android.app.presentation.ui.home.signup

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.appcompat.widget.AppCompatButton
import com.init_android.R
import com.init_android.app.ServiceCreator
import com.init_android.app.data.request.RequestSignUp
import com.init_android.app.data.response.ResponseSignIn
import com.init_android.app.data.response.ResponseSignUp
import com.init_android.app.presentation.ui.MainActivity
import com.init_android.app.presentation.ui.home.adapter.PositionAdapter
import com.init_android.app.presentation.ui.home.adapter.PositionData
import com.init_android.app.presentation.ui.home.signin.SignInActivity
import com.init_android.databinding.FragmentBaseInfoTwoBinding
import com.playtogether_android.app.presentation.base.BaseFragment
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

// 기본정보(2)
class BaseInfoTwoFragment :
    BaseFragment<FragmentBaseInfoTwoBinding>(R.layout.fragment_base_info_two) {

    private lateinit var positionAdapter: PositionAdapter
    var positionSelected = 0
    var skillSelected = 0

    var checkSkillState = false
    var checkPositionState = false

    // 넘겨줄 회원정보 배열 선언(id,pw, // 이름,이메일,소속,학적상태,성별 // 포지션,숙련도)
    private var signUpArray = arrayListOf<Any>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // 프로그레스바 진행 정도
        (activity as SignUpActivity).binding.pbSignup.progress = 100

        selectBtnInit()
        initPositionRV()

        getBundle()
        goBackBtn() // 뒤로가기

        signUpBtnEvent() // 회원가입 버튼 이벤트
    }

    // bundle 값 받아오기
    private fun getBundle() {
        val bundle = arguments
        val arrayList = bundle?.getSerializable("signUpArray")
        arrayList as ArrayList<*>

        for (i in 0 until arrayList.size) signUpArray.add(arrayList[i])
    }

    // RV 초기화
    private fun initPositionRV() {
        positionAdapter = PositionAdapter()
        binding.rvPosition.adapter = positionAdapter

        val itemList = positionAdapter.positionList

        itemList.addAll(
            listOf(
                PositionData("DESIGN", R.drawable.ic_designer, false),
                PositionData("PLAN", R.drawable.ic_planner, false),
                PositionData("IOS", R.drawable.ic_ios, false),
                PositionData("ANDROID", R.drawable.ic_android, false),
                PositionData("WEB", R.drawable.ic_website, false),
                PositionData("GAME", R.drawable.ic_game, false),
                PositionData("SERVER", R.drawable.ic_server, false)
            )
        )

        positionAdapter.notifyDataSetChanged()

        // 아이템 선택 이벤트
        positionAdapter.setItemClickListener(object : PositionAdapter.OnItemClickListener {
            override fun onClick(v: View, position: Int) {

                if (itemList[position].checkState) { // 아이템 선택됨
                    itemList[position].checkState = false // 선택 off
                    checkPositionState = false
                } else { // 아이템 선택x
                    for (i in 0 until itemList.size) { // 모든 선택 off
                        itemList[i].checkState = false
                    }
                    itemList[position].checkState = true // 클릭 요소만  선택 on
                    checkPositionState = true

                    positionSelected = position // 서버에 넘겨주기 위한 position 받아오기
                }

                checkNextBtnState() // 버튼 활성화 체크
                positionAdapter.notifyDataSetChanged() // 클릭할 때마다 데이터 갱신

            }
        })

    }

    // 숙련도 선택 이벤트
    private fun selectSkillBtn(btn: AppCompatButton) {

        btn.isSelected = !btn.isSelected

        // 나머지 버튼 비활성화
        binding.apply {
            if (btn.isSelected) {
                when (btn) {
                    btnTop -> {
                        btnMiddle.isSelected = false
                        btnBottom.isSelected = false
                    }
                    btnMiddle -> {
                        btnTop.isSelected = false
                        btnBottom.isSelected = false
                    }
                    btnBottom -> {
                        btnTop.isSelected = false
                        btnMiddle.isSelected = false
                    }

                }
                checkSkillState = true

                when (btn) {
                    btnTop -> skillSelected = 0
                    btnMiddle -> skillSelected = 1
                    btnBottom -> skillSelected = 2
                }

            } else checkSkillState = false
        }

        checkNextBtnState() // 버튼 활성화 체크
    }

    // 숙련도 버튼 이벤트 초기화
    private fun selectBtnInit() {
        binding.apply {
            btnTop.setOnClickListener { selectSkillBtn(btnTop) }
            btnMiddle.setOnClickListener { selectSkillBtn(btnMiddle) }
            btnBottom.setOnClickListener { selectSkillBtn(btnBottom) }
        }
    }

    // 다음 버튼 활성화 여부 체크
    private fun checkNextBtnState() {
        binding.btnNext.isEnabled = checkPositionState && checkSkillState
    }

    // 회원가입 버튼 이벤트
    private fun signUpBtnEvent() {

        binding.btnNext.setOnClickListener {
            // 회원가입 서버통신 시도
            tryPostSignUp()
        }


    }

    // 뒤로가기 버튼 활성화
    private fun goBackBtn() {
        binding.ibtnBack.setOnClickListener {
            (activity as SignUpActivity).finish()
        }
    }

    // todo 회원가입 서버통신 시도
    private fun tryPostSignUp() {
        signUpArray.add(positionSelected) // 포지션
        signUpArray.add(skillSelected) // 숙련도

        val requestSignUp = RequestSignUp(
            mNum = signUpArray[0] as Int,
            mType = signUpArray[1] as Int,
            mID = signUpArray[2] as String,
            mName = signUpArray[3] as String,
            mEmail = signUpArray[4] as String,
            mDept = signUpArray[5] as String,
            mAcademic = signUpArray[6] as String,
            mGender = signUpArray[7] as Int,
            mPosition = signUpArray[8] as Int,
            mLevel = signUpArray[9] as Int,
            mApproval = signUpArray[10] as Int,
            mIntroduction = signUpArray[11] as String
        )

        val call: Call<ResponseSignUp> = ServiceCreator.initService.postRegister(requestSignUp)

        call.enqueue(object: Callback<ResponseSignUp> {
            override fun onResponse(
                call: Call<ResponseSignUp>,
                response: Response<ResponseSignUp>
            ) {
                if(response.isSuccessful){ // 회원가입 성공
                    Toast.makeText(requireContext(), "회원가입 성공", Toast.LENGTH_SHORT).show()
                }else{
                    Toast.makeText(requireContext(), "회원가입 실패", Toast.LENGTH_SHORT).show()
                }
            }

            override fun onFailure(call: Call<ResponseSignUp>, t: Throwable) { // 서버 통신에러
                Log.e("NetworkTest", "error:$t")
            }

        })
    }
}