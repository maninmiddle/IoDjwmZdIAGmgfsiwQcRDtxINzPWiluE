package com.example.project.data.network

import com.example.project.data.model.Categories
import com.example.project.data.model.Meals
import retrofit2.Response
import retrofit2.http.GET

interface ApiService {
    @GET("/api/json/v1/1/search.php?s")
    suspend fun getProducts(): Response<Meals>

    @GET("/api/json/v1/1/categories.php")
    suspend fun getCategories(): Response<Categories>
}