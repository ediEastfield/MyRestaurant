package com.dicoding.myrestaurant.core.di

import com.dicoding.myrestaurant.core.data.RestaurantRepository
import com.dicoding.myrestaurant.core.domain.repository.IRestaurantRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module(includes = [NetworkModule::class, DatabaseModule::class])
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Binds
    abstract fun provideRepository(restaurantRepository: RestaurantRepository): IRestaurantRepository
}