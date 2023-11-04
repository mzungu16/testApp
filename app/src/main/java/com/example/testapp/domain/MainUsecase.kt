package com.example.testapp.domain

import com.example.testapp.utils.FoodItem
import kotlinx.coroutines.flow.Flow

interface MainUsecase {
    suspend fun getFoodFromRepo(query: String): Flow<List<FoodItem>>
}