package com.example.testapp.domain

import com.example.testapp.utils.FoodItem
import kotlinx.coroutines.flow.Flow


interface Repository {
    suspend fun getFoodFromRemote(query: String): Flow<List<FoodItem>>
}