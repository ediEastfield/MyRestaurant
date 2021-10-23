package com.dicoding.myrestaurant.core.domain.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class DetailRestaurant(

    val id: String,
    val name: String,
    val description: String,
    val city: String,
    val address: String,
    val pictureId: String,
    val rating: Double,
    val isFavorite: Boolean
): Parcelable