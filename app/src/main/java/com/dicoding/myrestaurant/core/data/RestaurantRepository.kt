package com.dicoding.myrestaurant.core.data

import com.dicoding.myrestaurant.R
import com.dicoding.myrestaurant.core.data.source.local.LocalDataSource
import com.dicoding.myrestaurant.core.data.source.remote.RemoteDataSource
import com.dicoding.myrestaurant.core.data.source.remote.network.ApiResponse
import com.dicoding.myrestaurant.core.data.source.remote.response.DetailRestaurantResponse
import com.dicoding.myrestaurant.core.data.source.remote.response.RestaurantResponse
import com.dicoding.myrestaurant.core.domain.model.CustomerReview
import com.dicoding.myrestaurant.core.domain.model.DetailRestaurant
import com.dicoding.myrestaurant.core.domain.model.Menu
import com.dicoding.myrestaurant.core.domain.model.Restaurant
import com.dicoding.myrestaurant.core.domain.repository.IRestaurantRepository
import com.dicoding.myrestaurant.core.utils.AppExecutors
import com.dicoding.myrestaurant.core.utils.DataMapper
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class RestaurantRepository @Inject constructor(
    private val remoteDataSource: RemoteDataSource,
    private val localDataSource: LocalDataSource,
    private val appExecutors: AppExecutors
) : IRestaurantRepository {

    override fun getAllRestaurant(): Flow<Resource<List<Restaurant>>> =
        object : NetworkBoundResource<List<Restaurant>, List<RestaurantResponse>>() {
            override fun loadFromDB(): Flow<List<Restaurant>> {
                return localDataSource.getAllRestaurant().map {
                    DataMapper.mapRestaurantEntitiesToDomain(it)
                }
            }

            override fun shouldFetch(data: List<Restaurant>?): Boolean =
                data == null || data.isEmpty()

            override suspend fun createCall(): Flow<ApiResponse<List<RestaurantResponse>>> =
                remoteDataSource.getAllRestaurant()

            override suspend fun saveCallResult(data: List<RestaurantResponse>) {
                val restaurantList = DataMapper.mapRestaurantResponseToEntities(data)
                localDataSource.insertRestaurant(restaurantList)
            }
        }.asFlow()

    override fun getDetailRestaurant(id: String): Flow<Resource<DetailRestaurant>> =
        object : NetworkBoundResource<DetailRestaurant, DetailRestaurantResponse>() {
            override fun loadFromDB(): Flow<DetailRestaurant> {
                return localDataSource.getDetailRestaurant(id).map { detail ->
                    if (detail != null) {
                        DataMapper.mapDetailRestaurantEntitiesToDomain(detail)
                    } else {
                        detail
                    }
                }
            }

            override fun shouldFetch(data: DetailRestaurant?): Boolean =
                data == null

            override suspend fun createCall(): Flow<ApiResponse<DetailRestaurantResponse>> =
                remoteDataSource.getDetailRestaurant(id)

            override suspend fun saveCallResult(data: DetailRestaurantResponse) {
                val restaurant = DataMapper.mapDetailRestaurantResponseToEntities(data)
                localDataSource.insertDetailRestaurant(restaurant)
            }
        }.asFlow()

    override fun getMenuRestaurant(id: String): Flow<Menu> {
        return remoteDataSource.getMenuRestaurant(id).map {
            DataMapper.mapMenuResponseToDomain(it)
        }
    }

    override fun getReviewRestaurant(id: String): Flow<List<CustomerReview>> {
        return remoteDataSource.getReviewRestaurant(id).map {
            DataMapper.mapCustomerReviewResponseToDomain(it)
        }
    }


    override fun getAllFavoriteRestaurant(): Flow<List<Restaurant>> {
        return localDataSource.getAllFavoriteRestaurant().map {
            DataMapper.mapDetailRestaurantEntitiesToRestaurantDomain(it)
        }
    }

    override fun setFavoriteRestaurant(restaurant: DetailRestaurant, state: Boolean) {
        val restaurantEntity = DataMapper.mapDetailRestaurantDomainToEntity(restaurant)
        appExecutors.diskIO().execute { localDataSource.setFavoriteRestaurant(restaurantEntity, state) }
    }

    override fun getSearchRestaurant(q: String): Flow<List<Restaurant>> {
        return remoteDataSource.getSearchRestaurant(q).map {
            DataMapper.mapRestaurantResponseToDomain(it)
        }
    }

    override fun postReviewRestaurant(
        id: String,
        name: String,
        review: String
    ): Flow<List<CustomerReview>> {
        return remoteDataSource.postReviewRestaurant(id, name, review).map {
            DataMapper.mapCustomerReviewResponseToDomain(it)
        }
    }
}