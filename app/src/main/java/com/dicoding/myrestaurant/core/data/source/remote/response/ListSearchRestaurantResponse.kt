package com.dicoding.myrestaurant.core.data.source.remote.response

import com.google.gson.annotations.SerializedName

data class ListSearchRestaurantResponse(

    @field:SerializedName("error")
    val error: Boolean,

    @field:SerializedName("founded")
    val founded: Int,

    @field:SerializedName("restaurants")
    val restaurants: List<RestaurantResponse>
)
