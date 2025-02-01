package com.example.pizzashift2025.shared.pizza.data.repository

import com.example.pizzashift2025.shared.pizza.data.Converter
import com.example.pizzashift2025.shared.pizza.data.network.api.PizzaApiService
import com.example.pizzashift2025.shared.pizza.domain.model.CatalogItem
import com.example.pizzashift2025.shared.pizza.domain.repository.PizzaDetailsRepository
import javax.inject.Inject

class PizzaDetailsRepositoryImpl @Inject constructor(
    private val api: PizzaApiService,
    private val converter: Converter
): PizzaDetailsRepository {
    override suspend fun getCurrentPizza(id: String): CatalogItem {
        val catalog = api.getAllPizzaList().catalog.map { dto ->
            converter.catalogItemDtoToDomain(dto)
        }
        return catalog[id.toInt()-1]
    }
}