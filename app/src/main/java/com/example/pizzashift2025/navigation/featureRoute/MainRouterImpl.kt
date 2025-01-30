package com.example.pizzashift2025.navigation.featureRoute

import com.example.pizzashift2025.navigation.GlobalRouter
import com.example.pizzashift2025.feature.pizza_main_list.pizza_main_list.MainListRoute
import com.example.pizzashift2025.presentation.MainRouter
import javax.inject.Inject

class MainRouterImpl @Inject constructor(
    private val globalRouter: GlobalRouter
): MainRouter {
    override fun openListPizza() {
        globalRouter.openPoppingAllPrevious(MainListRoute)
    }
}