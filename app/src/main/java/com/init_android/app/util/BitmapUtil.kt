package com.init_android.app.util

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.util.Log

class BitmapUtil {
    fun getImg(input: Map<*, *>): Bitmap? {
        Log.d("maptest", input.toString())
        //var data = input as LinkedTreeMap<*, *>
        var array = input["data"] as ArrayList<Double>
        var bitmap: Bitmap? = convertBitmap(array)
        return bitmap
    }

    fun convertBitmap(input: ArrayList<Double>): Bitmap {
        val arr = exByte(input)
        try {

            return BitmapFactory.decodeByteArray(arr, 0, arr.size)

        } catch (e: Exception) {
            throw RuntimeException(e)
        }
    }

    fun exByte(list: ArrayList<Double>): ByteArray {
        var list2: MutableList<Byte> = mutableListOf()
        for (i in 0 until list.size) {
            list2.add(list[i].toInt().toByte())
        }
        var arr = list2.toByteArray()
        return arr
    }
}