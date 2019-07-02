package com.example.alpha.data.database.sqlite

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.provider.BaseColumns

object DatabaseContract {

    private const val SQL_CREATE_ENTRIES =
        "CREATE TABLE ${Currency.TABLE_NAME} (" +
                "${BaseColumns._ID} INTEGER PRIMARY KEY," +
                "${Currency.COLUMN_NAME_NAME} TEXT," +
                "${Currency.COLUMN_NAME_CURRENCY} TEXT)"

    private const val SQL_DELETE_ENTRIES = "DROP TABLE IF EXISTS ${Currency.TABLE_NAME}"

    object Currency : BaseColumns {
        const val TABLE_NAME = "currency"
        const val COLUMN_NAME_NAME = "name"
        const val COLUMN_NAME_CURRENCY = "currency"
    }

    class AppDatabaseHelper(context: Context) : SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION) {

        override fun onCreate(db: SQLiteDatabase) {
            db.execSQL(SQL_CREATE_ENTRIES)
        }
        override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
            db.execSQL(SQL_DELETE_ENTRIES)
            onCreate(db)
        }

        companion object {
            const val DATABASE_VERSION = 1
            const val DATABASE_NAME = "AppDatabase.db"
        }

    }

}