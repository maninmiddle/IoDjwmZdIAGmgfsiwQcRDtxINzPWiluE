package com.example.project.domain

import com.example.project.data.model.Categories
import com.example.project.data.model.Meals
import retrofit2.Response

interface ProjectRepository {

    suspend fun getProducts(): Response<Meals>
    suspend fun getCategories(): Response<Categories>
}