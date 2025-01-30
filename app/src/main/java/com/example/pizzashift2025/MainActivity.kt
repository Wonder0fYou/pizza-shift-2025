package com.example.pizzashift2025

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.CompositionLocalProvider
import com.example.pizzashift2025.util.LocalViewModelFactory
import com.example.pizzashift2025.util.ViewModelFactory
import com.example.pizzashift2025.pizza_main.ui.PizzaMainScreen
import com.example.pizzashift2025.ui.theme.PizzaShift2025Theme
import javax.inject.Inject

class MainActivity : ComponentActivity() {

    @Inject
    lateinit var viewModelFactory: ViewModelFactory

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val appComponent = (application as PizzaApplication).appComponent
        appComponent.inject(this)

        enableEdgeToEdge()
        setContent {
            CompositionLocalProvider(LocalViewModelFactory provides viewModelFactory) {
                PizzaShift2025Theme {
                    PizzaMainScreen()
                }
            }
        }
    }
}