package com.example.alpha.data.preferences

import android.content.Context

class UserPreferencesHelper(private val context: Context) {

    private val sharedPreferences = context.getSharedPreferences("prefs_user", Context.MODE_PRIVATE)

    public fun saveId(id: String?) {
        sharedPreferences.edit().putString(KEY_USER_ID, id).apply()
    }

    public fun savePhone(phone: String?) {
        sharedPreferences.edit().putString(KEY_USER_PHONE, phone).apply()
    }

    public fun saveUdid(udid: String?) {
        sharedPreferences.edit().putString(KEY_USER_UDID, udid).apply()
    }

    public fun saveToken(token: String?) {
        sharedPreferences.edit().putString(KEY_USER_TOKEN, token).apply()
    }

    public fun getId() = sharedPreferences.getString(KEY_USER_ID, null)
    public fun getPhone() = sharedPreferences.getString(KEY_USER_PHONE, null)
    public fun getUdid() = sharedPreferences.getString(KEY_USER_UDID, null)
    public fun getToken() = sharedPreferences.getString(KEY_USER_TOKEN, null)

    fun clearAll() {
        saveUdid(null)
        saveId(null)
        savePhone(null)
        saveToken(null)
    }

    companion object {

        private const val KEY_USER_PHONE = "user_phone"
        private const val KEY_USER_UDID = "user_udid"
        private const val KEY_USER_ID = "user_id"
        private const val KEY_USER_TOKEN = "user_token"

    }

}