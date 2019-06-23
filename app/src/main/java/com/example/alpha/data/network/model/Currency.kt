package com.example.alpha.data.network.model

import com.google.gson.annotations.SerializedName

data class Currency(
    @SerializedName("chargeUpperBound") var chargeUppedBound: Float,
    @SerializedName("type") var type: String,
    @SerializedName("symbol") var symbol: String,
    @SerializedName("name") var name: String,
    @SerializedName("code") var code: String,
    @SerializedName("precision") var precision: String,
    @SerializedName("chargeLowerBound") var chargeLowerBound: Float
)