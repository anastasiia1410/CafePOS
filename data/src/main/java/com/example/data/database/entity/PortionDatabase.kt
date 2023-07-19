package com.example.data.database.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "portion")
data class PortionDatabase(
    @PrimaryKey(autoGenerate = false)
    val id : Long,
    val portionType : PortionTypeDatabase
)
