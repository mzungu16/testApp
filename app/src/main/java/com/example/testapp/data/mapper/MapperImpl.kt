package com.example.testapp.data.mapper

import com.example.testapp.data.database.FoodEntity
import com.example.testapp.data.retrofit.MealsInfo
import com.example.testapp.domain.Mapper
import com.example.testapp.utils.FoodItem

class MapperImpl : Mapper  {
    override fun mapEntityToFoodItem(foodEntity: FoodEntity): FoodItem {
        TODO("Not yet implemented")
    }

    override fun mapRemoteToLocal(foodList: List<MealsInfo>): List<FoodItem> {
        return foodList.map {
            FoodItem(
                foodName = it.mealName,
                foodImg = it.mealImg,
                foodDes = it.mealDes,
                foodPrice = it.price
            )
        }
    }
}