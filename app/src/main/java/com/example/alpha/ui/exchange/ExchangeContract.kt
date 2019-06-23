package com.example.alpha.ui.exchange

import com.example.alpha.ui.base.BasePresenter
import com.example.alpha.ui.base.BaseView

interface ExchangeContract {

    interface View : BaseView<Presenter> {

        fun showProgress()
        fun dismissProgress()
        fun updateList(data: List<String>)
        fun updateRate(number: Double)

    }

    interface Presenter : BasePresenter {

        fun getCurrencyList()
        fun getCurrencyRateList()
        fun calculateRate(number: Double, position: Int)
        fun calculateToBase(number: Double, position: Int)

    }

}