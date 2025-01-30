package com.example.pizzashift2025.pizza_main.data.network.model

data class CatalogItemDto(
    val id: String,
    val name: String,
    val ingredients: List<IngredientDto>,
    val toppings: List<ToppingDto>,
    val description: String,
    val sizes: List<SizeDto>,
    val doughs: List<DoughDto>,
    val calories: Int,
    val protein: String,
    val totalFat: String,
    val carbohydrates: String,
    val sodium: String,
    val allergens: List<String>,
    val isVegetarian: Boolean,
    val isGlutenFree: Boolean,
    val isNew: Boolean,
    val isHit: Boolean,
    val img: String
)
