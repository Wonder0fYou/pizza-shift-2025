package com.example.pizzashift2025.pizza_main.domain.repository

import com.example.pizzashift2025.pizza_main.domain.model.CatalogItem

interface PizzaMainRepository {
    suspend fun getAllPizza(): List<CatalogItem>
}