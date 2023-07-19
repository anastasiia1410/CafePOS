package com.example.data.database.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "category")
data class CategoryDatabase(
        @PrimaryKey(autoGenerate = false)
        val id: Long,
        val categoryName: String,
        val icon: CategoryIconDatabase
)