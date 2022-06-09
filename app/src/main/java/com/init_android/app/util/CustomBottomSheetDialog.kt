package com.init_android.app.util


import android.content.DialogInterface
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.init_android.R
import com.init_android.app.data.model.SelectableData
import com.init_android.app.data.response.ResponseFinishProject
import com.init_android.app.presentation.ui.home.viewmodel.mainViewModel
import com.init_android.databinding.FragmentCustomBottomSheetDialogBinding


class CustomBottomSheetDialog(private val title: String) : BottomSheetDialogFragment() {

    private val mainViewModel: mainViewModel by activityViewModels {
        object : ViewModelProvider.Factory {
            override fun <T : ViewModel> create(modelClass: Class<T>): T {
                return mainViewModel() as T
            }
        }
    }

    // 바텀시트 타이틀
    private var _titleData = MutableLiveData<String>()
    val titleData: LiveData<String>
        get() = _titleData

    var completeOperation: () -> Unit = { }

    private var SelectAdapter: SelectAdapter
    private lateinit var _binding: FragmentCustomBottomSheetDialogBinding
    val binding get() = _binding!!
    var link = DataToFragment()

    init {
        SelectAdapter = SelectAdapter(link)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = DataBindingUtil.inflate(
            inflater,
            R.layout.fragment_custom_bottom_sheet_dialog,
            container,
            false
        )

        initBottomSheetSetting()
        initTitle()
        initAdapter()
        setClickListener()
        observeSelectedData()
        DataToFragment()

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.clCustomBottomSheet.layoutParams.height =
            resources.displayMetrics.heightPixels * 72 / 100
        binding.tvBottomsheeetTitle.text = title
        binding.executePendingBindings()
    }

    override fun onDismiss(dialog: DialogInterface) {
        super.onDismiss(dialog)
        SelectAdapter.clearSelect()
    }

    private fun initBottomSheetSetting() {
        binding.btnBottomsheetComplete.isEnabled = false
        binding.btnBottomsheetComplete.isSelected = true
    }

    private fun setClickListener() {
        binding.btnBottomsheetCancel.setOnClickListener {
            activity?.supportFragmentManager!!.beginTransaction().remove(this).commit()
        }
        binding.fragment = this
    }

    //타이틀 왼쪽에 표시되는 제목
    private fun initTitle() {
        titleData.observe(viewLifecycleOwner) {
            binding.tvBottomsheeetTitle.text = titleData.value
        }
        _titleData.value = title
    }


    private fun initAdapter() {
        // Recycler view 구분선 추가
        val decoration =
            CustomDecoration(1.dpToPxF, 0.0f, requireContext().getColor(R.color.gray_1))
        binding.rvBottomsheet.addItemDecoration(decoration)

        binding.rvBottomsheet.adapter = SelectAdapter
    }

    private fun observeSelectedData() {
        SelectAdapter.selectedData.observe(viewLifecycleOwner) {
            binding.btnBottomsheetComplete.isEnabled =
                SelectAdapter.selectedData.value!!.isSelected
        }
    }


    fun setCompleteListener(operation: () -> Unit) {
        completeOperation = operation
    }

    fun completeBtnListener(view: View) {
        completeOperation()
        dismiss()
    }

    fun setDataList(dataList: MutableList<SelectableData>) {
        SelectAdapter.dataList.addAll(dataList)
        SelectAdapter.notifyDataSetChanged()
    }

    fun getSelectedData(): SelectableData? {
        return SelectAdapter.selectedData.value
    }

    fun setSelectedData(dataId: Int) {
        SelectAdapter.setSelectedData(dataId)
    }


    inner class DataToFragment() {
        fun getBtnSelector(bool: Boolean) {
            binding.btnBottomsheetComplete.isSelected = bool
        }
    }
}