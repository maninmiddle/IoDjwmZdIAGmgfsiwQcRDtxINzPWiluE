package com.example.project.domain

import javax.inject.Inject

class GetProductsUseCase @Inject constructor(
    private val repository: ProjectRepository
) {
    suspend fun getProducts() = repository.getProducts()
}