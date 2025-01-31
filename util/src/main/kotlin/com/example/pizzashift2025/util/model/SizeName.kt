package com.example.pizzashift2025.util.model

import com.example.pizzashift2025.util.R

enum class SizeName(
    val displayNameResId: Int,
) {
    SMALL(R.string.size_small),
    MEDIUM(R.string.size_medium),
    LARGE(R.string.size_large);

    companion object {
        fun fromString(sizeName: String): SizeName {
            return when (sizeName) {
                "SMALL" -> SMALL
                "MEDIUM" -> MEDIUM
                "LARGE" -> LARGE
                else -> throw IllegalArgumentException("Unknown pizza size: $sizeName")
            }
        }
    }
}