package com.dicoding.myrestaurant.core.domain.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Menu(

    val foods: List<FoodDrink>,
    val drinks: List<FoodDrink>
): Parcelable
