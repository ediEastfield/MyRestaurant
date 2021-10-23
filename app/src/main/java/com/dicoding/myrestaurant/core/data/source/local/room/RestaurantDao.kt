package com.dicoding.myrestaurant.core.data.source.local.room

import androidx.room.*
import com.dicoding.myrestaurant.core.data.source.local.entity.DetailRestaurantEntity
import com.dicoding.myrestaurant.core.data.source.local.entity.RestaurantEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface RestaurantDao {

    @Query("SELECT * FROM restaurant")
    fun getAllRestaurant(): Flow<List<RestaurantEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertRestaurant(restaurant: List<RestaurantEntity>)

    @Query("SELECT * FROM detail_restaurant WHERE isFavorite = 1")
    fun getFavoriteRestaurant(): Flow<List<DetailRestaurantEntity>>

    @Query("SELECT * FROM detail_restaurant WHERE restaurantId =:id")
    fun getDetailRestaurant(id: String): Flow<DetailRestaurantEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertDetailRestaurant(restaurant: DetailRestaurantEntity)

    @Update
    fun updateFavoriteRestaurant(restaurant: DetailRestaurantEntity)
}