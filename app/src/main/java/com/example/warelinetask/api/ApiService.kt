package com.example.warelinetask.api

import com.example.warelinetask.model.ApiImage
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {
    @GET("curated")
    suspend fun getPhotosFromPexelsApi(@Query("per_page") perPage: Int): ApiImage
}
