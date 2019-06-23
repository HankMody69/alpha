package com.example.alpha.data.network.model

import com.google.gson.annotations.SerializedName

data class CurrencyRate(
    @SerializedName("id") var id: String,
    @SerializedName("buy") var buy: String,
    @SerializedName("sell") var sell: String,
    @SerializedName("currencyCode") var code: String
)