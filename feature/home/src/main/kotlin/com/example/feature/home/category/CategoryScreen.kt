package com.example.feature.home.category

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import com.arkivanov.decompose.router.stack.push
import com.example.android.core.model.Category
import com.example.android.core.model.Product
import com.example.core.design_system.SystemBar
import core.common.NavConfig
import core.common.navigation
import core.component_base.LoadUIState
import kotlinx.collections.immutable.PersistentList
import kotlinx.collections.immutable.toPersistentList

@OptIn(
    ExperimentalMaterial3Api::class, ExperimentalFoundationApi::class,
    ExperimentalLayoutApi::class
)
@Composable
fun CategoryScreen(modifier: Modifier = Modifier, component: CategoryComponent) {
    SystemBar(MaterialTheme.colorScheme.background)
    Scaffold(
        modifier = modifier,
        topBar = { TopAppBar(title = { Text(text = "分类") }, windowInsets = WindowInsets(0.dp)) },
        contentWindowInsets = WindowInsets(0.dp)
    ) { padding ->
        val loadCategoriesUIState by component.modelState.loadCategoriesUIStateFlow.collectAsState()
        val categories = when (loadCategoriesUIState) {
            is LoadUIState.Success -> {
                (loadCategoriesUIState as LoadUIState.Success<List<Category>>).data
            }

            else -> {
                emptyList()
            }
        }
        val loadProductsUIState by component.modelState.loadProductsUIStateFlow.collectAsState()
        Row(
            Modifier
                .padding(padding)
                .fillMaxSize()
        ) {
            LazyColumn(
                modifier = Modifier
                    .padding(bottom = 10.dp)
                    .clip(RoundedCornerShape(topEnd = 10.dp, bottomEnd = 10.dp))
                    .width(60.dp)
                    .fillMaxHeight()
                    .background(MaterialTheme.colorScheme.primaryContainer)
            ) {
                categories.forEach {
                    val isSelected = it.id == component.modelState.selectedCategoryId
                    if (isSelected) {
                        stickyHeader(key = it.id) {
                            CategorySortItem(
                                modifier = Modifier.fillMaxSize(),
                                selected = true,
                                title = it.name,
                                onClick = { component.modelState.selectedCategoryId = it.id })
                        }
                    } else {
                        item(key = it.id) {
                            CategorySortItem(
                                modifier = Modifier.fillMaxSize(),
                                selected = false,
                                title = it.name,
                                onClick = { component.modelState.selectedCategoryId = it.id })
                        }
                    }
                }
            }
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(PaddingValues(10.dp, 0.dp, 0.dp, 10.dp))
                    .clip(RoundedCornerShape(topStart = 10.dp, bottomStart = 10.dp))
                    .fillMaxSize()
                    .background(MaterialTheme.colorScheme.secondaryContainer)
            ) {
                when (loadProductsUIState) {
                    is LoadUIState.Error -> {}
                    LoadUIState.Loading -> {
                        CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
                    }

                    is LoadUIState.Success -> {
                        Products(
                            products = (loadProductsUIState as LoadUIState.Success<List<Product>>).data.toPersistentList(),
                            onProductClick = { navigation.push(NavConfig.ProductDetail(it.id)) }
                        )
                    }
                }
            }
        }
    }
}

@Composable
private fun Products(
    modifier: Modifier = Modifier,
    products: PersistentList<Product>,
    onProductClick: (Product) -> Unit
) {
    LazyVerticalGrid(
        columns = GridCells.Fixed(3),
        modifier = modifier
            .fillMaxSize()
    ) {
        items(
            items = products,
            key = { it.id }) {
            ProductItemSimple(
                product = it,
                onClick = { onProductClick(it) },
                modifier = Modifier.height(140.dp),
                contentPadding = PaddingValues(5.dp)
            )
        }
    }
}