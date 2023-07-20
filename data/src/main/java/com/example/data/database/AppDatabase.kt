package com.example.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.data.database.dao.CategoryDao
import com.example.data.database.dao.MenuDao
import com.example.data.database.dao.PortionDao
import com.example.data.database.entity.CategoryDatabase
import com.example.data.database.entity.MenuDatabase
import com.example.data.database.entity.PortionDatabase

@Database(
    entities = [
        MenuDatabase::class,
        CategoryDatabase::class,
        PortionDatabase::class
    ],
    version = 1
)
internal abstract class AppDatabase : RoomDatabase() {
    abstract fun menuDao(): MenuDao
    abstract fun categoryDao(): CategoryDao
    abstract fun portionDao(): PortionDao
}