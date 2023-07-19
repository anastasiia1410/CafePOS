package com.example.domain.entity

data class Category(
    val id: Long,
    val categoryName: String,
    val icon: CategoryIcon,
    val itemsMenu : List<Menu>
)