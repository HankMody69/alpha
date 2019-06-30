package com.example.alpha.data.database

import androidx.room.*

@Dao
interface CurrencyDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(vararg currency: Currency): LongArray

    @Query("SELECT * FROM currency")
    fun getAll(): List<Currency>
}