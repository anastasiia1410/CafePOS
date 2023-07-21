package com.example.domain.repository

import com.example.domain.entity.Category
import com.example.domain.entity.Menu
import com.example.domain.entity.Portion


interface MenuRepository {

    suspend fun insert()

    suspend fun insertCategory(category: Category)

    suspend fun insertPortion(portion: Portion)

    suspend fun getAllMenu(): List<Category>

    suspend fun getCategoryById(id: Long): Category

    suspend fun getPortionById(id: Long): Portion

    suspend fun getPortionView(): List<Portion>

    suspend fun insertMenu(menu: Menu)
}