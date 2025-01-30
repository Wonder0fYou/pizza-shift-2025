package com.example.pizzashift2025.di

import android.app.Application
import com.example.pizzashift2025.MainActivity
import com.example.pizzashift2025.navigation.di.NavigationModule
import com.example.pizzashift2025.pizza_main.di.NetworkPizzaMainModule
import com.example.pizzashift2025.pizza_main.di.RepositoryPizzaMainModule
import com.example.pizzashift2025.util.ApplicationScope
import com.example.pizzashift2025.util.ViewModelFactory
import dagger.BindsInstance
import dagger.Component

@Component(
    modules = [
        NetworkModule::class,
        RepositoryPizzaMainModule::class,
        NavigationModule::class,
        ViewModelModule:: class
    ]
)
@ApplicationScope
interface AppComponent {
    fun getViewModelFactory(): ViewModelFactory
    @Component.Factory
    interface Factory {
        fun create(
            @BindsInstance app: Application
        ): AppComponent
    }
    fun inject(activity: MainActivity)
}