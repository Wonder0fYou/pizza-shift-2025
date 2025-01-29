package com.example.pizzashift2025.navigation

import androidx.navigation.NavController
import javax.inject.Inject

class GlobalRouterImpl @Inject constructor(): GlobalRouter, NavControllerHolder {

    private var navController: NavController? = null

    override fun open(route: Any) {
        navController?.navigate(route)
    }

    override fun pop() {
        navController?.popBackStack()
    }

    override fun openPoppingAllPrevious(route: Any) {
        var navController = navController ?: return
        navController.navigate(route) {
            popUpTo(navController.graph.startDestinationId)
            launchSingleTop = true
        }
    }

    override fun setNavController(navController: NavController) {
        this.navController = navController
    }

    override fun clearNavController() {
        navController = null
    }
}