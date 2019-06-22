package com.example.alpha.data.network.model

import com.google.gson.annotations.SerializedName

data class ActivationResponse(
    @SerializedName("id") var id: String,
    @SerializedName("phone") var phone: String,
    @SerializedName("udid") var udid: String,
    @SerializedName("token") var token: String
)