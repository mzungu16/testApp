package com.example.testapp.data.repository

import com.example.testapp.domain.RemoteDataSource
import com.example.testapp.domain.Repository
import kotlinx.coroutines.flow.flow

class RepositoryImpl(
    private val remoteDataSource: RemoteDataSource,
):Repository {
    override suspend fun getFoodFromRemote(query: String) = flow {
        emit(remoteDataSource.getNewMeals(query))
    }
}