package com.example.alpha.data.network.model

import com.google.gson.annotations.SerializedName

data class LoginResponse(
    @SerializedName("phone") var phone: String,
    @SerializedName("udid") var udid: String
)