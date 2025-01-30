package com.example.pizzashift2025.pizza_main.domain.repository

import com.example.pizzashift2025.pizza_main.data.network.api.Result
import com.example.pizzashift2025.pizza_main.domain.model.CatalogItem

interface PizzaMainRepository {
    suspend fun getAllPizza(): Result<List<CatalogItem>>
}