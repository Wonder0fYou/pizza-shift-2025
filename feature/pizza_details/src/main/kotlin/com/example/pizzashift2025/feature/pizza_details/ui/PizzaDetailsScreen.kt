package com.example.pizzashift2025.feature.pizza_details.ui

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
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
    }
}