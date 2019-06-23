package com.example.alpha.ui.login.code

import android.content.Context
import android.util.Log
import com.example.alpha.data.network.ApiService
import com.example.alpha.data.network.model.ActivationResponse
import com.example.alpha.data.preferences.UserPreferencesHelper
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class CodePresenter(private val context: Context, private val codeView: CodeContract.View) : CodeContract.Presenter {

    init {
        codeView.mPresenter = this
    }

    override fun activateLogin(code: String, phone: String, udid: String) {
        ApiService.create().activate(phone, udid, android.os.Build.MODEL, "android", null, code)
            .enqueue(object : Callback<ActivationResponse> {

            override fun onResponse(call: Call<ActivationResponse>, response: Response<ActivationResponse>) {
                if (response.isSuccessful && response.code() == 200) {
                    codeView.disableSubmit()
                    codeView.setMessage("ارسال اطلاعات موفقیت آمیز بود. منتظر باشید!")
                    val prefs = UserPreferencesHelper(context)
                    val bodyData = response.body()
                    prefs.savePhone(bodyData?.phone)
                    //prefs.saveUdid(bodyData?.udid)
                    //prefs.saveId(bodyData?.id)
                    prefs.saveToken(bodyData?.token)
                    codeView.done()
                } else {
                    Log.i("Failed Response", response.message())
                    codeView.setMessage("ارسال اطلاعات با مشکل مواجه شد.")
                    codeView.enableSubmit()
                }
            }

            override fun onFailure(call: Call<ActivationResponse>, t: Throwable) {
                codeView.setMessage("ارسال اطلاعات با مشکل مواجه شد.")
                codeView.enableSubmit()
            }

        })
    }

}