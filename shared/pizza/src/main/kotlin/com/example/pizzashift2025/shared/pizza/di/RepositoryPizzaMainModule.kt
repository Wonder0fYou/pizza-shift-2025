package com.example.pizzashift2025.shared.pizza.di

import com.example.pizzashift2025.shared.pizza.data.repository.PizzaMainRepositoryImpl
import com.example.pizzashift2025.shared.pizza.domain.repository.PizzaMainRepository
import dagger.Module
import dagger.Provides

@Module
object RepositoryPizzaMainModule {

    @Provides
    fun providePizzaMainRepository(repositoryImpl: PizzaMainRepositoryImpl): PizzaMainRepository {
        return repositoryImpl
    }
}