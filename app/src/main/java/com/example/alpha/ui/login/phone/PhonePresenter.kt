package com.example.alpha.ui.login.phone

import android.content.Context
import android.os.Bundle
import android.util.Log
import com.example.alpha.data.network.ApiService
import com.example.alpha.data.network.model.LoginResponse
import com.example.alpha.data.preferences.UserPreferencesHelper
import com.example.alpha.util.Utils
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class PhonePresenter(private val context: Context, private val phoneView: PhoneContract.View)
    : PhoneContract.Presenter {

    init {
        phoneView.mPresenter = this
    }

    override fun login(phone: String) {
        phoneView.disableSend()
        ApiService.create().login(phone, Utils.getPhoneUdid(context))
            .enqueue(object : Callback<LoginResponse> {
            override fun onResponse(call: Call<LoginResponse>, response: Response<LoginResponse>) {
                Log.i("ResponseInfo", response.body().toString())
                if (response.code() == 200) {
                    /*val userPrefs = UserPreferencesHelper(context)
                    userPrefs.savePhone(bodyData?.phone)*/
                    phoneView.setMessage("ارسال اطلاعات موفقیت آمیز بود. منتظر باشید!")
                    val bundle = Bundle()
                    bundle.putSerializable("data", response.body())
                    phoneView.openNextActivity(bundle)
                } else {
                    phoneView.setMessage("ارسال اطلاعات با مشکل مواجه شد.")
                    phoneView.enableSend()
                }
            }

            override fun onFailure(call: Call<LoginResponse>, t: Throwable) {
                Log.e("Info", t.message.toString())
                phoneView.setMessage("ارسال اطلاعات با مشکل مواجه شد.")
                phoneView.enableSend()
            }

        })
    }

}