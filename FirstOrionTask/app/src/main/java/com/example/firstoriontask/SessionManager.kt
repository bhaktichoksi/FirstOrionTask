package com.example.firstoriontask

import android.content.Context
import android.provider.Settings
import java.util.*
import com.google.gson.reflect.TypeToken
import com.google.gson.Gson
import android.preference.PreferenceManager
import android.content.SharedPreferences
import com.example.UserData
import kotlin.collections.ArrayList

class SessionManager {

    fun saveArrayOfObjectList(context: Context, list: List<UserData>, key: String) {
        val prefs = PreferenceManager.getDefaultSharedPreferences(context)
        val editor = prefs.edit()
        val gson = Gson()
        val json = gson.toJson(list)
        editor.putString(key, json)
        editor.apply()     // This line is IMPORTANT !!!
    }

    fun getArrayOfObjectList(context: Context, key: String): List<UserData> {

        val prefs = PreferenceManager.getDefaultSharedPreferences(context)
        val gson = Gson()
        val json = prefs.getString(key, null)
        val type = object : TypeToken<List<UserData>>() {
        }.type
        return gson.fromJson(json, type)
    }


    fun clear(context: Context) {
        val editor = context.getSharedPreferences("", Context.MODE_PRIVATE).edit().clear().commit()
    }
}