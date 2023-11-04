package com.example.testapp.domain

import com.example.testapp.data.database.FoodEntity
import com.example.testapp.data.retrofit.MealsInfo
import com.example.testapp.utils.FoodItem

interface Mapper {
    fun mapEntityToFoodItem(foodEntity: FoodEntity):FoodItem
    fun mapRemoteToLocal(foodList:List<MealsInfo>):List<FoodItem>
}