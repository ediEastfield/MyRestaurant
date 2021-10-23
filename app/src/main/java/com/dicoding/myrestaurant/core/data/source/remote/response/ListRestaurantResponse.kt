package com.dicoding.myrestaurant.core.data.source.remote.response

import com.google.gson.annotations.SerializedName

data class ListRestaurantResponse(

    @field:SerializedName("error")
    val error: Boolean,

    @field:SerializedName("message")
    val message: String,

    @field:SerializedName("count")
    val count: Int,

    @field:SerializedName("restaurants")
    val restaurants: List<RestaurantResponse>
)
