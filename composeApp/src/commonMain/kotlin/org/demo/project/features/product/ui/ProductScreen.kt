package org.demo.project.features.product.ui
import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.runtime.Composable
import app.cash.paging.compose.collectAsLazyPagingItems
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberUpdatedState
import androidx.compose.ui.Alignment
import androidx.compose.ui.graphics.Color

import org.koin.compose.getKoin


@Composable
fun ProductScreen() {
    val viewModel: ProductViewModel = getKoin().get()
    val result by rememberUpdatedState(viewModel.products.collectAsLazyPagingItems())
    Column(
        horizontalAlignment = Alignment.CenterHorizontally
    ){
        PagingGrid(data = result, content = { ProductCard(it) })
    }
}

