package com.example.pizzashift2025.shared.pizza.domain.repository

import com.example.pizzashift2025.util.Result
import com.example.pizzashift2025.shared.pizza.domain.model.CatalogItem

interface PizzaMainRepository {
    suspend fun getAllPizza(): Result<List<CatalogItem>>
}