package com.example.testapp.ui

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.testapp.domain.MainUsecase
import com.example.testapp.utils.DataState
import com.example.testapp.utils.FoodItem
import kotlinx.coroutines.launch

class MainViewModel(private val usecase: MainUsecase) : ViewModel() {
    private val _foodLiveData = MutableLiveData<DataState<List<FoodItem>>>()
    val foodLiveData: LiveData<DataState<List<FoodItem>>>
        get() = _foodLiveData


    fun getFood() {
        viewModelScope.launch {
            usecase.getFoodFromRepo().collect {
                Log.d("TAGUS",(it as DataState<List<FoodItem>>).toString())
                _foodLiveData.postValue(it as DataState<List<FoodItem>>)
            }
        }
    }
}