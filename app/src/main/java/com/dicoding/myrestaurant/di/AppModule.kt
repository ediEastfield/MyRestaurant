package com.dicoding.myrestaurant.di

import com.dicoding.myrestaurant.core.domain.usecase.RestaurantInteractor
import com.dicoding.myrestaurant.core.domain.usecase.RestaurantUseCase
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
abstract class AppModule {

    @Binds
    abstract fun provideRestaurantUseCase(restaurantInteractor: RestaurantInteractor): RestaurantUseCase
}