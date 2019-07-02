package com.example.alpha.ui.wallet.list

import android.content.Context
import android.provider.BaseColumns
import com.example.alpha.data.database.sqlite.DatabaseContract

class WalletListPresenter(private val view: WalletListContract.View) : WalletListContract.Presenter {

    init {
        view.mPresenter = this
    }

    override fun getWallets(context: Context) {
        val dbHelper = DatabaseContract.AppDatabaseHelper(context)
        val db = dbHelper.readableDatabase

        val projection = arrayOf(
            BaseColumns._ID,
            DatabaseContract.Currency.COLUMN_NAME_NAME,
            DatabaseContract.Currency.COLUMN_NAME_CURRENCY
        )

        val cursor = db.query(
            DatabaseContract.Currency.TABLE_NAME,
            projection,
            null,
            null,
            null,
            null,
            null
        )

        with(cursor) {
            while (moveToNext()) {
                val id = getLong(getColumnIndexOrThrow(BaseColumns._ID))
                val name = getString(getColumnIndexOrThrow(DatabaseContract.Currency.COLUMN_NAME_NAME))
                val currency = getString(getColumnIndexOrThrow(DatabaseContract.Currency.COLUMN_NAME_CURRENCY))
                view.addWallet("$id) - $name - $currency")
            }
        }
    }
}