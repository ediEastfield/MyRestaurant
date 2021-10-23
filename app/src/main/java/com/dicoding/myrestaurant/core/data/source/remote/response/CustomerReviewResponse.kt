package com.dicoding.myrestaurant.core.data.source.remote.response

import com.google.gson.annotations.SerializedName

data class CustomerReviewResponse(

    @field:SerializedName("name")
    val name: String,

    @field:SerializedName("date")
    val date: String,

    @field:SerializedName("review")
    val review: String
)
