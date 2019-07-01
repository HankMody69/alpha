package com.example.alpha.ui.currencylist

import android.content.Context
import com.example.alpha.data.network.model.Currency
import com.example.alpha.ui.base.BasePresenter
import com.example.alpha.ui.base.BaseView

interface CurrencyListContract {

    interface View : BaseView<Presenter> {
        fun updateList(currencies: List<Currency>)
        fun showProgress()
        fun dismissProgress()
        fun setMessage(message: String)
    }

    interface Presenter : BasePresenter {
        fun getCurrencies()
        fun saveCurrency(applicationContext: Context, position: Int)
        fun getSavedCurrencies(applicationContext: Context)
        fun saveAllCurrencies(applicationContext: Context)
    }

}