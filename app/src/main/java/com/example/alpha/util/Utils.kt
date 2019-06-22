package com.example.alpha.util

import android.content.Context
import android.provider.Settings.Secure;
import android.app.Activity
import android.view.View
import android.view.inputmethod.InputMethodManager


object Utils {

    fun udid(context: Context) = Secure.getString(context.contentResolver, Secure.ANDROID_ID)

    fun hideKeyboard(activity: Activity) {
        val imm = activity.getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager
        var view = activity.currentFocus
        if (view == null) {
            view = View(activity)
        }
        imm.hideSoftInputFromWindow(view.windowToken, 0)
    }

}