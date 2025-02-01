package com.example.pizzashift2025.feature.pizza_details.presentation

import com.example.pizzashift2025.shared.pizza.domain.model.Dough
import com.example.pizzashift2025.shared.pizza.domain.model.Size
import com.example.pizzashift2025.shared.pizza.domain.model.Topping

sealed interface DetailsUserAction {

    data class SelectSize(val size: Size) : DetailsUserAction

    data class SelectDough(val dough: Dough) : DetailsUserAction

    data class ToggleTopping(val topping: Topping) : DetailsUserAction

    data object InBasket: DetailsUserAction
}