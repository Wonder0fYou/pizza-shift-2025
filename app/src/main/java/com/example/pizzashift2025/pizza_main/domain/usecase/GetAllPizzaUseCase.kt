package com.example.pizzashift2025.pizza_main.domain.usecase

import com.example.pizzashift2025.pizza_main.data.network.api.Result
import com.example.pizzashift2025.pizza_main.domain.model.CatalogItem
import com.example.pizzashift2025.pizza_main.domain.repository.PizzaMainRepository
import javax.inject.Inject

class GetAllPizzaUseCase @Inject constructor(
    private val pizzaRepository: PizzaMainRepository
) {
    suspend operator fun invoke(): Result<List<CatalogItem>> {
        return pizzaRepository.getAllPizza()
    }
}