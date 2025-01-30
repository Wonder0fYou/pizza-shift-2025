package com.example.pizzashift2025.shared.pizza.data.network.api

sealed class Result<out T> {
    data class Success<out T>(val data: T): Result<T>()
    data class Error(val reason: String): Result<Nothing>()
}
