package com.dicoding.myrestaurant.core.data.source.remote.response

import com.google.gson.annotations.SerializedName

data class DetailRestaurantResponse(

    @field:SerializedName("id")
    val id: String,

    @field:SerializedName("name")
    val name: String,

    @field:SerializedName("description")
    val description: String,

    @field:SerializedName("city")
    val city: String,

    @field:SerializedName("address")
    val address: String,

    @field:SerializedName("pictureId")
    val pictureId: String,

    @field:SerializedName("rating")
    val rating: Double,

    @field:SerializedName("menus")
    val menus: MenuResponse,

    @field:SerializedName("customerReviews")
    val customerReviews: List<CustomerReviewResponse>
)
