package com.example.pizzashift2025.navigation.featureRoute

import com.example.pizzashift2025.navigation.GlobalRouter
import com.example.pizzashift2025.pizza_main.presentation.MainListRouter
import javax.inject.Inject

class MainListRouterImpl @Inject constructor(
    private val globalRouter: GlobalRouter
): MainListRouter {
    override fun openPizza(id: Int?) {
        TODO("Not yet implemented")
    }
}