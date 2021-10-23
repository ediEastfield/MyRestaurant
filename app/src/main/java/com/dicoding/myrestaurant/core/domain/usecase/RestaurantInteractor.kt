package com.dicoding.myrestaurant.core.domain.usecase

import com.dicoding.myrestaurant.core.domain.model.CustomerReview
import com.dicoding.myrestaurant.core.domain.model.DetailRestaurant
import com.dicoding.myrestaurant.core.domain.model.Menu
import com.dicoding.myrestaurant.core.domain.repository.IRestaurantRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class RestaurantInteractor @Inject constructor(private val restaurantRepository: IRestaurantRepository): RestaurantUseCase {

    override fun getAllRestaurant() = restaurantRepository.getAllRestaurant()
    override fun getDetailRestaurant(id: String) = restaurantRepository.getDetailRestaurant(id)
    override fun getMenuRestaurant(id: String) = restaurantRepository.getMenuRestaurant(id)
    override fun getReviewRestaurant(id: String) = restaurantRepository.getReviewRestaurant(id)
    override fun getAllFavoriteRestaurant() = restaurantRepository.getAllFavoriteRestaurant()
    override fun setFavoriteRestaurant(restaurant: DetailRestaurant, state: Boolean) = restaurantRepository.setFavoriteRestaurant(restaurant, state)
    override fun getSearchRestaurant(q: String) = restaurantRepository.getSearchRestaurant(q)
    override fun postReviewRestaurant(
        id: String,
        name: String,
        review: String
    ) = restaurantRepository.postReviewRestaurant(id, name, review)
}