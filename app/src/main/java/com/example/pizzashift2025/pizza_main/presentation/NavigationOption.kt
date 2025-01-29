package com.example.pizzashift2025.pizza_main.presentation

import kotlin.reflect.KClass

enum class NavigationOption(val route: KClass<*>) {
    CATALOG(MainListRoute::class)
}