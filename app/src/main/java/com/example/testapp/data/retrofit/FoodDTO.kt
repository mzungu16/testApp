package com.example.testapp.data.retrofit

import com.google.gson.annotations.SerializedName

data class FoodDTO(
    @SerializedName("meals") val meals: List<MealsInfo>
)

data class MealsInfo(
    @SerializedName("idMeal") val price: String,
    @SerializedName("strMeal") val mealName: String,
    @SerializedName("strInstructions") val mealDes: String,
    @SerializedName("strMealThumb") val mealImg: String
)