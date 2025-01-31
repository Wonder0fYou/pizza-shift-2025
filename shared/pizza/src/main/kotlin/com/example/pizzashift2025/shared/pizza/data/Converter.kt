package com.example.pizzashift2025.shared.pizza.data

import com.example.pizzashift2025.shared.pizza.data.network.model.CatalogItemDto
import com.example.pizzashift2025.shared.pizza.data.network.model.DoughDto
import com.example.pizzashift2025.shared.pizza.data.network.model.IngredientDto
import com.example.pizzashift2025.shared.pizza.data.network.model.SizeDto
import com.example.pizzashift2025.shared.pizza.data.network.model.ToppingDto
import com.example.pizzashift2025.shared.pizza.domain.model.CatalogItem
import com.example.pizzashift2025.shared.pizza.domain.model.Dough
import com.example.pizzashift2025.shared.pizza.domain.model.Ingredient
import com.example.pizzashift2025.shared.pizza.domain.model.Size
import com.example.pizzashift2025.shared.pizza.domain.model.Topping
import com.example.pizzashift2025.util.Constants.BASE_URL
import javax.inject.Inject

class Converter @Inject constructor() {

    fun catalogItemDtoToDomain(model: CatalogItemDto): CatalogItem {
        return CatalogItem(
            id = model.id,
            name = model.name,
            ingredients = model.ingredients.map { ingredientDtoToDomain(it) },
            toppings = model.toppings.map { toppingsDtoToDomain(it) },
            description = model.description,
            sizes = model.sizes.map { sizesDtoToDomain(it) },
            doughs = model.doughs.map { doughDtoToDomain(it) },
            calories = model.calories,
            totalFat = model.totalFat,
            img = BASE_URL + model.img
        )
    }

    private fun ingredientDtoToDomain(model: IngredientDto): Ingredient {
        return Ingredient(
            name = model.name,
            cost = model.cost,
            img = model.img
        )
    }

    private fun toppingsDtoToDomain(model: ToppingDto): Topping {
        return Topping(
            name = model.name,
            cost = model.cost,
            img = model.img
        )
    }

    private fun sizesDtoToDomain(model: SizeDto): Size {
        return Size(
            name = model.name,
            price = model.price
        )
    }

    private fun doughDtoToDomain(model: DoughDto): Dough {
        return Dough(
            name = model.name,
            price = model.price
        )
    }
}