package com.example.shopcart.ui.theme.screens.products

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.shopcart.data.ProductViewModel
import com.example.shopcart.models.ProductModel
import com.example.shopcart.navigation.ROUTE_UPDATE_PRODUCT


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ViewProductScreen(
    navController: NavController,
    productId: String,
    viewModel: ProductViewModel
) {
    val product = remember { mutableStateOf<ProductModel?>(null) }

    LaunchedEffect(productId) {
        viewModel.getProductById(productId) { productData ->
            product.value = productData
        }
    }

    product.value?.let { product ->
        Scaffold(
            topBar = {
                TopAppBar(
                    title = { Text("View Product") },
                    navigationIcon = {
                        IconButton(onClick = { navController.popBackStack() }) {
                            Icon(Icons.Default.ArrowBack, contentDescription = "Back")
                        }
                    }
                )
            }
        ) { paddingValues ->
            Column(
                modifier = Modifier
                    .padding(paddingValues)
                    .fillMaxSize()
                    .padding(16.dp)
            ) {
                Text("Product Name: ${product.name}", fontSize = 22.sp, fontWeight = FontWeight.Bold)
                Spacer(modifier = Modifier.height(8.dp))
                Text("Category: ${product.category}")
                Spacer(modifier = Modifier.height(8.dp))
                Text("Price: KSh ${product.price}", color = Color.Red)
                Spacer(modifier = Modifier.height(8.dp))
                Text("Description: ${product.description}")
                Spacer(modifier = Modifier.height(16.dp))

                Button(
                    onClick = {
                        navController.navigate("$ROUTE_UPDATE_PRODUCT/${product.productId}")
                    },
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text("Edit Product")
                }
            }
        }
    }
}

