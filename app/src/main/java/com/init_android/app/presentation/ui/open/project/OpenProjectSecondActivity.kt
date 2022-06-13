package com.init_android.app.presentation.ui.open.project

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.activity.viewModels
import com.init_android.R
import com.init_android.app.data.request.RequestAddProject
import com.init_android.app.presentation.ui.main.MainViewModel
import com.init_android.app.presentation.ui.open.viewmodel.ProjectViewModel
import com.init_android.databinding.ActivityOpenProjectSecondBinding
import com.playtogether_android.app.presentation.base.BaseActivity
import java.text.SimpleDateFormat

class OpenProjectSecondActivity :
    BaseActivity<ActivityOpenProjectSecondBinding>(R.layout.activity_open_project_second) {

    private val projectViewModel: ProjectViewModel by viewModels()
    private val mainViewModel : MainViewModel by viewModels()

    val formatter = SimpleDateFormat("yyyy-MM-dd")
    var pGender: Int? = 0
    var pAcademic: Int? = 0
    var pPlanf: Int? = 0
    var pDesignf: Int? = 0
    var pIosf: Int? = 0
    var pAosf: Int? = 0
    var pGamef: Int? = 0
    var pWebf: Int? = 0
    var pServerf: Int? = 0


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        initSetting()
        initNextBtn()
        initBackBtn()
        initNetwork()
        backBtnListener()

        with(binding) {
            makeRadioButton2(tvMale, tvFemale)
            makeRadioButton3(tvEnroll, tvLeave, tvGraduate)
            makeRadioButton3(tvPlanHigh, tvPlanMid, tvPlanLow)
            makeRadioButton3(tvDesignHigh, tvDesignMid, tvDesignLow)
            makeRadioButton3(tvWebHigh,tvWebMid,tvWebLow)
            makeRadioButton3(tvIosHigh,tvIosMid,tvIosLow)
            makeRadioButton3(tvAosHigh, tvAosMid, tvAosLow)
            makeRadioButton3(tvGameHigh, tvGameMid, tvGameLow)
            makeRadioButton3(tvServerHigh, tvServerMid, tvServerLow)
        }

    }

    //초기 화면 세팅
    private fun initSetting() {
        val pPlan = intent.getIntExtra("pPlan", 0)
        if (pPlan == 0) {
            binding.tvPlan.visibility = View.GONE
            binding.tvPlanHigh.visibility = View.GONE
            binding.tvPlanLow.visibility = View.GONE
            binding.tvPlanMid.visibility = View.GONE
        } else {
            binding.tvPlan.visibility = View.VISIBLE
            binding.tvPlanHigh.visibility = View.VISIBLE
            binding.tvPlanLow.visibility = View.VISIBLE
            binding.tvPlanMid.visibility = View.VISIBLE
        }

        val pDesign = intent.getIntExtra("pDesign", 0)
        if (pDesign == 0) {
            binding.tvDesign.visibility = View.GONE
            binding.tvDesignHigh.visibility = View.GONE
            binding.tvDesignLow.visibility = View.GONE
            binding.tvDesignMid.visibility = View.GONE
        } else {
            binding.tvDesign.visibility = View.VISIBLE
            binding.tvDesignHigh.visibility = View.VISIBLE
            binding.tvDesignLow.visibility = View.VISIBLE
            binding.tvDesignMid.visibility = View.VISIBLE
        }

        val pIos = intent.getIntExtra("pIos", 0)
        if (pIos == 0) {
            binding.tvIos.visibility = View.GONE
            binding.tvIosHigh.visibility = View.GONE
            binding.tvIosLow.visibility = View.GONE
            binding.tvIosMid.visibility = View.GONE
        } else {
            binding.tvIos.visibility = View.VISIBLE
            binding.tvIosHigh.visibility = View.VISIBLE
            binding.tvIosLow.visibility = View.VISIBLE
            binding.tvIosMid.visibility = View.VISIBLE
        }

        val pAos = intent.getIntExtra("pAos", 0)
        if (pAos == 0) {
            binding.tvAos.visibility = View.GONE
            binding.tvAosHigh.visibility = View.GONE
            binding.tvAosLow.visibility = View.GONE
            binding.tvAosMid.visibility = View.GONE
        } else {
            binding.tvAos.visibility = View.VISIBLE
            binding.tvAosHigh.visibility = View.VISIBLE
            binding.tvAosLow.visibility = View.VISIBLE
            binding.tvAosMid.visibility = View.VISIBLE
        }

        val pGame = intent.getIntExtra("pGame", 0)
        if (pGame == 0) {
            binding.tvGame.visibility = View.GONE
            binding.tvGameHigh.visibility = View.GONE
            binding.tvGameMid.visibility = View.GONE
            binding.tvGameLow.visibility = View.GONE
        } else {
            binding.tvGame.visibility = View.VISIBLE
            binding.tvGameHigh.visibility = View.VISIBLE
            binding.tvGameMid.visibility = View.VISIBLE
            binding.tvGameLow.visibility = View.VISIBLE
        }

        val pWeb = intent.getIntExtra("pWeb", 0)
        if (pWeb == 0) {
            binding.tvWeb.visibility = View.GONE
            binding.tvWebHigh.visibility = View.GONE
            binding.tvWebLow.visibility = View.GONE
            binding.tvWebMid.visibility = View.GONE
        } else {
            binding.tvWeb.visibility = View.VISIBLE
            binding.tvWebHigh.visibility = View.VISIBLE
            binding.tvWebLow.visibility = View.VISIBLE
            binding.tvWebMid.visibility = View.VISIBLE
        }

        val pServer = intent.getIntExtra("pGame", 0)
        if (pServer == 0) {
            binding.tvServer.visibility = View.GONE
            binding.tvServerHigh.visibility = View.GONE
            binding.tvServerLow.visibility = View.GONE
            binding.tvServerMid.visibility = View.GONE
        } else {
            binding.tvServer.visibility = View.VISIBLE
            binding.tvServerHigh.visibility = View.VISIBLE
            binding.tvServerLow.visibility = View.VISIBLE
            binding.tvServerMid.visibility = View.VISIBLE
        }
    }

    //뒤로가기 버튼
    private fun initBackBtn() {
        binding.tvFinish.setOnClickListener {
            finish()
        }
    }

    //끝내기 버튼튼
    private fun initNextBtn() {


        if (binding.tvMale.isSelected) {
            pGender = 0
        } else if (binding.tvFemale.isSelected) {
            pGender = 1
        } else {
            pGender = null
        }

        if (binding.tvEnroll.isSelected) {
            pAcademic = 0
        } else if (binding.tvLeave.isSelected) {
            pAcademic = 1
        } else {
            pAcademic = null
        }

        if (binding.tvPlanHigh.isSelected) {
            pPlanf = 0
        } else if (binding.tvPlanMid.isSelected) {
            pPlanf = 1
        } else if (binding.tvPlanLow.isSelected) {
            pPlanf = 2
        } else {
            pPlanf = null
        }

        if (binding.tvDesignHigh.isSelected) {
            pDesignf = 0
        } else if (binding.tvDesignMid.isSelected) {
            pDesignf = 1
        } else if (binding.tvDesignLow.isSelected) {
            pDesignf = 2
        } else {
            pDesignf = null
        }

        if (binding.tvIosHigh.isSelected) {
            pIosf = 0
        } else if (binding.tvIosMid.isSelected) {
            pIosf = 1
        } else if (binding.tvIosLow.isSelected) {
            pIosf = 2
        } else {
            pIosf = null
        }

        if (binding.tvAosHigh.isSelected) {
            pAosf = 0
        } else if (binding.tvAosMid.isSelected) {
            pAosf = 1
        } else if (binding.tvAosLow.isSelected) {
            pAosf = 2
        } else {
            pAosf = null
        }

        if (binding.tvGameHigh.isSelected) {
            pGamef = 0
        } else if (binding.tvGameMid.isSelected) {
            pGamef = 1
        } else if (binding.tvGameLow.isSelected) {
            pGamef = 2
        } else {
            pGamef = null
        }

        if (binding.tvWebHigh.isSelected) {
            pWebf = 0
        } else if (binding.tvWebMid.isSelected) {
            pWebf = 1
        } else if (binding.tvWebLow.isSelected) {
            pWebf = 2
        } else {
            pWebf = null
        }

        if (binding.tvServerHigh.isSelected) {
            pServerf = 0
        } else if (binding.tvServerMid.isSelected) {
            pServerf = 1
        } else if (binding.tvServerLow.isSelected) {
            pServerf = 2
        } else {
            pServerf = null
        }


    }

    private fun initNetwork() {
        binding.tvFinish.setOnClickListener {
            val userId = intent.getIntExtra("userId", 1)

            val requestAddProject = RequestAddProject(
                pTitle = intent.getStringExtra("pTitle").toString(),
                pType = intent.getIntExtra("pType", 0),
                pRecruitStart = formatter.parse(intent.getStringExtra("pRecruitStart")),
                pRecruitDue = formatter.parse(intent.getStringExtra("pRecruitDue")),
                pStart = formatter.parse(intent.getStringExtra("pStart")),
                pDue = formatter.parse(intent.getStringExtra("pDue")),
                pPlan = intent.getIntExtra("pPlan", 0),
                pDesign = intent.getIntExtra("pDesign", 0),
                pIos = intent.getIntExtra("pIos", 0),
                pAos = intent.getIntExtra("pAos", 0),
                pGame = intent.getIntExtra("pGame", 0),
                pWeb = intent.getIntExtra("pWeb", 0),
                pServer = intent.getIntExtra("pGame", 0),
                pDescription = intent.getStringExtra("pDescription").toString(),
                pOnOff = intent.getIntExtra("pOnOff", 0),
                pStack = intent.getStringExtra("pStack").toString(),
                mNum = mainViewModel.signData.value?.mNum ?: 1,
                pGender = pGender,
                pAcademic = pAcademic,
                pPlanf = pPlanf,
                pDesignf = pDesignf,
                pIosf = pIosf,
                pAosf = pAosf,
                pGamef = pGamef,
                pWebf = pWebf,
                pServerf = pServerf,
                mPosition = 1
            )

            projectViewModel.postOpenProject(requestAddProject)
            projectViewModel.openProject.observe(this) {
                if(it.code == 201) {
                    finish()
                } else {
                    projectViewModel.postOpenProject(requestAddProject)
                }
            }
            Log.d("test", "클릭")
            finish()
        }
    }

    private fun makeRadioButton3(view1: View, view2: View, view3: View) {
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

    private fun makeRadioButton2(view1: View, view2: View) {
        view1.setOnClickListener {
            if (!view1.isSelected) {
                view1.isSelected = true
                view2.isSelected = false
            }
        }
        view2.setOnClickListener {
            if (!view2.isSelected) {
                view1.isSelected = false
                view2.isSelected = true
            }
        }
    }

    private fun backBtnListener() {
        binding.ivOpenProjectBack.setOnClickListener {
            finish()
        }
    }
}