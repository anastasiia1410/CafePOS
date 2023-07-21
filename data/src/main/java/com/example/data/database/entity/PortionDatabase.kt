package com.example.data.database.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "portion")
data class PortionDatabase(
    @PrimaryKey(autoGenerate = true)
    val id : Long = 0L,
    val portionType : String,
    val portionUnit : String
)
