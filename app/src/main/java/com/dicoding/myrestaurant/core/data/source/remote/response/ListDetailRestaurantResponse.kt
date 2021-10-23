package com.dicoding.myrestaurant.core.data.source.remote.response

import com.google.gson.annotations.SerializedName

data class ListDetailRestaurantResponse(

    @field:SerializedName("error")
    val error: Boolean,

    @field:SerializedName("message")
    val message: String,

    @field:SerializedName("restaurant")
    val restaurant: DetailRestaurantResponse
)
