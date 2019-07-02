package com.example.alpha.data.database.room

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey

@Entity(tableName = "currency", indices = [Index(value = ["code"], unique = true)])
data class Currency(
    @PrimaryKey(autoGenerate = true) val id: Int? = null,
    @ColumnInfo(name = "name") val name: String?,
    @ColumnInfo(name = "code") val code: String?,
    @ColumnInfo(name = "symbol") val symbol: String?
)