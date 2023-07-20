package com.example.data.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.data.database.entity.PortionDatabase

@Dao
interface PortionDao {

    @Insert
    suspend fun insertPortion(portionDB: PortionDatabase)

    @Query("SELECT * FROM portion")
    suspend fun getAllPortions(): List<PortionDatabase>

    @Query("SELECT COUNT(*) FROM portion")
    suspend fun portionCount(): Int

    @Query("SELECT * FROM portion WHERE id = :id")
    suspend fun getPortionById(id: Long): PortionDatabase
}