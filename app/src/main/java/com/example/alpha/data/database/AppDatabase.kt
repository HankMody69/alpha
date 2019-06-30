package com.example.alpha.data.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [Currency::class], version = 1)
abstract class AppDatabase() : RoomDatabase() {
    abstract fun currencyDao(): CurrencyDao

    companion object {

        private var instance: AppDatabase? = null

        fun getInstance(applicationContext: Context): AppDatabase =
            instance ?: Room.databaseBuilder(
                applicationContext,
                AppDatabase::class.java,
                "app-database")
                .build()


    }

}