package com.example.pizzashift2025.feature.pizza_main_list.pizza_main_list.di

import androidx.lifecycle.ViewModel
import com.example.pizzashift2025.util.ViewModelKey
import com.example.pizzashift2025.feature.pizza_main_list.pizza_main_list.presentation.PizzaListMainViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
interface ViewModelPizzaMainModule {
    @Binds
    @IntoMap
    @ViewModelKey(PizzaListMainViewModel::class)
    fun bindViewModelPizzaListMain(viewModel: PizzaListMainViewModel): ViewModel
}