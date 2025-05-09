package com.example.shopcart.ui.theme.screens.products

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.shopcart.data.ProductViewModel
import com.example.shopcart.models.ProductModel
import android.content.Context
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.text.input.KeyboardType

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun EditProductScreen(
    navController: NavController,
    productId: String,
    productViewModel: ProductViewModel
) {
    val context = LocalContext.current
    val product = remember { mutableStateOf<ProductModel?>(null) }

    // Load product by ID
    LaunchedEffect(productId) {
        productViewModel.getProductById(productId) {
            product.value = it
        }
    }

    product.value?.let { product ->
        var name by remember { mutableStateOf(product.name) }
        var category by remember { mutableStateOf(product.category) }
        var price by remember { mutableStateOf(product.price.toString()) }
        var description by remember { mutableStateOf(product.description) }

        Scaffold(
            topBar = {
                TopAppBar(
                    title = { Text("Edit Product") },
                    navigationIcon = {
                        IconButton(onClick = { navController.popBackStack() }) {
                            Icon(Icons.Default.ArrowBack, contentDescription = "Back")
                        }
                    }
                )
            }
        ) { padding ->
            Column(
                modifier = Modifier
                    .padding(padding)
                    .padding(16.dp)
                    .fillMaxSize(),
                verticalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                OutlinedTextField(value = name, onValueChange = { name = it }, label = { Text("Product Name") })
                OutlinedTextField(value = category, onValueChange = { category = it }, label = { Text("Category") })
                OutlinedTextField(value = price, onValueChange = { price = it }, label = { Text("Price") })
                OutlinedTextField(value = description, onValueChange = { description = it }, label = { Text("Description") })

                Button(onClick = {
                    productViewModel.updateProduct(
                        productId = product.productId,
                        name = name,
                        category = category,
                        price = price,
                        description = description,
                        context = context,
                        navController = navController
                    )
                }) {
                    Text("Update Product")
                }
            }
        }
    } ?: run {
        // Loading or fallback UI
        Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
            CircularProgressIndicator()
        }
    }
}
