package com.example.testapp.ui

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.testapp.data.retrofit.CatInfo
import com.example.testapp.domain.MainUsecase
import com.example.testapp.utils.DataState
import com.example.testapp.utils.FoodItem
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class MainViewModel(private val usecase: MainUsecase) : ViewModel() {
    private val _foodLiveData = MutableLiveData<DataState<List<FoodItem>>>()
    val foodLiveData: LiveData<DataState<List<FoodItem>>>
        get() = _foodLiveData

    private val _categoryLiveData = MutableLiveData<DataState<List<CatInfo>>>()

    val categoryInfo:LiveData<DataState<List<CatInfo>>>
        get() = _categoryLiveData

    fun getFood() {
        viewModelScope.launch {
            usecase.getFoodFromRepo().collect {
                _foodLiveData.postValue(it as DataState<List<FoodItem>>)
            }
        }
    }

    fun getCategory(){
        viewModelScope.launch{
            usecase.getCategoryFromRepo().collect{
                _categoryLiveData.postValue(it as DataState<List<CatInfo>>)
            }
        }
    }
}