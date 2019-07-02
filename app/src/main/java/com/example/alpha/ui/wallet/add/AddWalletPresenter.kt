package com.example.alpha.ui.wallet.add

import android.app.Activity
import android.content.ContentValues
import android.content.Context
import android.util.Log
import com.example.alpha.data.database.sqlite.DatabaseContract
import com.example.alpha.data.network.ApiService
import com.example.alpha.data.network.model.Currency
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class AddWalletPresenter(private val view: AddWalletContract.View) : AddWalletContract.Presenter {

    private lateinit var currencies: List<Currency>

    init {
        view.mPresenter = this
    }

    override fun getCurrencies() {
        ApiService.create().getCurrencies().enqueue(object : Callback<List<Currency>> {

            override fun onResponse(call: Call<List<Currency>>, response: Response<List<Currency>>) {
                if (response.code() == 200) {
                    val data = response.body()
                    if (data != null && data.isNotEmpty()) {
                        currencies = data
                        view.showMessage("در حال به‌روزرسانی لیست ارزها")
                        view.initList(data.map { it.name })
                    }
                }
            }

            override fun onFailure(call: Call<List<Currency>>, t: Throwable) {
                view.showMessage("خطا در ردیافت اطلاعات از سرور")
            }

        })
    }

    override fun addWallet(context: Context, name: String, currency: String) {
        val dbHelper = DatabaseContract.AppDatabaseHelper(context)
        val db = dbHelper.writableDatabase
        // Create a new map of values, where column names are the keys
        val values = ContentValues().apply {
            put(DatabaseContract.Currency.COLUMN_NAME_NAME, name)
            put(DatabaseContract.Currency.COLUMN_NAME_CURRENCY, currency)
        }

        val newRowId = db?.insert(DatabaseContract.Currency.TABLE_NAME, null, values)
        if (newRowId != null && newRowId > 0) {
            view.showMessage("کیف پول ذخیره شد.")
            val value = values[DatabaseContract.Currency.COLUMN_NAME_NAME].toString() +
                    " - " + values[DatabaseContract.Currency.COLUMN_NAME_CURRENCY].toString()
            view.finishWithResult(Activity.RESULT_OK, value)
            Log.i("INFO", "new row id is: $newRowId")
        }
    }
}