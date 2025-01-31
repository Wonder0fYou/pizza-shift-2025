package com.example.pizzashift2025.feature.pizza_details.ui

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import coil.compose.AsyncImage
import com.example.pizzashift2025.component.ui.ErrorComponent
import com.example.pizzashift2025.component.ui.LoadingComponent
import com.example.pizzashift2025.feature.pizza_details.R
import com.example.pizzashift2025.feature.pizza_details.presentation.PizzaDetailsState
import com.example.pizzashift2025.feature.pizza_details.presentation.PizzaDetailsViewModel
import com.example.pizzashift2025.shared.pizza.domain.model.CatalogItem
import com.example.pizzashift2025.shared.pizza.domain.model.Topping

@Composable
fun PizzaDetailsScreen(
    viewModel: PizzaDetailsViewModel,
    pizzaId: String
) {
    val detailsState by viewModel.state.collectAsStateWithLifecycle()

    LaunchedEffect(Unit) {
        viewModel.loadPizza(pizzaId)
    }
    
    Column (modifier = Modifier.fillMaxSize()){
        Row(
            verticalAlignment = Alignment.CenterVertically
        ){
            Icon(
                modifier = Modifier
                    .padding(start = 8.dp)
                    .clickable { viewModel.goBack() },
                imageVector = Icons.Default.ArrowBack,
                contentDescription = ""
            )
            Text(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 8.dp, vertical = 12.dp),
                text = stringResource(id = R.string.pizza)
            )
        }
        when(val state = detailsState){
            PizzaDetailsState.Initial,
            PizzaDetailsState.Loading -> LoadingComponent()

            is PizzaDetailsState.Failure -> ErrorComponent(
                message = state.message ?: stringResource(id = R.string.error_unknown_error),
                onRetry = { viewModel.loadPizza(pizzaId) }
            )

            is PizzaDetailsState.Content -> PizzaDetailsContent(
                pizzaCatalog = state.pizza
            )
        }
    }
}

@Composable
private fun PizzaDetailsContent(
    pizzaCatalog: CatalogItem
) {
    Column (
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        AsyncImage(
            model = pizzaCatalog.img,
            contentDescription = pizzaCatalog.name,
            modifier = Modifier
                .size(300.dp)
                .padding(bottom = 16.dp)
        )

        Text(
            text = pizzaCatalog.name,
            modifier = Modifier.padding(bottom = 8.dp)
        )

        Text(
            text = pizzaCatalog.sizes.first().name,
            modifier = Modifier.padding(bottom = 4.dp)
        )

        Text(
            text = pizzaCatalog.ingredients.joinToString { it.name },
            modifier = Modifier.padding(bottom = 16.dp)
        )

        PizzaSizeSelector()

        Text(
            text = stringResource(id = R.string.add_to_taste),
            modifier = Modifier.padding(bottom = 8.dp)
        )

        PizzaIngredientsGrid(toppings = pizzaCatalog.toppings)
    }
}

@Composable
private fun PizzaSizeSelector() {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceAround
    ) {
        Button(onClick = { /*TODO*/ },

        ) {
            Text(text = stringResource(id = R.string.small))
        }
        Button(onClick = { /*TODO*/ },
        ) {
            Text(text = stringResource(id = R.string.medium))
        }
        Button(onClick = { /*TODO*/ },

        ) {
            Text(text = stringResource(id = R.string.large))
        }
    }
}

@Composable
private fun PizzaIngredientsGrid(toppings: List<Topping>) {
    LazyVerticalGrid(
        columns = GridCells.Fixed(3),
        horizontalArrangement = Arrangement.spacedBy(8.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        items(toppings) { topping ->
            PizzaIngredientItem(topping = topping)
        }
    }
}

@Composable
private fun PizzaIngredientItem(topping: Topping) {
    Column(
        modifier = Modifier
            .width(100.dp)
            .clip(RoundedCornerShape(8.dp)),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        AsyncImage(
            model = topping.img,
            contentDescription = topping.name,
            modifier = Modifier.padding(bottom = 4.dp)
        )

        Text(
            text = topping.name,
            modifier = Modifier.padding(horizontal = 4.dp)
        )

        Text(
            text = "${topping.cost} P",
        )

    }
}