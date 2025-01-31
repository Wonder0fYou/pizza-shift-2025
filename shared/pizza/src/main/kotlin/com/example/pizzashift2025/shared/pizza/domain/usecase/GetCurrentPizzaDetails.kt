package com.example.pizzashift2025.shared.pizza.domain.usecase

import com.example.pizzashift2025.shared.pizza.domain.model.CatalogItem
import com.example.pizzashift2025.shared.pizza.domain.repository.PizzaDetailsRepository
import javax.inject.Inject

class GetCurrentPizzaDetails @Inject constructor(
    private val pizzaDetails: PizzaDetailsRepository
) {
   suspend operator fun invoke(id: String): CatalogItem {
       return pizzaDetails.getCurrentPizza(id)
   }
}