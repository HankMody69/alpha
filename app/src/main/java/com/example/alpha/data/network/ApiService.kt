package com.example.alpha.data.network

import com.example.alpha.data.network.model.ActivationResponse
import com.example.alpha.data.network.model.LoginResponse
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.HTTP

interface ApiService {

    @FormUrlEncoded
    @HTTP(method = "CLAIM", path = "apiv1/clients", hasBody = true)
    fun login(@Field("phone") phone: String, @Field("udid") udid: String): Call<LoginResponse>

    @FormUrlEncoded
    @HTTP(method = "BIND", path = "apiv1/clients", hasBody = true)
    fun activate(@Field("phone") phone: String, @Field("udid") udid: String,
                 @Field("deviceName") deviceName: String?, @Field("deviceType") deviceType: String?,
                 @Field("firebaseRegistrationId") firebaseId: String?, @Field("activationCode") code: String)
    : Call<ActivationResponse>

    companion object {

        private const val BASE_URL = "https://nightly-alpha.carrene.com/"

        fun create() = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(ApiService::class.java)

    }

}