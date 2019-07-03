package com.example.alpha.ui.wallet.add

import android.content.Context
import com.example.alpha.ui.base.BasePresenter
import com.example.alpha.ui.base.BaseView

interface AddWalletContract {

    interface View : BaseView<Presenter> {
        fun initList(data: List<String>)
        fun showMessage(message: String)
        fun finishWithResult(result: Int, data: String)
        fun dismissProgress()
        fun showProgress()
    }

    interface Presenter : BasePresenter {
        fun getCurrencies()
        fun addWallet(context: Context, name: String, currency: String)
    }

}