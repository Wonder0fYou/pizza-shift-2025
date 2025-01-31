package com.example.pizzashift2025.feature.pizza_details.presentation

import com.example.pizzashift2025.shared.pizza.domain.model.CatalogItem

sealed interface PizzaDetailsState {
    data object Initial: PizzaDetailsState
    data object Loading: PizzaDetailsState
    data class Failure(val message: String?): PizzaDetailsState
    data class Content(val pizza: CatalogItem): PizzaDetailsState
}