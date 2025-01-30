package com.example.pizzashift2025.pizza_main.data.repository

import com.example.pizzashift2025.pizza_main.data.Converter
import com.example.pizzashift2025.pizza_main.data.network.api.PizzaApiService
import com.example.pizzashift2025.pizza_main.data.network.api.Result
import com.example.pizzashift2025.pizza_main.domain.model.CatalogItem
import com.example.pizzashift2025.pizza_main.domain.repository.PizzaMainRepository
import javax.inject.Inject

class PizzaMainRepositoryImpl @Inject constructor(
    private val pizzaApi: PizzaApiService,
    private val converter: Converter
): PizzaMainRepository {
    override suspend fun getAllPizza(): Result<List<CatalogItem>> {
        val response = pizzaApi.getAllPizzaList()
        return if (!response.success) {
            Result.Error(response.reason ?: "Unknown error")
        } else {
            val catalogItems = pizzaApi.getAllPizzaList().catalog.map { catalogDto ->
                converter.catalogItemDtoToDomain(catalogDto)
            }
            return Result.Success(catalogItems)
        }
    }
}