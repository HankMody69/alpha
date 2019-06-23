package com.example.alpha.util

import android.content.Context
import android.provider.Settings
import android.app.Activity
import android.net.ConnectivityManager
import android.net.NetworkInfo
import android.view.View
import android.view.inputmethod.InputMethodManager
import java.security.MessageDigest

object Utils {

    /*fun getSha1FromUdid(context: Context): String? {
        val udid = Secure.getString(context.contentResolver, Secure.ANDROID_ID)
        val messageDigest = MessageDigest.getInstance("SHA-1")
        var udidBytes = udid.toByteArray(Charsets.UTF_8)
        messageDigest.update(udidBytes, 0, udidBytes.size)
        udidBytes = messageDigest.digest()
        var result: String? = null
        for (b in udidBytes) {
            val st = String.format("%02X", b)
            print(st)
            result += st
        }
        return result
    }*/

    fun getPhoneUdid(context: Context): String {
        val phoneId = Settings.System.getString(context.contentResolver, Settings.Secure.ANDROID_ID)
        val udid = hashString("SHA-1", phoneId)
        return udid
    }

    private fun hashString(type: String, input: String): String {
        val HEX_CHARS = "0123456789ABCDEF"
        val bytes = MessageDigest.getInstance(type).digest(input.toByteArray())
        val result = StringBuilder(bytes.size * 2)

        bytes.forEach {
            val i = it.toInt()
            result.append(HEX_CHARS[i shr 4 and 0x0f])
            result.append(HEX_CHARS[i and 0x0f])
        }

        return result.toString()
    }

    fun hideKeyboard(activity: Activity) {
        val imm = activity.getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager
        var view = activity.currentFocus
        if (view == null) {
            view = View(activity)
        }
        imm.hideSoftInputFromWindow(view.windowToken, 0)
    }

    fun isConnectedToInternet(context: Context): Boolean {
        val cm = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val activeNetwork: NetworkInfo? = cm.activeNetworkInfo
        return activeNetwork?.isConnectedOrConnecting == true
    }

}