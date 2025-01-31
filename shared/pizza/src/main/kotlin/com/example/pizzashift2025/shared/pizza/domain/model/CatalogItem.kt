package com.example.pizzashift2025.shared.pizza.domain.model

data class CatalogItem(
    val id: String,
    val name: String,
    val ingredients: List<Ingredient>,
    val toppings: List<Topping>,
    val description: String,
    val sizes: List<Size>,
    val doughs: List<Dough>,
    val calories: Int,
    val totalFat: String,
    val img: String
)
