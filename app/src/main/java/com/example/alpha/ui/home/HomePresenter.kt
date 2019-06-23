package com.example.alpha.ui.home

import android.app.Activity
import android.content.Context
import android.content.Intent
import com.example.alpha.data.preferences.UserPreferencesHelper
import com.example.alpha.data.preferences.model.User
import com.example.alpha.ui.login.phone.PhoneActivity

class HomePresenter(private val context: Context, private val homeView: HomeContract.View) : HomeContract.Presenter {

    init {
        homeView.mPresenter = this
    }

    override fun isUserLoggedIn() {
        val userPrefs = UserPreferencesHelper(context)
        if (userPrefs.getPhone() == null || userPrefs.getToken() == null) {
            homeView.isNotLoggedIn()
        } else {
            val user = User.getInstance()
            val userPrefs = UserPreferencesHelper(context)
            user.phone = userPrefs.getPhone()
            user.token = userPrefs.getToken()
            homeView.setPhoneNumber(user.phone)
        }
    }

    override fun logout(activity: Activity) {
        UserPreferencesHelper(activity).clearAll()
        val launchActivity = Intent(activity, PhoneActivity::class.java)
        activity.startActivity(launchActivity)
        activity.finish()
    }
}