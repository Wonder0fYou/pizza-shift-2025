package com.example.pizzashift2025.feature.pizza_main_list.pizza_main_list.ui

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import coil.compose.AsyncImage
import com.example.pizzashift2025.component.ui.ErrorComponent
import com.example.pizzashift2025.component.ui.LoadingComponent
import com.example.pizzashift2025.feature.pizza_main_list.R
import com.example.pizzashift2025.shared.pizza.domain.model.CatalogItem
import com.example.pizzashift2025.feature.pizza_main_list.pizza_main_list.presentation.PizzaListMainState
import com.example.pizzashift2025.feature.pizza_main_list.pizza_main_list.presentation.PizzaListMainViewModel
import com.example.pizzashift2025.util.Constants.RUBLE

@Composable
fun PizzaListScreen(
    viewModel: PizzaListMainViewModel
) {
    val listState by viewModel.state.collectAsStateWithLifecycle()
    LaunchedEffect(Unit) {
        viewModel.loadPizza()
    }
    Column (
        modifier = Modifier.fillMaxSize()
    ){
        TopBar()
        when(val state = listState) {
            PizzaListMainState.Initial,
            PizzaListMainState.Loading -> LoadingComponent()

            is PizzaListMainState.Failure -> ErrorComponent(
                message = state.message ?: stringResource(id = R.string.error_unknown_error),
                onRetry = { viewModel.loadPizza() }
            )

            is PizzaListMainState.Content -> ContentComponent(
                catalogs = state.catalog,
                onItemClicked = viewModel::openPizza
            )
        }
    }
}

@Composable
private fun ContentComponent(
    catalogs: List<CatalogItem>?,
    onItemClicked: (id: String) -> Unit
) {
    if (catalogs == null) {
        Text(
            text = stringResource(id = R.string.pizza_catalog_empty),
            style = MaterialTheme.typography.titleLarge
        )
        return
    }

    LazyColumn(
        contentPadding = PaddingValues(12.dp)
    ) {
        items(catalogs) { pizzaItem ->
            pizzaItem.let {
                PizzaItemCard(
                    pizza = pizzaItem,
                    onItemClicked = onItemClicked
                ) 
                Spacer(modifier = Modifier.height(16.dp))
            }
        }
    }
}

@Composable
private fun PizzaItemCard(
    pizza: CatalogItem,
    onItemClicked: (id: String) -> Unit
) {
    Row (
        modifier = Modifier
            .fillMaxWidth()
            .clickable {
                onItemClicked(pizza.id)
            }
            .padding(8.dp)
    ){
        AsyncImage(
            model = pizza.img,
            contentDescription = pizza.name,
            modifier = Modifier.size(120.dp)
        )
        Spacer(modifier = Modifier.width(16.dp))
        Column (
            modifier = Modifier
                .fillMaxHeight(),
            verticalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                text = pizza.name,
                style = MaterialTheme.typography.bodyLarge,
                modifier = Modifier
                    .padding(bottom = 8.dp)
            )
            Text(
                text = pizza.description,
                style = MaterialTheme.typography.bodyMedium,
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = stringResource(id = R.string.from) + "${pizza.sizes[0].price} " + RUBLE,
                style = MaterialTheme.typography.labelSmall
            )
        }
    }
}

@Composable
private fun TopBar() {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 16.dp, start = 24.dp)
    ) {
        Text(
            text = stringResource(id = R.string.pizza),
            style = MaterialTheme.typography.titleLarge
        )
    }
}