package com.example.alpha.ui.home

import android.app.Activity
import com.example.alpha.ui.base.BasePresenter
import com.example.alpha.ui.base.BaseView

interface HomeContract {

    interface View : BaseView<Presenter> {
        fun isNotLoggedIn()
        fun setPhoneNumber(phone: String)
    }

    interface Presenter : BasePresenter {
        fun isUserLoggedIn()
        fun logout(activity: Activity)
    }

}