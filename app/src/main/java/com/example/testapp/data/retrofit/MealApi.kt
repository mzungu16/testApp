package com.example.testapp.data.retrofit

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface MealApi {
    @GET("/api/json/v1/1/search.php?f={query}")
    suspend fun getFoodMenu(
        @Query("query") query: String
    ): Call<FoodDTO>
}