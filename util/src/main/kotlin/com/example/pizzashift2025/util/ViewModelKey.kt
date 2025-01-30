package com.example.pizzashift2025.util

import androidx.lifecycle.ViewModel
import dagger.MapKey
import kotlin.reflect.KClass

@MapKey
annotation class ViewModelKey(val value: KClass<out ViewModel>)