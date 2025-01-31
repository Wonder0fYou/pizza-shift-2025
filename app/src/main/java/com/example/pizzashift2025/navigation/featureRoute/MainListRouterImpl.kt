package com.example.pizzashift2025.navigation.featureRoute

import com.example.pizzashift2025.feature.pizza_details.PizzaDetailsRoute
import com.example.pizzashift2025.navigation.GlobalRouter
import com.example.pizzashift2025.feature.pizza_main_list.pizza_main_list.presentation.MainListRouter
import javax.inject.Inject

class MainListRouterImpl @Inject constructor(
    private val globalRouter: GlobalRouter
): MainListRouter {
    override fun openPizza(id: String) {
        if (id != null) {
            globalRouter.open(PizzaDetailsRoute(id))
        }
    }
}