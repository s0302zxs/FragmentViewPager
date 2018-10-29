package com.giles.cibd.data.model

import com.google.gson.annotations.SerializedName

class LoginResponse {
    @SerializedName("code")
    var code: Int = 0
    @SerializedName("message")
    var message: String? = null
}