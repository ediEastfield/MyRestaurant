package com.dicoding.myrestaurant.core.data.source.local.room

import androidx.room.Database
import androidx.room.RoomDatabase
import com.dicoding.myrestaurant.core.data.source.local.entity.DetailRestaurantEntity
import com.dicoding.myrestaurant.core.data.source.local.entity.RestaurantEntity

@Database(
    entities = [
        RestaurantEntity::class,
        DetailRestaurantEntity::class,
    ],
    version = 1,
    exportSchema = false
)
abstract class RestaurantDatabase : RoomDatabase() {

    abstract fun restaurantDao(): RestaurantDao
}