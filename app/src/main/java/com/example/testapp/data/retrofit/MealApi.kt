package com.example.testapp.data.retrofit

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface MealApi {
    @GET("api/json/v1/1/search.php?f=p")
     fun getFoodMenu(): Call<FoodDTO>
}