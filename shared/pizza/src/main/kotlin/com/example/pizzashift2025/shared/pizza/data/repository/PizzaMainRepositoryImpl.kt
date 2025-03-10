package com.example.pizzashift2025.shared.pizza.data.repository

import com.example.pizzashift2025.shared.pizza.R
import com.example.pizzashift2025.shared.pizza.data.Converter
import com.example.pizzashift2025.shared.pizza.data.network.api.PizzaApiService
import com.example.pizzashift2025.util.Result
import com.example.pizzashift2025.shared.pizza.domain.model.CatalogItem
import com.example.pizzashift2025.shared.pizza.domain.repository.PizzaMainRepository
import javax.inject.Inject

class PizzaMainRepositoryImpl @Inject constructor(
    private val pizzaApi: PizzaApiService,
    private val converter: Converter
): PizzaMainRepository {
    override suspend fun getAllPizza(): Result<List<CatalogItem>> {
        val response = pizzaApi.getAllPizzaList()
        return if (!response.success) {
            Result.Error(response.reason ?: "${R.string.unknown_error}")
        } else {
            val catalogItems = pizzaApi.getAllPizzaList().catalog.map { catalogDto ->
                converter.catalogItemDtoToDomain(catalogDto)
            }
            return Result.Success(catalogItems)
        }
    }
}