package com.example.pizzashift2025.util.model

import com.example.pizzashift2025.util.R

enum class DoughName(
    val displayNameResId: Int,
) {
    THIN(R.string.dough_thin),
    THICK(R.string.dough_thick);

    companion object {
        fun fromString(doughName: String): DoughName {
            return when (doughName) {
                "THIN"  -> THIN
                "THICK" -> THICK
                else -> throw IllegalArgumentException("Unknown dough type: $doughName")
            }
        }
    }
}