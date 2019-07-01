package com.example.alpha.ui.currencylist

import android.content.Context
import android.os.AsyncTask
import android.util.Log
import com.example.alpha.data.database.AppDatabase
import com.example.alpha.data.network.ApiService
import com.example.alpha.data.network.model.Currency
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.uiThread
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class CurrencyListPresenter(private val view: CurrencyListContract.View) : CurrencyListContract.Presenter {

    private lateinit var currencies: List<Currency>

    init {
        view.mPresenter = this
    }

    override fun getCurrencies() {
        view.showProgress()
        ApiService.create().getCurrencies().enqueue(object : Callback<List<Currency>> {
            override fun onResponse(call: Call<List<Currency>>, response: Response<List<Currency>>) {
                if (response.code() == 200) {
                    view.setMessage("در حال به روز رسانی...")
                    val data = response.body()
                    if (data != null) {
                        currencies = data
                        view.updateList(data)
                    } else {
                        view.setMessage("لیست ارزی خالی می‌باشد")
                    }
                }

                view.dismissProgress()

            }

            override fun onFailure(call: Call<List<Currency>>, t: Throwable) {
                view.setMessage(t.message.toString())
                view.dismissProgress()
            }

        })
    }

    override fun saveCurrency(applicationContext: Context, position: Int) {
        val currencyNetworkModel = currencies[position]
        val model = com.example.alpha.data.database.Currency(
            null,
            currencyNetworkModel.name,
            currencyNetworkModel.code,
            currencyNetworkModel.symbol
        )
        doAsync {
            val result = AppDatabase.getInstance(applicationContext).currencyDao().insertAll(model)
            uiThread {
                Log.e("HEY", result[0].toString())
                if (result.isNotEmpty() && result[0] > 0) {
                    view.setMessage("با موفقیت ذخیره شد.")
                }
            }
        }
    }

    override fun getSavedCurrencies(applicationContext: Context) {
        doAsync {
            val data = AppDatabase.getInstance(applicationContext).currencyDao().getAll()
            Log.e("INFO", "currencies: $data")
        }
    }

    override fun saveAllCurrencies(applicationContext: Context) {
        val models = mutableListOf<com.example.alpha.data.database.Currency>()
        for (currency in currencies) {
            val model = com.example.alpha.data.database.Currency(
                null,
                currency.name,
                currency.code,
                currency.symbol
            )
            models.add(model)

        }
        doAsync {
            val result = AppDatabase.getInstance(applicationContext).currencyDao().insertAll(*models.toTypedArray())
            uiThread {
                if (result.isNotEmpty()) {
                    view.setMessage("تمامی موارد با موفقیت ذخیره شدند.")
                }
            }
        }
    }
}