package com.example.pizzashift2025.navigation.featureRoute

import com.example.pizzashift2025.feature.pizza_details.presentation.PizzaDetailsRouter
import com.example.pizzashift2025.navigation.GlobalRouter
import javax.inject.Inject

class PizzaDetailsRouterImpl @Inject constructor(
    private val globalRouter: GlobalRouter
): PizzaDetailsRouter {
    override fun goBack() {
        globalRouter.pop()
    }
}