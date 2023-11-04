package com.example.testapp.domain

import androidx.lifecycle.LiveData
import com.example.testapp.data.retrofit.CatInfo
import com.example.testapp.utils.DataState
import com.example.testapp.utils.FoodItem

interface RemoteDataSource {
    suspend fun getNewMeals():DataState<List<FoodItem>>
    suspend fun getCategories():DataState<List<CatInfo>>
}