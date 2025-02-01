package com.example.pizzashift2025.feature.pizza_details.presentation

import com.example.pizzashift2025.shared.pizza.domain.model.Dough
import com.example.pizzashift2025.shared.pizza.domain.model.Size
import com.example.pizzashift2025.shared.pizza.domain.model.Topping

data class PizzaOrderState(
    val catalogItemId: String = "",
    val size: Size? = null,
    val dough: Dough? = null,
    val toppings: List<Topping> = emptyList(),
    val totalPrice: Int = 0
)
