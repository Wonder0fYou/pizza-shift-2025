package com.example.pizzashift2025.navigation

import androidx.navigation.NavController

interface NavControllerHolder {

    fun setNavController(navController: NavController)

    fun clearNavController()
}