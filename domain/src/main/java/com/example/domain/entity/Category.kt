package com.example.domain.entity


data class Category(
    val id: Long = 0L,
    val categoryName: String,
    val icon: String,
    val itemsMenu: List<Menu>,
)