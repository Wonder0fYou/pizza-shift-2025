package com.example.pizzashift2025.shared.pizza.data.network.model

data class CatalogResponseDto(
    val success: Boolean,
    val reason: String?,
    val catalog: List<CatalogItemDto>
)
