package com.dicoding.myrestaurant.search

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.dicoding.myrestaurant.core.domain.usecase.RestaurantUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class SearchViewModel @Inject constructor(private val restaurantUseCase: RestaurantUseCase) : ViewModel() {
    fun search(q: String) = restaurantUseCase.getSearchRestaurant(q).asLiveData()
}