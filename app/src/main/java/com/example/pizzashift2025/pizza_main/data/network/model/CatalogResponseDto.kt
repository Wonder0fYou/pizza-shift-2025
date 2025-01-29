package com.example.pizzashift2025.pizza_main.data.network.model

data class CatalogResponseDto(
    val success: Boolean?,
    val reason: String?,
    val catalog: List<CatalogItemDto?>
)
