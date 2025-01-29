package com.example.pizzashift2025

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.example.pizzashift2025.pizza_main.presentation.PizzaMainScreen
import com.example.pizzashift2025.ui.theme.PizzaShift2025Theme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val appComponent = (application as PizzaApplication).appComponent
        appComponent.inject(this)

        enableEdgeToEdge()
        setContent {
            PizzaShift2025Theme {
                PizzaMainScreen()
            }
        }
    }
}