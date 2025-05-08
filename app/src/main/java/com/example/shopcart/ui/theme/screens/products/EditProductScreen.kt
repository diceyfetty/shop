package com.example.shopcart.ui.theme.screens.products

import android.widget.Toast
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.shopcart.data.ProductViewModel
import com.example.shopcart.navigation.ROUTE_VIEW_PRODUCTS

@Composable
fun EditProductScreen(
    navController: NavController,
    productId: String,
    existingName: String,
    existingCategory: String,
    existingPrice: String,
    existingDescription: String
) {
    val context = LocalContext.current
    val productViewModel: ProductViewModel = viewModel()

    var name by remember { mutableStateOf(existingName) }
    var category by remember { mutableStateOf(existingCategory) }
    var price by remember { mutableStateOf(existingPrice) }
    var description by remember { mutableStateOf(existingDescription) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(20.dp)
            .verticalScroll(rememberScrollState()),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text("Edit Product", fontSize = 28.sp, color = Color.Black)
        Spacer(modifier = Modifier.height(20.dp))

        OutlinedTextField(
            value = name,
            onValueChange = { name = it },
            label = { Text("Product Name") },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(12.dp))

        OutlinedTextField(
            value = category,
            onValueChange = { category = it },
            label = { Text("Category") },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(12.dp))

        OutlinedTextField(
            value = price,
            onValueChange = { price = it },
            label = { Text("Price") },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(12.dp))

        OutlinedTextField(
            value = description,
            onValueChange = { description = it },
            label = { Text("Description") },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(20.dp))

        Button(
            onClick = {
                if (name.isBlank() || category.isBlank() || price.isBlank() || description.isBlank()) {
                    Toast.makeText(context, "Please fill all fields", Toast.LENGTH_SHORT).show()
                } else {
                    productViewModel.updateProduct(
                        context = context,
                        navController = navController,
                        name = name,
                        category = category,
                        price = price,
                        description = description,
                        productId = productId
                    )
                }
            },
            modifier = Modifier.fillMaxWidth(),
            colors = ButtonDefaults.buttonColors(Color.Black)
        ) {
            Text("Update Product")
        }

        Spacer(modifier = Modifier.height(12.dp))

        Text(
            text = "Cancel",
            color = Color.Red,
            modifier = Modifier.clickable {
                navController.navigate(ROUTE_VIEW_PRODUCTS)
            }
        )
    }
}