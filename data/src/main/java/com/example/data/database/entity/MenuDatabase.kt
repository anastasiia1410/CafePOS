package com.example.data.database.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "menu")
data class MenuDatabase(
    @PrimaryKey(autoGenerate = true)
    val menuId : Long,
    val title : String,
    val image : String,
    val categoryId : Long,
    val portionId : Long,
    val portionSize : Int,
    val price : Double
)
