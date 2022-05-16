package com.init_android.app.presentation.oepn.project

import android.annotation.SuppressLint
import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import androidx.annotation.LayoutRes
import androidx.databinding.DataBindingUtil
import com.init_android.R
import com.init_android.databinding.ItemKindSpinnerBinding
import com.init_android.databinding.SpinnerItemBinding

class SpinnerAdapter(
    context: Context,
    @LayoutRes private var resId: Int,
    private var values: List<String>
) : ArrayAdapter<String>(context, resId, values) {

    override fun getCount() = values.size

    override fun getItem(position: Int) = values[position]

    //눈에 보여지는 Spinner 모습
    //클릭안했을때
    @SuppressLint("ViewHolder")
    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        val binding: ItemKindSpinnerBinding = DataBindingUtil.inflate(
            LayoutInflater.from(parent.context),
            R.layout.item_kind_spinner, parent, false
        )

        binding.textSignupMajorinfoMajor.text = "모바일"

        return binding.root
    }

    //메뉴 나타났을떄
    override fun getDropDownView(position: Int, convertView: View?, parent: ViewGroup): View {
        val binding: SpinnerItemBinding = DataBindingUtil.inflate(
            LayoutInflater.from(parent.context),
            R.layout.spinner_item, parent, false
        )

        binding.textSpinner.text = values[position]




        return binding.root
    }
}