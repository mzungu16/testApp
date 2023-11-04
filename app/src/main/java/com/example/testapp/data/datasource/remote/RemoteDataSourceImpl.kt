package com.example.testapp.data.datasource.remote

import com.example.testapp.data.retrofit.FoodDTO
import com.example.testapp.data.retrofit.MealApi
import com.example.testapp.data.retrofit.MealsInfo
import com.example.testapp.domain.Mapper
import com.example.testapp.domain.RemoteDataSource
import com.example.testapp.utils.FoodItem
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class RemoteDataSourceImpl(
    private val api: MealApi,
    private val mapper: Mapper
): RemoteDataSource {
    override suspend fun getNewMeals(query:String): List<FoodItem>{
        var result:List<MealsInfo> = emptyList()
            api.getFoodMenu().enqueue(object: Callback<FoodDTO>{
                override fun onResponse(call: Call<FoodDTO>, response: Response<FoodDTO>) {
                    if(response.isSuccessful){
                        result = response.let {
                            it.body()?.meals ?: emptyList()
                        }
                    }
                }

                override fun onFailure(call: Call<FoodDTO>, t: Throwable) {
                    TODO("Not yet implemented")
                }

            })
        return mapper.mapRemoteToLocal(result)
    }
}

