package org.demo.project.features.product.ui;
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import org.demo.project.core.domain.onError
import org.demo.project.core.domain.onSuccess
import org.demo.project.features.product.data.ProductRepositoryImp
import org.demo.project.features.product.domain.ProductRepository
import androidx.lifecycle.viewModelScope
import app.cash.paging.PagingData
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch
import org.demo.project.features.product.domain.Products

class ProductViewModel(
    private val productRepository: ProductRepository
): ViewModel() {

    var products = productRepository.getProducts()

}