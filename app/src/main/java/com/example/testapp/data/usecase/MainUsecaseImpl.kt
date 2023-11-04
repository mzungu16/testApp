package com.example.testapp.data.usecase

import androidx.lifecycle.LiveData
import com.example.testapp.domain.MainUsecase
import com.example.testapp.domain.Repository
import com.example.testapp.utils.DataState
import com.example.testapp.utils.FoodItem
import kotlinx.coroutines.flow.Flow

class MainUsecaseImpl(val repository: Repository) : MainUsecase {
    override suspend fun getFoodFromRepo(): Flow<DataState<*>> {
        return repository.getFoodFromRemote()
    }
}