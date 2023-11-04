package com.example.testapp.ui

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.testapp.domain.MainUsecase
import com.example.testapp.utils.FoodItem
import kotlinx.coroutines.launch

class MainViewModel(private val usecase: MainUsecase) : ViewModel() {
    private val _foodLiveData = MutableLiveData<List<FoodItem>>()
    val foodLiveData: LiveData<List<FoodItem>>
        get() = _foodLiveData


    fun getFood(query: String) {
        viewModelScope.launch {
            usecase.getFoodFromRepo(query).collect {
                Log.d("TAGUS",it.toString())
                _foodLiveData.postValue(it)
            }
        }
    }
}