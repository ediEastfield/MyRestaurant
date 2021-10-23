package com.dicoding.myrestaurant.core.data.source.remote.network

import com.dicoding.myrestaurant.core.data.source.remote.response.ListDetailRestaurantResponse
import com.dicoding.myrestaurant.core.data.source.remote.response.ListRestaurantResponse
import com.dicoding.myrestaurant.core.data.source.remote.response.ListSearchRestaurantResponse
import com.dicoding.myrestaurant.core.data.source.remote.response.PostReviewResponse
import retrofit2.http.*

interface ApiService {

    @GET("list")
    suspend fun getList(): ListRestaurantResponse

    @GET("detail/{id}")
    suspend fun getDetail(
        @Path("id") id: String
    ): ListDetailRestaurantResponse

    @GET("search")
    suspend fun getSearch(
        @Query("q") q: String
    ): ListSearchRestaurantResponse

    @FormUrlEncoded
    @Headers("Authorization: token 12345")
    @POST("review")
    suspend fun postReview(
        @Field("id") id: String,
        @Field("name") name: String,
        @Field("review") review: String
    ): PostReviewResponse
}