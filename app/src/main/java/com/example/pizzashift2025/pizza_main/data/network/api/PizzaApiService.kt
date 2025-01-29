package com.example.pizzashift2025.pizza_main.data.network.api

import com.example.pizzashift2025.pizza_main.data.network.model.CatalogResponseDto
import retrofit2.http.GET

interface PizzaApiService {

    @GET("catalog")
    suspend fun getAllPizzaList(): CatalogResponseDto
}