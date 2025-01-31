package com.example.pizzashift2025.shared.pizza.domain.usecase

import com.example.pizzashift2025.util.Result
import com.example.pizzashift2025.shared.pizza.domain.model.CatalogItem
import com.example.pizzashift2025.shared.pizza.domain.repository.PizzaMainRepository
import javax.inject.Inject

class GetAllPizzaUseCase @Inject constructor(
    private val pizzaRepository: PizzaMainRepository
) {
    suspend operator fun invoke(): Result<List<CatalogItem>> {
        return pizzaRepository.getAllPizza()
    }
}