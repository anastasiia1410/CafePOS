package com.example.data.database.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "category")
data class CategoryDatabase(
        @PrimaryKey(autoGenerate = true)
        val id: Long = 0L,
        val categoryName: String,
        val icon: String
)