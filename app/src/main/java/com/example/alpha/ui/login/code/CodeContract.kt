package com.example.alpha.ui.login.code

import com.example.alpha.ui.base.BasePresenter
import com.example.alpha.ui.base.BaseView

interface CodeContract {

    interface View : BaseView<Presenter> {
        fun setMessage(message: String)
        fun enableSubmit()
        fun disableSubmit()
        fun done()
    }

    interface Presenter : BasePresenter {

        fun activateLogin(code: String, phone: String, udid: String)

    }

}