package com.example.pizzashift2025.shared.pizza.domain.repository

import com.example.pizzashift2025.shared.pizza.domain.model.CatalogItem

interface PizzaDetailsRepository {
    suspend fun getCurrentPizza(id: Int): CatalogItem
}