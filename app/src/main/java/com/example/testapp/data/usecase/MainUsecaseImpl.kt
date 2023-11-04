package com.example.testapp.data.usecase

import com.example.testapp.domain.MainUsecase
import com.example.testapp.domain.Repository
import com.example.testapp.utils.FoodItem
import kotlinx.coroutines.flow.Flow

class MainUsecaseImpl(val repository: Repository) : MainUsecase {
    override suspend fun getFoodFromRepo(query: String): Flow<List<FoodItem>> {
        return repository.getFoodFromRemote(query)
    }
}