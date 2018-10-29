package com.giles.cibd.data.local

interface PreferencesHelper{
    fun getCookie(): String
    fun setCookie(cookie : String)

}