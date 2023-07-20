package com.example.data.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Transaction
import com.example.data.database.entity.CategoryDatabase
import com.example.data.database.entity.MenuWithCategory

@Dao
interface CategoryDao {

    @Insert
    suspend fun insertCategory(category: CategoryDatabase)

    @Query("SELECT * FROM category")
    suspend fun getAllCategories(): List<CategoryDatabase>

    @Query("SELECT * FROM category WHERE id = :id")
    suspend fun getCategoryById(id: Long): CategoryDatabase

    @Query("SELECT COUNT(*) FROM menu")
    suspend fun categoryCount(): Int

    @Query("SELECT * FROM category")
    @Transaction
    suspend fun getMenuCategoriesWithMenuItems(): List<MenuWithCategory>
}