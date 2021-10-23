package com.dicoding.myrestaurant.core.data.source.local.entity

import androidx.annotation.NonNull
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "restaurant")
data class RestaurantEntity(

    @PrimaryKey
    @NonNull
    @ColumnInfo(name = "restaurantId")
    var restaurantId: String,

    @ColumnInfo(name = "name")
    var name: String,

    @ColumnInfo(name = "pictureId")
    var pictureId: String,

    @ColumnInfo(name = "city")
    var city: String,

    @ColumnInfo(name = "rating")
    var rating: Double
)