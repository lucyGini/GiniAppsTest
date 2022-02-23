package com.example.giniappstest.data.network

import com.example.giniappstest.util.Constants
import retrofit2.http.GET
import retrofit2.http.Query

interface SimpleApi {

    @GET("api")
    suspend fun getImages(
        @Query("key") key: String = Constants.API_KEY
    ): PixalImages
}