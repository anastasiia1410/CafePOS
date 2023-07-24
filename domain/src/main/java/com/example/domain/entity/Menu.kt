package com.example.domain.entity

data class Menu(
    val menuId : Long = 0L,
    val title : String,
    val image : String,
    val categoryId : Long,
    val portion : Portion,
    val portionSize : Int,
    val price : Double
)
