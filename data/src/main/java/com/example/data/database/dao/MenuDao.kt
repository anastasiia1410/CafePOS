package com.example.data.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.data.database.entity.MenuDatabase

@Dao
interface MenuDao {
    @Insert
    suspend fun insertMenu(menu: MenuDatabase)

    @Query("SELECT * FROM menu")
    suspend fun getAllMenu(): List<MenuDatabase>

    @Query("SELECT * FROM menu WHERE categoryId = :id")
    suspend fun getMenuPositionsByCategory(id: Long): List<MenuDatabase>
}