package com.example.pizzashift2025.util.model

import com.example.pizzashift2025.util.R

enum class IngredientName(
    val displayNameResId: Int
) {
    MOZZARELLA(R.string.mozzarella),
    PEPERONI(R.string.peperoni),
    GREEN_PEPPER(R.string.green_pepper),
    MUSHROOMS(R.string.mushrooms);

    companion object {
        fun fromString(ingredient: String): IngredientName {
            return when(ingredient) {
                "MOZZARELLA" -> MOZZARELLA
                "PEPERONI" -> PEPERONI
                "GREEN_PEPPER" -> GREEN_PEPPER
                "MUSHROOMS" -> MUSHROOMS
                else -> throw IllegalArgumentException("Unknown ingredient: $ingredient")
            }
        }
    }
}