package com.example.domain.entity

data class Menu(
    val menuId : Long,
    val title : String,
    val image : String,
    val categoryId : Long,
    val portionId : Long,
    val portionSize : Int,
    val price : Double
)
