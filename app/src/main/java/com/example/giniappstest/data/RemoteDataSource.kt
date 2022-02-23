package com.example.giniappstest.data

import com.example.giniappstest.data.network.PixalImages
import com.example.giniappstest.data.network.SimpleApi
import com.example.giniappstest.util.Constants
import retrofit2.Response
import javax.inject.Inject

class RemoteDataSource @Inject constructor(
    private val simpleApi: SimpleApi
    ){

    suspend fun getImages(): PixalImages {
        return simpleApi.getImages()
    }
}