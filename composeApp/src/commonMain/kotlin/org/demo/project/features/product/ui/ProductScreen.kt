package org.demo.project.features.product.ui
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import app.cash.paging.compose.collectAsLazyPagingItems
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberUpdatedState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController

import org.koin.compose.getKoin


@Composable
fun ProductScreen(navController: NavHostController) {
    val viewModel: ProductViewModel = getKoin().get()
    val result by rememberUpdatedState(viewModel.products.collectAsLazyPagingItems())
    Column(
        horizontalAlignment = Alignment.CenterHorizontally
    ){
        Button(onClick = {
            navController.navigateUp()
        }) {
            Text(text = "Back")
        }
        Text(
            "Product Screen",
            style = MaterialTheme.typography.titleLarge,
            modifier = Modifier.padding(vertical = 20.dp)
        )
        PagingGrid(data = result, content = { ProductCard(it) })
    }
}

