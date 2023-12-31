package com.example.data.database

import com.example.data.database.entity.CategoryDatabase
import com.example.data.database.entity.MenuDatabase
import com.example.data.database.entity.MenuWithCategory
import com.example.data.database.entity.PortionDatabase

interface DatabaseManager {

    suspend fun insertCategory(category: CategoryDatabase)
    suspend fun getCategoryById(id: Long): CategoryDatabase
    suspend fun categoryCount(): Int

    suspend fun insertPortion(portion: PortionDatabase)
    suspend fun getAllPortions(): List<PortionDatabase>
    suspend fun getPortionById(id: Long): PortionDatabase

    suspend fun insertMenu(menu: MenuDatabase)

    suspend fun getMenuWithCategoryAndPortion(): List<MenuWithCategory>

}