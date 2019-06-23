package com.example.alpha.ui.exchange

import android.util.Log
import com.example.alpha.data.network.ApiService
import com.example.alpha.data.network.model.Currency
import com.example.alpha.data.network.model.CurrencyRate
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ExchangePresenter(private val exchangeView: ExchangeContract.View) : ExchangeContract.Presenter {

    private val mCurrencies = mutableListOf<Currency>()
    private val mCurrencyRates = mutableListOf<CurrencyRate>()

    init {
        exchangeView.mPresenter = this
    }

    override fun getCurrencyList() {
        exchangeView.showProgress()
        ApiService.create().getCurrencies().enqueue(object : Callback<List<Currency>>{
            override fun onResponse(call: Call<List<Currency>>, response: Response<List<Currency>>) {
                if (response.code() == 200) {
                    mCurrencies.clear()
                    response.body()?.let { mCurrencies.addAll(it) }
                    if (mCurrencies.isNotEmpty()) {
                        val names = mCurrencies.map { it.name }
                        exchangeView.updateList(names)
                    }
                }
            }
            override fun onFailure(call: Call<List<Currency>>, t: Throwable) {

            }

        })
    }

    override fun getCurrencyRateList() {
        ApiService.create().getCurrenciesRate().enqueue(object : Callback<List<CurrencyRate>> {

            override fun onResponse(call: Call<List<CurrencyRate>>, response: Response<List<CurrencyRate>>) {
                if (response.code() == 200) {
                    response.body()?.let { mCurrencyRates.addAll(it) }
                }
            }

            override fun onFailure(call: Call<List<CurrencyRate>>, t: Throwable) {

            }

        })
    }

    override fun calculateRate(number: Double, position: Int) {
        if (mCurrencyRates.isNotEmpty()) {
            val incomingCode = mCurrencies[position].code
            var rate: Double? = null
            for (currencyRate in mCurrencyRates) {
                if (currencyRate.code == incomingCode) {
                    rate = currencyRate.buy.toDouble()
                }
            }
            rate?.let {
                val result = number * it
                exchangeView.updateRate(result)
            }
        }
    }

    override fun calculateToBase(number: Double, position: Int) {

        if (mCurrencyRates.isNotEmpty()) {
            val incomingCode = mCurrencies[position].code
            var rate: Double? = null
            for (currencyRate in mCurrencyRates) {
                if (currencyRate.code == incomingCode) {
                    rate = currencyRate.buy.toDouble()
                }
            }
            rate?.let {
                //Log.e("Rate")
                val result = number / it
                exchangeView.updateRate(result)
            }
        }
    }

}