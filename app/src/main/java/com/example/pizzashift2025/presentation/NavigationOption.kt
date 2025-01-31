package com.example.pizzashift2025.presentation

import com.example.pizzashift2025.feature.pizza_main_list.pizza_main_list.MainListRoute
import kotlin.reflect.KClass

enum class NavigationOption(val route: KClass<*>) {
    CATALOG(MainListRoute::class),
    ORDERS(MainListRoute::class),
    CART(MainListRoute::class),
    PROFILE(MainListRoute::class)
}