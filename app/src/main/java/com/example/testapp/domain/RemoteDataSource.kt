package com.example.testapp.domain

import com.example.testapp.utils.FoodItem

interface RemoteDataSource {
    suspend fun getNewMeals(query:String): List<FoodItem>
}