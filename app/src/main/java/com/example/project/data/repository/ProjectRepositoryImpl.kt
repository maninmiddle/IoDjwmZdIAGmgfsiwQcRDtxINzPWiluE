package com.example.project.data.repository

import com.example.project.data.network.ApiService
import com.example.project.data.model.Categories
import com.example.project.data.model.Meals
import com.example.project.domain.ProjectRepository
import retrofit2.Response
import javax.inject.Inject

class ProjectRepositoryImpl @Inject constructor(
    private val apiService: ApiService
) : ProjectRepository {

    override suspend fun getProducts(): Response<Meals> {
        return apiService.getProducts()
    }

    override suspend fun getCategories(): Response<Categories> {
        return apiService.getCategories()
    }
}