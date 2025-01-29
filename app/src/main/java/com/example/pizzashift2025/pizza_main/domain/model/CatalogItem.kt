package com.example.pizzashift2025.pizza_main.domain.model

data class CatalogItem(
    val id: String?,
    val name: String?,
    val ingredients: List<Ingredient?>?,
    val toppings: List<Topping?>?,
    val description: String?,
    val sizes: List<Size?>?,
    val doughs: List<Dough?>?,
    val calories: Int?,
    val protein: String?,
    val totalFat: String?,
    val carbohydrates: String?,
    val sodium: String?,
    val allergens: List<String>?,
    val isVegetarian: Boolean?,
    val isGlutenFree: Boolean?,
    val isNew: Boolean?,
    val isHit: Boolean?,
    val img: String?
)
