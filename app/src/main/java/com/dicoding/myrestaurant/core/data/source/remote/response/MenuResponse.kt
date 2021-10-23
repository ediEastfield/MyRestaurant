package com.dicoding.myrestaurant.core.data.source.remote.response

import com.google.gson.annotations.SerializedName

data class MenuResponse(

    @field:SerializedName("foods")
    val foods: List<FoodDrinkResponse>,

    @field:SerializedName("drinks")
    val drinks: List<FoodDrinkResponse>
)
