package com.init_android.app.util

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.util.Log
import java.nio.ByteBuffer

class BitmapUtil {
    /*fun getImg(input: Map<*, *>): Bitmap? {
        Log.d("maptest",input.toString())
        //var data = input as LinkedTreeMap<*, *>
        var array = input["data"] as ArrayList<Double>
        Log.d("plz","array"+array.toString())
        var bitmap: Bitmap? = getOutputImage(array)
        return bitmap
    }

    /*fun convertBitmap(input: ArrayList<Double>): Bitmap {
        val arr = exByte(input)
        try {
            Log.d("plz",input.toString())
            Log.d("plz",arr.toString())
            return BitmapFactory.decodeByteArray(arr,0,arr.size)
           // val inputStream = arr.inputStream()
            //return BitmapFactory.decodeStream(inputStream)
            //Log.d("hell","please")
            // var bis: BufferedInputStream = BufferedInputStream(inputStream, inputStream.)
            //return BitmapFactory.decodeByteArray(arr, 0, arr.size)
        } catch (e: Exception) {
            Log.d("hell",e.message.toString())
            throw RuntimeException(e)
        }
    }*/

    private fun getOutputImage(array: ArrayList<Double>): Bitmap {
        val byteBuffer = exByte(array)

        byteBuffer.rewind() // Rewind the output buffer after running.

        val bitmap = Bitmap.createBitmap(100, 100, Bitmap.Config.ARGB_8888)
        val pixels = IntArray(100 * 100) // Set your expected output's height and width
        for (i in 0 until 100 * 100) {
            val a = 0xFF
            val r: Float = byteBuffer.float * 255.0f
            val g: Float = byteBuffer.float * 255.0f
            val b: Float = byteBuffer.float * 255.0f
            pixels[i] = a shl 24 or (r.toInt() shl 16) or (g.toInt() shl 8) or b.toInt()
        }
        bitmap.setPixels(pixels, 0, 100, 0, 0, 100, 100)

        return bitmap
    }

    fun exByte(list: ArrayList<Double>): ByteBuffer {
        var list2: MutableList<ByteBuffer> = mutableListOf()
        val bytes = ByteArray(8)
        for (i in 0 until list.size) {

            list2.add(ByteBuffer.wrap(bytes).putDouble(list[i]))
            //list2.add(list[i].bytes())
            //list2.add(list[i].toInt().toByte())
        }
        var arr = list2
        //var arr = list2.toByteArray()
        //Log.d("plz",arr.toString())
        return arr
    } */

}