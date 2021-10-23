package com.dicoding.myrestaurant.core.domain.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class CustomerReview(

    val name: String,
    val date: String,
    val review: String
): Parcelable