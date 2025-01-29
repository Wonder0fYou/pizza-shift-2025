package com.example.pizzashift2025.pizza_main.di

import com.example.pizzashift2025.pizza_main.data.repository.PizzaMainRepositoryImpl
import com.example.pizzashift2025.pizza_main.domain.repository.PizzaMainRepository
import dagger.Module
import dagger.Provides

@Module
object RepositoryPizzaMainModule {

    @Provides
    fun providePizzaMainRepository(repositoryImpl: PizzaMainRepositoryImpl): PizzaMainRepository {
        return repositoryImpl
    }
}