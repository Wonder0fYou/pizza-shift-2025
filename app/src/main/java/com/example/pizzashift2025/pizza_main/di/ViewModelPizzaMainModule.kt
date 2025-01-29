package com.example.pizzashift2025.pizza_main.di

import androidx.lifecycle.ViewModel
import com.example.pizzashift2025.pizza_main.presentation.PizzaMainViewModel
import com.example.pizzashift2025.di.ViewModelKey
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
interface ViewModelPizzaMainModule {
    @Binds
    @IntoMap
    @ViewModelKey(PizzaMainViewModel::class)
    fun bindViewModelPizzaMain(viewModel: PizzaMainViewModel): ViewModel
}