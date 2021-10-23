package com.dicoding.myrestaurant.core.data.source.remote

import android.util.Log
import com.dicoding.myrestaurant.core.data.source.remote.network.ApiResponse
import com.dicoding.myrestaurant.core.data.source.remote.network.ApiService
import com.dicoding.myrestaurant.core.data.source.remote.response.CustomerReviewResponse
import com.dicoding.myrestaurant.core.data.source.remote.response.DetailRestaurantResponse
import com.dicoding.myrestaurant.core.data.source.remote.response.MenuResponse
import com.dicoding.myrestaurant.core.data.source.remote.response.RestaurantResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class RemoteDataSource @Inject constructor(private val apiService: ApiService) {

    suspend fun getAllRestaurant(): Flow<ApiResponse<List<RestaurantResponse>>> {
        return flow {
            try {
                val response = apiService.getList()
                val dataArray = response.restaurants
                if (dataArray.isNotEmpty()) {
                    emit(ApiResponse.Success(response.restaurants))
                } else {
                    emit(ApiResponse.Empty)
                }
            } catch (e : Exception) {
                emit(ApiResponse.Error(e.toString()))
                Log.e("RemoteDataSource", e.toString())
            }
        }.flowOn(Dispatchers.IO)
    }

    fun getDetailRestaurant(id: String): Flow<ApiResponse<DetailRestaurantResponse>> {
        return flow {
            try {
                val response = apiService.getDetail(id)
                val dataArray = response.restaurant
                if (dataArray.id.isNotEmpty()) {
                    emit(ApiResponse.Success(response.restaurant))
                } else {
                    emit((ApiResponse.Empty))
                }
            } catch (e : Exception) {
                emit((ApiResponse.Error(e.toString())))
                Log.e("RemoteDataSource", e.toString())
            }
        }.flowOn(Dispatchers.IO)
    }

    fun getMenuRestaurant(id: String): Flow<MenuResponse> {
        return  flow {
            try {
                val response = apiService.getDetail(id)
                val dataArray = response.restaurant.menus
                emit(dataArray)
            } catch (e : Exception) {
                Log.e("RemoteDataSource", e.toString())
            }
        }.flowOn(Dispatchers.IO)
    }

    fun getReviewRestaurant(id: String): Flow<List<CustomerReviewResponse>> {
        return  flow {
            try {
                val response = apiService.getDetail(id)
                val dataArray = response.restaurant.customerReviews
                emit(dataArray)
            } catch (e : Exception) {
                Log.e("RemoteDataSource", e.toString())
            }
        }
    }

    fun getSearchRestaurant(q: String): Flow<List<RestaurantResponse>> {
        return flow {
            try {
                val response = apiService.getSearch(q)
                val dataArray = response.restaurants
                emit(dataArray)
            } catch (e : Exception) {
                Log.e("RemoteDataSource", e.toString())
            }
        }.flowOn(Dispatchers.IO)
    }

    fun postReviewRestaurant(id: String, name: String, review: String): Flow<List<CustomerReviewResponse>> {
        return flow {
            try {
                val response = apiService.postReview(id, name, review)
                val dataArray = response.customerReviews
                emit(dataArray)
            } catch (e: Exception) {
                Log.e("RemoteDataSource", e.toString())
            }
        }.flowOn(Dispatchers.IO)
    }
}