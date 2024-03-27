package com.example.project.presentation.main

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.project.data.model.Categories
import com.example.project.data.model.Category
import com.example.project.data.model.Meals
import com.example.project.domain.GetCategoriesUseCase
import com.example.project.domain.GetProductsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val getProductsUseCase: GetProductsUseCase,
    private val getCategoriesUseCase: GetCategoriesUseCase
) : ViewModel() {
    private val _products = MutableStateFlow(Meals(emptyList()))
    val products: StateFlow<Meals>
        get() = _products

    private val _categories = MutableStateFlow(Categories(emptyList()))
    val categories: StateFlow<Categories>
        get() = _categories


    fun getProducts() {
        viewModelScope.launch(Dispatchers.IO) {
            getProductsUseCase.getProducts().let { res ->
                if (res.isSuccessful) {
                    _products.value = res.body()!!
                } else {
                    Log.e("MainViewModel", "${res.errorBody()}")
                }
            }
        }
    }

    fun getCategories() {
        viewModelScope.launch(Dispatchers.IO) {
            getCategoriesUseCase.getCategories().let { res ->
                if (res.isSuccessful) {
                    _categories.value = res.body()!!
                } else {
                    Log.e("MainViewModel", "${res.errorBody()}")
                }
            }
        }
    }
}