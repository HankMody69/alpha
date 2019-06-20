package com.example.alpha.data.network

import com.example.alpha.data.network.model.LoginResponse
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.HTTP

interface ApiService {

    @FormUrlEncoded
    @HTTP(method = "CLAIM", path = "apiv1/claim")
    fun login(@Field("phone") phone: String, @Field("udid") udid: String): Call<LoginResponse>

    companion object {

        private const val BASE_URL = "http://nightly-alpha.carrene.com/"

        fun create() = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(ApiService::class.java)

    }

}