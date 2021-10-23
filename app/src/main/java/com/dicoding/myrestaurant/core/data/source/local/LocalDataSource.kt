package com.dicoding.myrestaurant.core.data.source.local

import com.dicoding.myrestaurant.core.data.source.local.entity.DetailRestaurantEntity
import com.dicoding.myrestaurant.core.data.source.local.entity.RestaurantEntity
import com.dicoding.myrestaurant.core.data.source.local.room.RestaurantDao
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class LocalDataSource @Inject constructor(private val restaurantDao: RestaurantDao) {

    fun getAllRestaurant(): Flow<List<RestaurantEntity>> = restaurantDao.getAllRestaurant()
    suspend fun insertRestaurant(restaurantList: List<RestaurantEntity>) = restaurantDao.insertRestaurant(restaurantList)
    fun getAllFavoriteRestaurant(): Flow<List<DetailRestaurantEntity>> = restaurantDao.getFavoriteRestaurant()
    fun getDetailRestaurant(id: String): Flow<DetailRestaurantEntity> = restaurantDao.getDetailRestaurant(id)
    suspend fun insertDetailRestaurant(restaurant: DetailRestaurantEntity) = restaurantDao.insertDetailRestaurant(restaurant)
    fun setFavoriteRestaurant(restaurant: DetailRestaurantEntity, newState: Boolean) {
        restaurant.isFavorite = newState
        restaurantDao.updateFavoriteRestaurant(restaurant)
    }
}