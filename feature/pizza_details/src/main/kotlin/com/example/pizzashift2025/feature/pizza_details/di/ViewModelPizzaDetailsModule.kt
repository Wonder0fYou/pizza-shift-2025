package com.example.pizzashift2025.feature.pizza_details.di

import androidx.lifecycle.ViewModel
import com.example.pizzashift2025.feature.pizza_details.presentation.PizzaDetailsViewModel
import com.example.pizzashift2025.util.ViewModelKey
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
interface ViewModelPizzaDetailsModule {
    @Binds
    @IntoMap
    @ViewModelKey(PizzaDetailsViewModel::class)
    fun bindViewModelPizzaDetails(viewModel: PizzaDetailsViewModel): ViewModel
}