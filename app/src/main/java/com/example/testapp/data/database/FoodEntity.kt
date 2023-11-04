package com.example.testapp.data.database

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class FoodEntity(
    @PrimaryKey(autoGenerate = true) val uId: Int = 0,
    @ColumnInfo(name = "foodName") val foodName: String,
    @ColumnInfo(name = "foodImg") val foodImg: String,
    @ColumnInfo(name = "foodDes") val foodDes: String,
    @ColumnInfo(name = "foodPrice") val foodPrice: String,
)