package com.example.pizzashift2025.feature.pizza_main_list.pizza_main_list.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.pizzashift2025.shared.pizza.data.network.api.Result
import com.example.pizzashift2025.shared.pizza.domain.usecase.GetAllPizzaUseCase
import kotlinx.coroutines.CancellationException
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

class PizzaListMainViewModel @Inject constructor(
    private val getPizzaUseCase: GetAllPizzaUseCase,
    private val router: MainListRouter
): ViewModel() {

    private val _state = MutableStateFlow<PizzaListMainState>(PizzaListMainState.Initial)
    val state: StateFlow<PizzaListMainState> = _state

    fun loadPizza() {
        if (_state.value is PizzaListMainState.Content || _state.value is PizzaListMainState.Loading) {
            return
        }

        viewModelScope.launch {
            _state.value = PizzaListMainState.Loading
            try {
                when(val catalog = getPizzaUseCase()) {
                    is Result.Success -> {
                        _state.value = PizzaListMainState.Content(catalog.data)
                    }
                    is Result.Error -> {
                        val message = catalog.reason
                        _state.value = PizzaListMainState.Failure(message)
                    }
                }
            } catch (ce: CancellationException) {
                throw ce
            } catch (e: Exception) {
                _state.value = PizzaListMainState.Failure(e.localizedMessage.orEmpty())
            }
        }
    }

    fun openPizza(pizzaId: Int?) {
        router.openPizza(pizzaId)
    }
}