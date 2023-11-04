package com.example.testapp.data.datasource.remote

import com.example.testapp.data.retrofit.CatInfo
import com.example.testapp.data.retrofit.MealApi
import com.example.testapp.domain.Mapper
import com.example.testapp.domain.RemoteDataSource
import com.example.testapp.utils.DataState
import com.example.testapp.utils.FoodItem

class RemoteDataSourceImpl(
    private val api: MealApi,
    private val mapper: Mapper
) : RemoteDataSource, BaseRemote() {

    private suspend fun getFoodFromServer() = apiCall { api.getFoodMenu() }
    private suspend fun getCategoryFromServer() = apiCall { api.getCategories() }
    override suspend fun getNewMeals(): DataState<List<FoodItem>> {
        val data = getFoodFromServer()
        return if (data is DataState.Success) {
            DataState.Success(
                mapper.mapRemoteToLocal(
                    data.data?.meals ?: emptyList()
                )
            )
        } else {
            DataState.Error(data.message.toString())
        }
    }

    override suspend fun getCategories(): DataState<List<CatInfo>> {
        val data = getCategoryFromServer()
        return if (data is DataState.Success) {
            DataState.Success(
                data.data?.categoryList ?: emptyList()
            )
        } else {
            DataState.Error(data.message.toString())
        }
    }

}

