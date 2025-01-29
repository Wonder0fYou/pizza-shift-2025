package com.example.pizzashift2025.pizza_main.presentation

import androidx.lifecycle.ViewModel
import com.example.pizzashift2025.pizza_main.domain.usecase.GetAllPizzaUseCase
import javax.inject.Inject

class PizzaMainViewModel @Inject constructor(
    private val mainUseCase: GetAllPizzaUseCase,
    private val router: MainRouter
): ViewModel() {
}