package com.dicoding.myrestaurant.detail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.dicoding.myrestaurant.core.domain.model.DetailRestaurant
import com.dicoding.myrestaurant.core.domain.usecase.RestaurantUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class DetailRestaurantViewModel @Inject constructor(private val restaurantUseCase: RestaurantUseCase) : ViewModel() {

    fun getDetailRestaurant(id: String) = restaurantUseCase.getDetailRestaurant(id).asLiveData()
    fun getMenuRestaurant(id: String) = restaurantUseCase.getMenuRestaurant(id).asLiveData()
    fun getReviewRestaurant(id: String) = restaurantUseCase.getReviewRestaurant(id).asLiveData()
    fun setFavoriteRestaurant(restaurant: DetailRestaurant, newStatus: Boolean) =
        restaurantUseCase.setFavoriteRestaurant(restaurant, newStatus)
}