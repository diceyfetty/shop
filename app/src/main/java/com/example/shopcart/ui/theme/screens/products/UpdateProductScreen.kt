//package com.example.shopcart.ui.theme.screens.products
//
//import android.widget.Toast
//import androidx.compose.foundation.layout.Column
//import androidx.compose.foundation.layout.Spacer
//import androidx.compose.foundation.layout.fillMaxSize
//import androidx.compose.foundation.layout.fillMaxWidth
//import androidx.compose.foundation.layout.height
//import androidx.compose.foundation.layout.padding
//import androidx.compose.foundation.rememberScrollState
//import androidx.compose.foundation.shape.RoundedCornerShape
//import androidx.compose.foundation.text.KeyboardOptions
//import androidx.compose.foundation.verticalScroll
//import androidx.compose.material3.Button
//import androidx.compose.material3.MaterialTheme
//import androidx.compose.material3.OutlinedTextField
//import androidx.compose.material3.Text
//import androidx.compose.runtime.Composable
//import androidx.compose.runtime.LaunchedEffect
//import androidx.compose.runtime.mutableStateOf
//import androidx.compose.runtime.remember
//import androidx.compose.ui.Alignment
//import androidx.compose.ui.Modifier
//import androidx.compose.ui.platform.LocalContext
//import androidx.compose.ui.text.input.KeyboardType
//import androidx.compose.ui.unit.dp
//import androidx.navigation.NavHostController
//import com.example.shopcart.data.ProductViewModel
//import com.example.shopcart.models.ProductModel
//
//@Composable
//fun UpdateProductScreen(
//    navController: NavHostController,
//    viewModel: ProductViewModel,
//    productId: String
//) {
//    val context = LocalContext.current
//
//    // State holders
//    val product = remember { mutableStateOf(ProductModel()) }
//    val name = remember { mutableStateOf("") }
//    val category = remember { mutableStateOf("") }
//    val price = remember { mutableStateOf("") }
//    val description = remember { mutableStateOf("") }
//
//    // Fetch product on load
//    LaunchedEffect(true) {
//        viewModel.fetchProductById(productId) { fetched ->
//            product.value = fetched
//            name.value = fetched.name
//            category.value = fetched.category
//            price.value = fetched.price.toString()
//            description.value = fetched.description
//        }
//    }
//
//    Column(
//        modifier = Modifier
//            .fillMaxSize()
//            .padding(20.dp)
//            .verticalScroll(rememberScrollState()),
//        horizontalAlignment = Alignment.CenterHorizontally
//    ) {
//        Text("Update Product", style = MaterialTheme.typography.headlineMedium)
//
//        Spacer(modifier = Modifier.height(20.dp))
//
//        OutlinedTextField(
//            value = name.value,
//            onValueChange = { name.value = it },
//            label = { Text("Product Name") },
//            modifier = Modifier.fillMaxWidth()
//        )
//
//        Spacer(modifier = Modifier.height(10.dp))
//
//        OutlinedTextField(
//            value = category.value,
//            onValueChange = { category.value = it },
//            label = { Text("Category") },
//            modifier = Modifier.fillMaxWidth()
//        )
//
//        Spacer(modifier = Modifier.height(10.dp))
//
//        OutlinedTextField(
//            value = price.value,
//            onValueChange = { price.value = it },
//            label = { Text("Price") },
//            keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Number),
//            modifier = Modifier.fillMaxWidth()
//        )
//
//        Spacer(modifier = Modifier.height(10.dp))
//
//        OutlinedTextField(
//            value = description.value,
//            onValueChange = { description.value = it },
//            label = { Text("Description") },
//            modifier = Modifier.fillMaxWidth()
//        )
//
//        Spacer(modifier = Modifier.height(20.dp))
//
//        Button(
//            onClick = {
//                if (name.value.isNotEmpty() && category.value.isNotEmpty() && price.value.isNotEmpty()) {
//                    val updatedProduct = ProductModel(
//                        name = name.value,
//                        category = category.value,
//                        price = price.value.toDoubleOrNull() ?: 0.0,
//                        description = description.value,
//                        productId = productId
//                    )
//                    viewModel.updateProduct(context, navController, updatedProduct)
//                } else {
//                    Toast.makeText(context, "All fields are required", Toast.LENGTH_SHORT).show()
//                }
//            },
//            modifier = Modifier.fillMaxWidth(),
//            shape = RoundedCornerShape(12.dp)
//        ) {
//            Text("UPDATE PRODUCT")
//        }
//    }
//}