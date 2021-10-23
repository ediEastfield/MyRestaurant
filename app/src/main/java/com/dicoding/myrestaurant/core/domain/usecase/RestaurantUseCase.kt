package com.dicoding.myrestaurant.core.domain.usecase

import com.dicoding.myrestaurant.core.data.Resource
import com.dicoding.myrestaurant.core.domain.model.CustomerReview
import com.dicoding.myrestaurant.core.domain.model.DetailRestaurant
import com.dicoding.myrestaurant.core.domain.model.Menu
import com.dicoding.myrestaurant.core.domain.model.Restaurant
import kotlinx.coroutines.flow.Flow

interface RestaurantUseCase {

    fun getAllRestaurant(): Flow<Resource<List<Restaurant>>>
    fun getDetailRestaurant(id: String): Flow<Resource<DetailRestaurant>>
    fun getMenuRestaurant(id: String): Flow<Menu>
    fun getReviewRestaurant(id: String): Flow<List<CustomerReview>>
    fun getAllFavoriteRestaurant(): Flow<List<Restaurant>>
    fun setFavoriteRestaurant(restaurant: DetailRestaurant, state: Boolean)
    fun getSearchRestaurant(q: String): Flow<List<Restaurant>>
    fun postReviewRestaurant(id: String, name: String, review: String): Flow<List<CustomerReview>>
}