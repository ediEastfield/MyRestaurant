package com.dicoding.myrestaurant.core.domain.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Restaurant(

    val restaurantId: String,
    val name: String,
    val pictureId: String,
    val city: String,
    val rating: Double
): Parcelable