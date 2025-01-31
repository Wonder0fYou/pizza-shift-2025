package com.example.pizzashift2025.util

sealed class Result<out T> {
    data class Success<out T>(val data: T): Result<T>()
    data class Error(val reason: String): Result<Nothing>()
}
