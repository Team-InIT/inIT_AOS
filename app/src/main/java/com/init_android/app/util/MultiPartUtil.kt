package com.init_android.app.util

import android.content.Context
import android.database.Cursor
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.Matrix
import android.media.ExifInterface
import android.net.Uri
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.MultipartBody
import okhttp3.RequestBody.Companion.toRequestBody
import java.io.ByteArrayOutputStream
import java.io.File
import java.io.IOException
import java.io.InputStream

class MultiPartUtil(context: Context) {

    val context = context

    fun uriToFilePath(uri: Uri?): MultipartBody.Part {
        val options = BitmapFactory.Options()
        val inputStream: InputStream =
            requireNotNull(context.contentResolver.openInputStream(uri!!))
        val bitmap = BitmapFactory.decodeStream(inputStream, null, options)
        val byteArrayOutputStream = ByteArrayOutputStream()
        bitmap!!.compress(Bitmap.CompressFormat.JPEG, 20, byteArrayOutputStream)

        val fileBody = byteArrayOutputStream.toByteArray()
            .toRequestBody(
                "image/*".toMediaTypeOrNull(),
                0
            )

        val part = MultipartBody.Part.createFormData(
            "file",
            File(uri.toString()).name,
            fileBody
        )

        return part
    }
}