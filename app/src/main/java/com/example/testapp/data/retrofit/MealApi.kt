package com.example.testapp.data.retrofit

import retrofit2.Call
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query
import java.util.Locale.Category

interface MealApi {
    @GET("api/json/v1/1/search.php?f=p")
     suspend fun getFoodMenu(): Response<FoodDTO>

     @GET("api/json/v1/1/categories.php")
     suspend fun getCategories(): Response<CategoryDTO>
}