package com.example.alpha.ui.login.phone

import android.util.Log
import com.example.alpha.data.network.ApiService
import com.example.alpha.data.network.model.LoginResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class PhonePresenter(private val phoneView: PhoneContract.View) : PhoneContract.Presenter {

    init {
        phoneView.mPresenter = this
    }

    override fun login(phone: String, udid: String) {
        ApiService.create().login(phone, udid).enqueue(object : Callback<LoginResponse> {

            override fun onResponse(call: Call<LoginResponse>, response: Response<LoginResponse>) {
                Log.i("Success", response.message() + " - " + response.body().toString())
                phoneView.setMessage("Success")
            }

            override fun onFailure(call: Call<LoginResponse>, t: Throwable) {

            }

        })
    }

}