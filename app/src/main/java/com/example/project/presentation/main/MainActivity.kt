package com.example.project.presentation.main

import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.core.view.marginStart
import androidx.core.view.setPadding
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.viewpager2.widget.MarginPageTransformer
import com.example.project.R
import com.example.project.data.model.Banner
import com.example.project.data.model.Meal
import com.example.project.databinding.ActivityMainBinding
import com.example.project.presentation.adapters.banner.BannerAdapter
import com.example.project.presentation.adapters.product.ProductAdapter
import com.google.android.material.chip.Chip
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var viewModel: MainViewModel
    private lateinit var productAdapter: ProductAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = ViewModelProvider(this)[MainViewModel::class.java]
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)


        viewModel.getCategories()
        viewModel.getProducts()
        responseCategories()
        responseProducts()
        bannerSetup()


    }

    private fun responseCategories() {
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.categories.collect { res ->
                    for (category in res.categories) {
                        val chip = Chip(this@MainActivity)
                        chip.apply {
                            text = category.strCategory
                            isClickable = true
                            isCheckable = true
                            chipStrokeWidth = 0F
                            elevation = 5F
                            setChipBackgroundColorResource(R.color.bg_chip_state)
                            setTextColor(getColorStateList(R.color.text_chip_state))
                            setOnCheckedChangeListener { current, isChecked ->
                                if (isChecked) {
                                    val filteredList =
                                        productAdapter.currentList.filter { meal -> meal.strCategory == current.text }
                                    productAdapter.submitList(filteredList)
                                } else {
                                    responseProducts()
                                    Log.d("MainActivity", "Reloaded products")
                                }
                            }
                        }

                        binding.categoriesGroup.addView(chip)
                    }
                }
            }
        }
    }

    private fun bannerSetup() {
        binding.viewPager2.apply {
            clipToPadding = false
            clipChildren = false
            offscreenPageLimit = 3
            setPadding(48, 8, 48, 8)
            setPageTransformer(MarginPageTransformer(20))
        }
        val viewPagerAdapter = BannerAdapter()
        viewPagerAdapter.submitList(
            listOf(
                Banner(R.drawable.custom_banner),
                Banner(R.drawable.custom_banner1),
                Banner(R.drawable.custom_banner2)
            )
        )
        binding.viewPager2.adapter = viewPagerAdapter
    }

    private fun responseProducts() {
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.products.collect { res ->
                    rvSetup(res.meals)
                }
            }
        }
    }


    private fun rvSetup(meals: List<Meal>) {
        productAdapter = ProductAdapter()
        productAdapter.submitList(meals)
        binding.rvLayout.adapter = productAdapter
        binding.rvLayout.layoutManager = LinearLayoutManager(this)
    }
}