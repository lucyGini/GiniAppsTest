package com.example.giniappstest.data.network


import com.google.gson.annotations.SerializedName

data class PixalImages(
    @SerializedName("hits")
    val hits: List<Hit>,
    @SerializedName("total")
    val total: Int,
    @SerializedName("totalHits")
    val totalHits: Int
)