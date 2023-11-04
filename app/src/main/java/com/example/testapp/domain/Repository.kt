package com.example.testapp.domain

import androidx.lifecycle.LiveData
import com.example.testapp.data.retrofit.CatInfo
import com.example.testapp.utils.DataState
import com.example.testapp.utils.FoodItem
import kotlinx.coroutines.flow.Flow


interface Repository {
    suspend fun getFoodFromRemote(): Flow<DataState<List<FoodItem>>>
    suspend fun getCategoryFromRemote(): Flow<DataState<List<CatInfo>>>
}