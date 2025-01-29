package com.example.pizzashift2025.navigation

interface GlobalRouter {

    fun open(route: Any)

    fun pop()

    fun openPoppingAllPrevious(route: Any)
}