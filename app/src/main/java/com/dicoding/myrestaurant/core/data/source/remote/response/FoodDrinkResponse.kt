package com.dicoding.myrestaurant.core.data.source.remote.response

import com.google.gson.annotations.SerializedName

data class FoodDrinkResponse(

    @field:SerializedName("name")
    val name: String
)
