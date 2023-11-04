package com.example.testapp.data.retrofit

import com.google.gson.annotations.SerializedName

data class CategoryDTO(
    @SerializedName("categories") val categoryList: List<CatInfo>
)

data class CatInfo(
    @SerializedName("strCategory") val category: String
)
