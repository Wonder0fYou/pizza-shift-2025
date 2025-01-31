package com.example.pizzashift2025.feature.pizza_details.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.pizzashift2025.shared.pizza.domain.usecase.GetCurrentPizzaDetails
import kotlinx.coroutines.CancellationException
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

class PizzaDetailsViewModel @Inject constructor(
    private val router: PizzaDetailsRouter,
    private val getCurrentPizzaDetails: GetCurrentPizzaDetails,
): ViewModel() {

    private val _state = MutableStateFlow<PizzaDetailsState>(PizzaDetailsState.Initial)
    val state: StateFlow<PizzaDetailsState> = _state

    fun loadPizza(pizzaId: String) {
        if (_state.value is PizzaDetailsState.Content || _state.value is PizzaDetailsState.Loading) {
            return
        }

        viewModelScope.launch {
            _state.value = PizzaDetailsState.Loading

            try {
                val pizza = getCurrentPizzaDetails(pizzaId)
                _state.value = PizzaDetailsState.Content(pizza)
            } catch (ex: CancellationException) {
                throw ex
            } catch (e: Exception) {
                _state.value = PizzaDetailsState.Failure(e.message)
            }
        }
    }

    fun goBack() {
        router.goBack()
    }
}