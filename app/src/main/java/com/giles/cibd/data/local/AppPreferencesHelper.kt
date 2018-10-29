package com.giles.cibd.data.local

import android.content.Context
import android.content.SharedPreferences
import javax.inject.Inject

class AppPreferencesHelper : PreferencesHelper{
    val PREF_KEY_COOKIE = "PREF_KEY_COOKIE"
    private var mPrefs: SharedPreferences

    @Inject
    constructor(context: Context, prefFileName: String){
        mPrefs = context.getSharedPreferences(prefFileName, Context.MODE_PRIVATE)
    }

//    @Inject
//    constructor(){
//
//    }

    override fun getCookie(): String {
        return mPrefs.getString(PREF_KEY_COOKIE, "Test")
//        return "aaaa"
    }

    override fun setCookie(cookie: String) {
        mPrefs.edit().putString(PREF_KEY_COOKIE, cookie).apply()
    }

}