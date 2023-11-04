package com.example.testapp.domain

import androidx.lifecycle.LiveData
import com.example.testapp.utils.DataState
import com.example.testapp.utils.FoodItem
import kotlinx.coroutines.flow.Flow

interface MainUsecase {
    suspend fun getFoodFromRepo(): Flow<DataState<*>>
}