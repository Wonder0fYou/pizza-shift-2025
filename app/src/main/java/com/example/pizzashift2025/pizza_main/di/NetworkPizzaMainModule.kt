package com.example.pizzashift2025.pizza_main.di

import com.example.pizzashift2025.pizza_main.data.network.api.PizzaApiService
import com.example.pizzashift2025.util.ApplicationScope
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit

@Module
object NetworkPizzaMainModule {

    @Provides
    @ApplicationScope
    fun providePizzaApiService(retrofit: Retrofit): PizzaApiService {
        return retrofit.create(PizzaApiService::class.java)
    }
}