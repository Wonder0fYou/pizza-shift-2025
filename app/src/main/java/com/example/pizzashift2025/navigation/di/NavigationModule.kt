package com.example.pizzashift2025.navigation.di

import com.example.pizzashift2025.feature.pizza_details.presentation.PizzaDetailsRouter
import com.example.pizzashift2025.navigation.GlobalRouter
import com.example.pizzashift2025.navigation.GlobalRouterImpl
import com.example.pizzashift2025.navigation.featureRoute.MainListRouterImpl
import com.example.pizzashift2025.navigation.featureRoute.MainRouterImpl
import com.example.pizzashift2025.feature.pizza_main_list.pizza_main_list.presentation.MainListRouter
import com.example.pizzashift2025.navigation.NavControllerHolder
import com.example.pizzashift2025.navigation.featureRoute.PizzaDetailsRouterImpl
import com.example.pizzashift2025.presentation.MainRouter
import com.example.pizzashift2025.util.ApplicationScope
import dagger.Module
import dagger.Provides

@Module
object NavigationModule {

    @Provides
    @ApplicationScope
    fun provideGlobalRouter(globalRouterImpl: GlobalRouterImpl): GlobalRouter {
        return globalRouterImpl
    }

    @Provides
    @ApplicationScope
    fun provideNavControllerHolder(globalRouterImpl: GlobalRouterImpl): NavControllerHolder {
        return globalRouterImpl
    }

    @Provides
    fun provideListMainRoute(mainListRouterImpl: MainListRouterImpl): MainListRouter {
        return mainListRouterImpl
    }

    @Provides
    fun provideMainRoute(mainRouterImpl: MainRouterImpl): MainRouter {
        return mainRouterImpl
    }

    @Provides
    fun provideDetailsRoute(detailsRoute: PizzaDetailsRouterImpl): PizzaDetailsRouter {
        return detailsRoute
    }
}