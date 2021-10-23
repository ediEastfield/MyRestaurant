package com.dicoding.myrestaurant.core.domain.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class FoodDrink(

    val name: String
): Parcelable