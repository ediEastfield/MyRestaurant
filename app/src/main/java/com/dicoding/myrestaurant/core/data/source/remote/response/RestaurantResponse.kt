package com.dicoding.myrestaurant.core.data.source.remote.response

import com.google.gson.annotations.SerializedName

data class RestaurantResponse(
    @field:SerializedName("id")
    val id: String,

    @field:SerializedName("name")
    val name: String,

    @field:SerializedName("pictureId")
    val pictureId: String,

    @field:SerializedName("city")
    val city: String,

    @field:SerializedName("rating")
    val rating: Double
)
