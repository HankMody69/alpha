package com.example.alpha.ui.login.phone

import android.content.Intent
import android.os.Bundle
import com.example.alpha.ui.base.BasePresenter
import com.example.alpha.ui.base.BaseView

interface PhoneContract {

    interface View : BaseView<Presenter> {

        fun setMessage(message: String)
        fun disableSend()
        fun enableSend()
        fun openNextActivity(bundle: Bundle)

    }

    interface Presenter : BasePresenter {

        fun login(phone: String)

    }

}