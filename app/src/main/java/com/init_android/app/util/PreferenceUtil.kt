package com.init_android.app.util

import android.content.Context
import android.content.SharedPreferences

object PreferenceUtil {

    private lateinit var prefs:SharedPreferences

    fun init(context: Context){
        prefs = context.getSharedPreferences("INIT_APP", Context.MODE_PRIVATE)
    }

    fun getString(key: String, defValue: String?): String {
        return prefs.getString(key, defValue).toString()
    }

    fun getInt(key: String, defValue: Int): Int {
        return prefs.getInt(key, defValue)
    }

    fun setString(key: String, str: String) {
        prefs.edit().putString(key, str).apply()
    }

    fun setInt(key: String, defValue: Int) {
        prefs.edit().putInt(key, defValue).apply()
    }
}