package com.example.data.database.entity

import androidx.room.Embedded
import androidx.room.Relation

data class MenuWithCategory(
    @Embedded
    val category: CategoryDatabase,
    @Relation(
        entity = MenuDatabase::class,
        parentColumn = "id",
        entityColumn = "categoryId"
    )
    val menuList: List<MenuWithPortion>,

    )

data class MenuWithPortion(
    @Embedded
    val menu: MenuDatabase,
    @Relation(
        entity = PortionDatabase::class,
        parentColumn = "portionId",
        entityColumn = "id"
    )
    val portionDatabase: PortionDatabase,
)