package com.example.pizzashift2025.pizza_main.presentation

import com.example.pizzashift2025.shared.pizza.domain.model.CatalogItem

sealed interface PizzaListMainState {

    data object Initial: PizzaListMainState
    data object Loading: PizzaListMainState
    data class Failure(val message: String?): PizzaListMainState
    data class Content(val catalog: List<CatalogItem>): PizzaListMainState
}