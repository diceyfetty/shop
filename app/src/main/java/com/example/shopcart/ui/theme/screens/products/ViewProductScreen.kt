//package com.example.shopcart.ui.theme.screens.products
//
//import androidx.compose.foundation.layout.Column
//import androidx.compose.foundation.layout.fillMaxSize
//import androidx.compose.foundation.layout.padding
//import androidx.compose.foundation.lazy.LazyColumn
//import androidx.compose.foundation.shape.RoundedCornerShape
//import androidx.compose.material3.Button
//import androidx.compose.material3.ButtonDefaults
//import androidx.compose.material3.Text
//import androidx.compose.runtime.Composable
//import androidx.compose.runtime.mutableStateListOf
//import androidx.compose.runtime.mutableStateOf
//import androidx.compose.runtime.remember
//import androidx.compose.ui.Alignment
//import androidx.compose.ui.Modifier
//import androidx.compose.ui.graphics.Color
//import androidx.compose.ui.platform.LocalContext
//import androidx.compose.ui.text.font.FontWeight
//import androidx.compose.ui.unit.dp
//import androidx.compose.ui.unit.sp
//import androidx.navigation.NavHostController
//import com.example.shopcart.data.ProductViewModel
//import com.example.shopcart.models.ProductModel
//import com.example.shopcart.navigation.ROUTE_UPDATE_PRODUCT
//
//@Composable
//fun ViewProductsScreen(navController: NavHostController, viewModel: ProductViewModel) {
//    val context = LocalContext.current
//
//    val currentProduct = remember { mutableStateOf(ProductModel()) }
//    val productList = remember { mutableStateListOf<ProductModel>() }
//
//    val products = viewModel.viewProducts(currentProduct, productList, context)
//
//    Column(
//        modifier = Modifier.fillMaxSize(),
//        horizontalAlignment = Alignment.CenterHorizontally
//    ) {
//        Text(
//            text = "All Products",
//            fontSize = 26.sp,
//            fontWeight = FontWeight.Bold,
//            modifier = Modifier.padding(16.dp)
//        )
//
//        LazyColumn {
//            items(products) { product ->
//                ProductItem(
//                    product = product,
//                    navController = navController,
//                    viewModel = viewModel
//                )
//            }
//        }
//    }
//    Button (
//        onClick = {
//            navController.navigate("$ROUTE_UPDATE_PRODUCT/${product.productId}")
//        },
//        shape = RoundedCornerShape(10.dp),
//        colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF4CAF50))
//    ) {
//        Text("UPDATE", color = Color.White)
//    }
//}