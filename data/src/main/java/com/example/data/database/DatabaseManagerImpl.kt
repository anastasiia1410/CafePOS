package com.example.data.database

import android.content.Context
import androidx.room.Room
import com.example.data.database.entity.CategoryDatabase
import com.example.data.database.entity.MenuDatabase
import com.example.data.database.entity.MenuWithCategory
import com.example.data.database.entity.PortionDatabase

class DatabaseManagerImpl(context: Context) : DatabaseManager {

    private val db = Room.databaseBuilder(
        context,
        AppDatabase::class.java,
        "cafePos.sqlite"
    ).build()

    override suspend fun insertCategory(category: CategoryDatabase) {
        db.categoryDao().insertCategory(category)
    }

    override suspend fun getCategoryById(id: Long): CategoryDatabase {
        return db.categoryDao().getCategoryById(id)
    }

    override suspend fun categoryCount(): Int {
        return db.categoryDao().categoryCount()
    }

    override suspend fun insertPortion(portion: PortionDatabase) {
        db.portionDao().insertPortion(portion)
    }

    override suspend fun getAllPortions(): List<PortionDatabase> {
        return db.portionDao().getAllPortions()
    }

    override suspend fun getPortionById(id: Long): PortionDatabase {
        return db.portionDao().getPortionById(id)
    }

    override suspend fun insertMenu(menu: MenuDatabase) {
        db.menuDao().insertMenu(menu)
    }

    override suspend fun getMenuWithCategoryAndPortion(): List<MenuWithCategory> {
        return db.categoryDao().getMenuCategoriesWithMenuItems()
    }
}