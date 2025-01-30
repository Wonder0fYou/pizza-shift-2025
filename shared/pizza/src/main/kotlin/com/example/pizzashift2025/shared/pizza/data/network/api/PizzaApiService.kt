package com.example.pizzashift2025.shared.pizza.data.network.api

import com.example.pizzashift2025.shared.pizza.data.network.model.CatalogResponseDto
import retrofit2.http.GET

interface PizzaApiService {

    @GET("pizza/catalog")
    suspend fun getAllPizzaList(): CatalogResponseDto
}