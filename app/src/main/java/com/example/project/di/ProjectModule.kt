package com.example.project.di

import android.content.Context
import com.example.project.data.network.ApiService
import com.example.project.data.network.OkHttpClientFactory
import com.example.project.data.repository.ProjectRepositoryImpl
import com.example.project.domain.ProjectRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object ProjectModule {

    @Provides
    fun provideProjectRepository(apiService: ApiService): ProjectRepository {
        return ProjectRepositoryImpl(apiService)
    }

    @Provides
    @Singleton
    fun provideRetrofitInstance(@ApplicationContext context: Context): ApiService {
        val okHttpClient = OkHttpClientFactory.createOkHttpClient(context)
        return Retrofit.Builder()
            .baseUrl("https://themealdb.com")
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(ApiService::class.java)
    }


}