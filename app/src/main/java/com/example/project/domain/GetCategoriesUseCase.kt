package com.example.project.domain

import javax.inject.Inject

class GetCategoriesUseCase @Inject constructor(
    private val repository: ProjectRepository
) {
    suspend fun getCategories() = repository.getCategories()
}