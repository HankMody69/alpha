package com.example.alpha.ui.wallet.list

import android.content.Context
import com.example.alpha.ui.base.BasePresenter
import com.example.alpha.ui.base.BaseView

interface WalletListContract {

    interface View : BaseView<Presenter> {
        fun addWallet(wallet: String)
        fun lisIsEmpty()
        fun listIsNotEmpty()
    }

    interface Presenter : BasePresenter {
        fun getWallets(context: Context)
    }

}