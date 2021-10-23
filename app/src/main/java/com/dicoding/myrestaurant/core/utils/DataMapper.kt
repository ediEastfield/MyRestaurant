package com.dicoding.myrestaurant.core.utils

import com.dicoding.myrestaurant.core.data.source.local.entity.DetailRestaurantEntity
import com.dicoding.myrestaurant.core.data.source.local.entity.RestaurantEntity
import com.dicoding.myrestaurant.core.data.source.remote.response.*
import com.dicoding.myrestaurant.core.domain.model.*

object DataMapper {

    fun mapRestaurantResponseToEntities(input: List<RestaurantResponse>): List<RestaurantEntity> {
        val restaurantList = ArrayList<RestaurantEntity>()
        input.map {
            val restaurant = RestaurantEntity(
                restaurantId = it.id,
                name = it.name,
                pictureId = it.pictureId,
                city = it.city,
                rating = it.rating
            )
            restaurantList.add(restaurant)
        }
        return restaurantList
    }

    fun mapRestaurantResponseToDomain(input: List<RestaurantResponse>): List<Restaurant> =
        input.map {
            Restaurant(
                restaurantId = it.id,
                name = it.name,
                pictureId = it.pictureId,
                city = it.city,
                rating = it.rating
            )
        }

    fun mapRestaurantEntitiesToDomain(input: List<RestaurantEntity>): List<Restaurant> =
        input.map {
            Restaurant(
                restaurantId = it.restaurantId,
                name = it.name,
                pictureId = it.pictureId,
                city = it.city,
                rating = it.rating
            )
        }

    fun mapDetailRestaurantEntitiesToDomain(input: DetailRestaurantEntity) = DetailRestaurant(
        id = input.restaurantId,
        name = input.name,
        description = input.description,
        city = input.city,
        address = input.address,
        pictureId = input.pictureId,
        rating = input.rating,
        isFavorite = input.isFavorite
    )

    fun mapDetailRestaurantResponseToEntities(input: DetailRestaurantResponse) = DetailRestaurantEntity(
        restaurantId = input.id,
        name = input.name,
        description = input.description,
        city = input.city,
        address = input.address,
        pictureId = input.pictureId,
        rating = input.rating,
        isFavorite = false
    )

    fun mapDetailRestaurantEntitiesToRestaurantDomain(input: List<DetailRestaurantEntity>): List<Restaurant> =
        input.map {
            Restaurant(
                restaurantId = it.restaurantId,
                name = it.name,
                city = it.city,
                pictureId = it.pictureId,
                rating = it.rating
            )
        }

    fun mapDetailRestaurantDomainToEntity(input: DetailRestaurant) = DetailRestaurantEntity(
        restaurantId = input.id,
        name = input.name,
        description = input.description,
        city = input.city,
        address = input.address,
        pictureId = input.pictureId,
        rating = input.rating,
        isFavorite = input.isFavorite
    )

    private fun mapFoodDrinkToDomain(input: List<FoodDrinkResponse>): List<FoodDrink> =
        input.map {
            FoodDrink(
                name = it.name
            )
        }

    fun mapMenuResponseToDomain(input: MenuResponse) = Menu(
        foods = mapFoodDrinkToDomain(input.foods),
        drinks = mapFoodDrinkToDomain(input.drinks)
    )

    fun mapCustomerReviewResponseToDomain(input: List<CustomerReviewResponse>): List<CustomerReview> =
        input.map {
            CustomerReview(
                name = it.name,
                date = it.date,
                review = it.review
            )
        }
}