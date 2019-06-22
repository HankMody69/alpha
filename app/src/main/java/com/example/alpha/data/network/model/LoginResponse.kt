package com.example.alpha.data.network.model

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class LoginResponse(
    @SerializedName("id") var id: String?,
    @SerializedName("name") var name: String?,
    @SerializedName("phone") var phone: String,
    @SerializedName("udid") var udid: String,
    @SerializedName("firebaseToken") var firebaseToken: String?
) : Serializable