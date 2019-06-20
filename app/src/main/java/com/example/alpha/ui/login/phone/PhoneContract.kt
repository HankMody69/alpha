package com.example.alpha.ui.login.phone

import com.example.alpha.ui.base.BasePresenter
import com.example.alpha.ui.base.BaseView

interface PhoneContract {

    interface View : BaseView<Presenter> {

        fun setMessage(message: String)

    }

    interface Presenter : BasePresenter {

        fun login(phone: String, udid: String)

    }

}