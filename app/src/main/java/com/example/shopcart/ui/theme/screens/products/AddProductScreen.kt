//package com.example.shopcart.ui.theme.screens.products
//
//import com.example.shopcart.data.ProductViewModel
//import android.widget.Toast
//import androidx.compose.foundation.layout.*
//import androidx.compose.material3.*
//import androidx.compose.runtime.*
//import androidx.compose.ui.Modifier
//import androidx.compose.ui.platform.LocalContext
//import androidx.compose.ui.unit.dp
//import androidx.navigation.NavController
//import androidx.navigation.compose.rememberNavController
//
//@OptIn(ExperimentalMaterial3Api::class)
//@Composable
//fun AddProductScreen(navController: NavController, productViewModel: ProductViewModel) {
//    var name by remember { mutableStateOf("") }
//    var category by remember { mutableStateOf("") }
//    var price by remember { mutableStateOf("") }
//    var description by remember { mutableStateOf("") }
//    val context = LocalContext.current
//
//    Scaffold(
//        topBar = {
//            TopAppBar(title = { Text("Add Product") })
//        }
//    ) { padding ->
//        Column(
//            modifier = Modifier
//                .padding(padding)
//                .padding(16.dp)
//                .fillMaxSize(),
//            verticalArrangement = Arrangement.spacedBy(12.dp)
//        ) {
//            OutlinedTextField(value = name, onValueChange = { name = it }, label = { Text("Product Name") })
//            OutlinedTextField(value = category, onValueChange = { category = it }, label = { Text("Category") })
//            OutlinedTextField(value = price, onValueChange = { price = it }, label = { Text("Price") })
//            OutlinedTextField(value = description, onValueChange = { description = it }, label = { Text("Description") })
//
//            Button(onClick = {
//                if (name.isNotBlank() && category.isNotBlank() && price.isNotBlank() && description.isNotBlank()) {
//                    productViewModel.addProduct(name, category, price, description, context, navController)
//                } else {
//                    Toast.makeText(context, "Please fill all fields", Toast.LENGTH_SHORT).show()
//                }
//            }) {
//                Text("Save Product")
//            }
//        }
//    }
//}