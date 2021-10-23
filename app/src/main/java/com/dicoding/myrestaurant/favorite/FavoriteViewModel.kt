package com.dicoding.myrestaurant.favorite

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.dicoding.myrestaurant.core.domain.usecase.RestaurantUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class FavoriteViewModel @Inject constructor(restaurantUseCase: RestaurantUseCase) : ViewModel() {
    val favoriteRestaurant = restaurantUseCase.getAllFavoriteRestaurant().asLiveData()
}