package com.example.testapp.data.database

import androidx.room.Dao
import androidx.room.Query

@Dao
interface FoodDao {
    @Query("SELECT * FROM FoodEntity")
    suspend fun getAllFood(): List<FoodEntity>
}