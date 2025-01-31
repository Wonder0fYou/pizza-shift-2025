package com.example.pizzashift2025.feature.pizza_details.ui

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.GridItemSpan
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.Card
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
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import coil.compose.AsyncImage
import com.example.pizzashift2025.component.ui.ErrorComponent
import com.example.pizzashift2025.component.ui.LoadingComponent
import com.example.pizzashift2025.feature.pizza_details.R
import com.example.pizzashift2025.feature.pizza_details.presentation.PizzaDetailsState
import com.example.pizzashift2025.feature.pizza_details.presentation.PizzaDetailsViewModel
import com.example.pizzashift2025.shared.pizza.domain.model.CatalogItem
import com.example.pizzashift2025.shared.pizza.domain.model.Topping
import com.example.pizzashift2025.util.model.IngredientName

@Composable
fun PizzaDetailsScreen(
    viewModel: PizzaDetailsViewModel,
    pizzaId: String
) {
    val detailsState by viewModel.state.collectAsStateWithLifecycle()

    LaunchedEffect(Unit) {
        viewModel.loadPizza(pizzaId)
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
    ) {
        TopBar(
            viewModel = viewModel
        )
        when (val state = detailsState) {
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

@OptIn(ExperimentalLayoutApi::class)
@Composable
private fun PizzaDetailsContent(
    pizzaCatalog: CatalogItem
) {
    LazyVerticalGrid(
        columns = GridCells.Fixed(3),
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {

        item(
            span = {
                GridItemSpan(maxCurrentLineSpan)
            }
        ) {
            AsyncImage(
                model = pizzaCatalog.img,
                contentDescription = pizzaCatalog.name,
                modifier = Modifier
                    .size(250.dp)
                    .padding(bottom = 16.dp)
            )
        }

        item(
            span = {
                GridItemSpan(maxCurrentLineSpan)
            }
        ) {
            Text(
                text = pizzaCatalog.name,
                fontSize = 32.sp,
                modifier = Modifier.padding(bottom = 8.dp)
            )
        }

        item(
            span = { GridItemSpan(maxCurrentLineSpan) }
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
            ) {
                Text(
                    text = pizzaCatalog.sizes.first().name + ",",
                    modifier = Modifier.padding(bottom = 4.dp)
                )
                Text(
                    text = pizzaCatalog.doughs.first().name,
                )
            }
        }

        item(
            span = { GridItemSpan(maxCurrentLineSpan) }
        ) {
            FlowRow(
                modifier = Modifier
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(4.dp),
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                pizzaCatalog.ingredients.forEachIndexed() { index, ingredient ->
                    val ingredientName = IngredientName.fromString(ingredient.name)
                    var textToDisplay = stringResource(id = ingredientName.displayNameResId)
                    if (index < pizzaCatalog.ingredients.size - 1) {
                        textToDisplay += ", "
                    }
                    Text(
                        text = textToDisplay,
                    )
                }
            }
            Spacer(modifier = Modifier.height(16.dp))
        }

        item(
            span = { GridItemSpan(maxCurrentLineSpan) }
        ) {
            PizzaSizeSelector()
        }

        item(
            span = { GridItemSpan(maxCurrentLineSpan) }
        ) {
            Text(
                text = stringResource(id = R.string.add_to_taste),
                modifier = Modifier.padding(bottom = 8.dp)
            )
        }

        items(pizzaCatalog.toppings) { topping ->
            PizzaIngredientItem(topping = topping)
        }

        item(
            span = { GridItemSpan(maxCurrentLineSpan) }
        ) {
            Button(
                onClick = { /*TODO*/ },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 16.dp)
            ) {
                Text(
                    text = stringResource(id = R.string.into_a_basket)
                )
            }
        }
    }
}

@Composable
private fun PizzaSizeSelector() {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceAround
    ) {
        Button(
            onClick = { /*TODO*/ },

            ) {
            Text(text = stringResource(id = R.string.small))
        }
        Button(
            onClick = { /*TODO*/ },
        ) {
            Text(text = stringResource(id = R.string.medium))
        }
        Button(
            onClick = { /*TODO*/ },

            ) {
            Text(text = stringResource(id = R.string.large))
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
        Card(
            modifier = Modifier
                .clickable { }
        ) {

            AsyncImage(
                model = topping.img,
                contentDescription = topping.name,
                modifier = Modifier
                    .size(100.dp)
                    .padding(bottom = 4.dp)
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
}

@Composable
private fun TopBar(
    viewModel: PizzaDetailsViewModel
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 32.dp, start = 24.dp)
    ) {
        Icon(
            modifier = Modifier
                .padding(top = 2.dp, end = 24.dp)
                .size(32.dp)
                .clickable { viewModel.goBack() },
            imageVector = Icons.Default.ArrowBack,
            contentDescription = ""
        )
        Text(
            text = stringResource(id = R.string.pizza),
            fontSize = 32.sp
        )
    }
}