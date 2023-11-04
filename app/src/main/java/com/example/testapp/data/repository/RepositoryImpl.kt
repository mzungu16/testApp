package com.example.testapp.data.repository

import com.example.testapp.data.retrofit.CatInfo
import com.example.testapp.domain.RemoteDataSource
import com.example.testapp.domain.Repository
import com.example.testapp.utils.DataState
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class RepositoryImpl(
    private val remoteDataSource: RemoteDataSource,
):Repository {
    override suspend fun getFoodFromRemote() = flow {
        emit(remoteDataSource.getNewMeals())
    }

    override suspend fun getCategoryFromRemote() = flow {
        emit(remoteDataSource.getCategories())
    }
}