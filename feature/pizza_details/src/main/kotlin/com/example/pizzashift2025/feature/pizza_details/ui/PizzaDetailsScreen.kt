package com.example.pizzashift2025.feature.pizza_details.ui

import androidx.annotation.StringRes
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.PaddingValues
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
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
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
import com.example.pizzashift2025.util.Constants.RUBLE
import com.example.pizzashift2025.util.model.IngredientName
import com.example.pizzashift2025.util.model.ToppingName

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
    var selectedSize by remember { mutableStateOf(R.string.small) }
    var selectedDough by remember { mutableStateOf(R.string.thin) }
    Column {
        LazyVerticalGrid(
            columns = GridCells.Fixed(3),
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp)
                .weight(1f)
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
                    style = MaterialTheme.typography.titleLarge,
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
                        text = stringResource(id = selectedSize) + ",",
                        modifier = Modifier.padding(bottom = 4.dp),
                        style = MaterialTheme.typography.bodyMedium,
                    )
                    Text(
                        text = stringResource(id = selectedDough),
                        style = MaterialTheme.typography.bodyMedium,
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
                            style = MaterialTheme.typography.labelMedium,
                        )
                    }
                }
            }

            item(
                span = { GridItemSpan(maxCurrentLineSpan) }
            ) {
                Text(
                    text = stringResource(id = R.string.sizes),
                    modifier = Modifier.padding(bottom = 8.dp, top = 8.dp),
                    style = MaterialTheme.typography.bodyLarge,
                )
            }

            item(
                span = { GridItemSpan(maxCurrentLineSpan) }
            ) {
                PizzaSizeSelector(
                    selectedSize = { selectedSize = it },
                    currentSelectedSize = selectedSize
                )
            }

            item(
                span = { GridItemSpan(maxCurrentLineSpan) }
            ) {
                Text(
                    text = stringResource(id = R.string.dough),
                    modifier = Modifier.padding(bottom = 8.dp),
                    style = MaterialTheme.typography.bodyLarge,
                )
            }

            item(
                span = { GridItemSpan(maxCurrentLineSpan) }
            ) {
                PizzaDoughSelector(
                    selectedDough = { selectedDough = it },
                    currentSelectedDough = selectedDough
                )
            }

            item(
                span = { GridItemSpan(maxCurrentLineSpan) }
            ) {
                Text(
                    text = stringResource(id = R.string.add_to_taste),
                    modifier = Modifier.padding(bottom = 8.dp),
                    style = MaterialTheme.typography.bodyLarge,
                )
            }

            items(pizzaCatalog.toppings) { topping ->
                PizzaIngredientItem(topping = topping)
            }

            //костыльное решение
            item(
                span = { GridItemSpan(maxCurrentLineSpan) }
            ) {
                Spacer(modifier = Modifier.height(32.dp))
            }

            item(
                span = { GridItemSpan(maxCurrentLineSpan) }
            ) {
                Button(
                    onClick = { /*TODO*/ },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 16.dp),
                    colors = ButtonColors(
                        containerColor = MaterialTheme.colorScheme.secondary,
                        contentColor = MaterialTheme.colorScheme.primary,
                        disabledContentColor = MaterialTheme.colorScheme.primary,
                        disabledContainerColor = MaterialTheme.colorScheme.secondary
                    )
                ) {
                    Text(
                        text = stringResource(id = R.string.into_a_basket),
                        style = MaterialTheme.typography.labelLarge,
                    )
                }
            }
        }
    }
}

@Composable
private fun PizzaSizeSelector(
    selectedSize: (Int) -> Unit,
    currentSelectedSize: Int
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 16.dp, bottom = 16.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        PizzaSizeButton(
            textRes = R.string.small,
            isSelected = currentSelectedSize == R.string.small,
            onClick = { selectedSize(R.string.small) },
        )
        PizzaSizeButton(
            textRes = R.string.medium,
            isSelected = currentSelectedSize == R.string.medium,
            onClick = { selectedSize(R.string.medium) },
        )
        PizzaSizeButton(
            textRes = R.string.large,
            isSelected = currentSelectedSize == R.string.large,
            onClick = { selectedSize(R.string.large) },
        )
    }
}

@Composable
private fun PizzaSizeButton(
    @StringRes textRes: Int,
    isSelected: Boolean,
    onClick: () -> Unit
) {
    Button(
        onClick = onClick,
        modifier = Modifier
            .height(40.dp),
        colors = ButtonDefaults.buttonColors(
            containerColor = if (isSelected) MaterialTheme.colorScheme.primary else Color.LightGray
        ),
        contentPadding = PaddingValues(horizontal = 24.dp, vertical = 8.dp)
    ) {
        Text(
            text = stringResource(id = textRes),
            style = MaterialTheme.typography.labelSmall
        )
    }
}

@Composable
private fun PizzaDoughSelector(
    selectedDough: (Int) -> Unit,
    currentSelectedDough: Int
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 16.dp, bottom = 16.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        PizzaDoughButton(
            textRes = R.string.thin,
            isSelected = currentSelectedDough == R.string.thin,
            onClick = { selectedDough(R.string.thin) },
        )
        PizzaDoughButton(
            textRes = R.string.thick,
            isSelected = currentSelectedDough == R.string.thick,
            onClick = { selectedDough(R.string.thick) },
        )
    }
}

@Composable
private fun PizzaDoughButton(
    @StringRes textRes: Int,
    isSelected: Boolean,
    onClick: () -> Unit
) {
    Button(
        onClick = onClick,
        modifier = Modifier
            .height(40.dp)
            .width(100.dp),
        colors = ButtonDefaults.buttonColors(
            containerColor = if (isSelected) MaterialTheme.colorScheme.primary else Color.LightGray
        ),
        contentPadding = PaddingValues(horizontal = 24.dp, vertical = 8.dp)
    ) {
        Text(
            text = stringResource(id = textRes),
            style = MaterialTheme.typography.labelSmall
        )
    }
}

@Composable
private fun PizzaIngredientItem(topping: Topping) {
    var isSelected by remember { mutableStateOf(false) }
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(8.dp)),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Card(
            modifier = Modifier
                .padding(5.dp)
                .size(width = 120.dp, height = 240.dp)
                .clickable { isSelected = !isSelected},
            elevation = CardDefaults.cardElevation(
                defaultElevation = 6.dp
            ),
            colors = CardDefaults.cardColors(
                containerColor = if (isSelected) Color.LightGray else MaterialTheme.colorScheme.primary
            )
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(8.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.SpaceBetween
            ) {
                AsyncImage(
                    model = topping.img,
                    contentDescription = topping.name,
                    modifier = Modifier
                        .size(width = 100.dp, height = 160.dp)
                        .align(Alignment.CenterHorizontally)
                )
                Spacer(modifier = Modifier.weight(1f))
                val toppingName = ToppingName.fromString(topping.name)
                Text(
                    text = stringResource(id = toppingName.displayNameResId),
                    modifier = Modifier.padding(horizontal = 4.dp),
                    style = MaterialTheme.typography.labelMedium,
                    textAlign = TextAlign.Center
                )
                Text(
                    text = "${topping.cost} " + RUBLE,
                    style = MaterialTheme.typography.labelSmall,
                    textAlign = TextAlign.Center
                )
            }
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
            .padding(top = 16.dp, start = 16.dp)
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
            style = MaterialTheme.typography.titleLarge
        )
    }
}