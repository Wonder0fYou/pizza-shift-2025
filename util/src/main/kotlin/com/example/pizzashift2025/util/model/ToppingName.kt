package com.example.pizzashift2025.util.model

import com.example.pizzashift2025.util.R

enum class ToppingName(
    val displayNameResId: Int
) {
    PINEAPPLE(R.string.pineapple),
    GREEN_PEPPER(R.string.green_pepper),
    MUSHROOMS(R.string.mushrooms),
    BACON(R.string.bacon),
    SHRIMPS(R.string.shrimps),
    HAM(R.string.ham),
    MOZZARELLA(R.string.mozzarella),
    PEPERONI(R.string.peperoni),
    CHICKEN_FILLET(R.string.chicken_fillet),
    ONION(R.string.onion),
    BASIL(R.string.basil),
    CHILE(R.string.chile),
    CHEDDAR(R.string.cheddar),
    MEATBALLS(R.string.meatballs),
    PICKLE(R.string.pickle),
    TOMATO(R.string.tomato),
    FETA(R.string.feta);

    companion object {
        fun fromString(toppingName: String): ToppingName {
            return when (toppingName) {
                "PINEAPPLE" -> PINEAPPLE
                "GREEN_PEPPER" -> GREEN_PEPPER
                "MUSHROOMS" -> MUSHROOMS
                "BACON" -> BACON
                "SHRIMPS" -> SHRIMPS
                "HAM" -> HAM
                "MOZZARELLA" -> MOZZARELLA
                "PEPERONI" -> PEPERONI
                "CHICKEN_FILLET" -> CHICKEN_FILLET
                "ONION" -> ONION
                "BASIL" -> BASIL
                "CHILE" -> CHILE
                "CHEDDAR" -> CHEDDAR
                "MEATBALLS" -> MEATBALLS
                "PICKLE" -> PICKLE
                "TOMATO" -> TOMATO
                "FETA" -> FETA
                else -> throw IllegalArgumentException("Unknown topping: $toppingName")
            }
        }
    }
}