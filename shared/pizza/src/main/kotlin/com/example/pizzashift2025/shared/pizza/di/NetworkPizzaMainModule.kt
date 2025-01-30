package com.example.pizzashift2025.shared.pizza.di

import com.example.pizzashift2025.shared.pizza.data.network.api.PizzaApiService
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