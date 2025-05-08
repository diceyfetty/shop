//package com.example.shopcart.ui.theme.screens.products
//
//import androidx.compose.foundation.layout.Arrangement
//import androidx.compose.foundation.layout.Column
//import androidx.compose.foundation.layout.Row
//import androidx.compose.foundation.layout.Spacer
//import androidx.compose.foundation.layout.fillMaxWidth
//import androidx.compose.foundation.layout.height
//import androidx.compose.foundation.layout.padding
//import androidx.compose.foundation.shape.RoundedCornerShape
//import androidx.compose.material3.Button
//import androidx.compose.material3.ButtonDefaults
//import androidx.compose.material3.Card
//import androidx.compose.material3.CardDefaults
//import androidx.compose.material3.MaterialTheme
//import androidx.compose.material3.Text
//import androidx.compose.runtime.Composable
//import androidx.compose.ui.Modifier
//import androidx.compose.ui.platform.LocalContext
//import androidx.compose.ui.text.font.FontWeight
//import androidx.compose.ui.text.style.TextOverflow
//import androidx.compose.ui.unit.dp
//import androidx.navigation.NavHostController
//import com.example.shopcart.data.ProductViewModel
//import com.example.shopcart.models.ProductModel
//import com.example.shopcart.navigation.ROUTE_UPDATE_PRODUCT
//
//@Composable
//fun ProductItem(
//    product: ProductModel,
//    navController: NavHostController,
//    viewModel: ProductViewModel
//) {
//    val context = LocalContext.current
//
//    Card(
//        modifier = Modifier
//            .fillMaxWidth()
//            .padding(10.dp),
//        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surfaceContainer),
//        shape = RoundedCornerShape(16.dp),
//        elevation = CardDefaults.cardElevation(defaultElevation = 6.dp)
//    ) {
//        Row (modifier = Modifier.padding(10.dp)) {
//            Column(modifier = Modifier.weight(1f)) {
//                Text(
//                    text = product.name,
//                    style = MaterialTheme.typography.titleLarge,
//                    fontWeight = FontWeight.Bold
//                )
//                Text(
//                    text = "Category: ${product.category}",
//                    style = MaterialTheme.typography.bodyMedium
//                )
//                Text(
//                    text = "Price: $${product.price}",
//                    style = MaterialTheme.typography.bodyMedium
//                )
//                Spacer(modifier = Modifier.height(6.dp))
//                Text(
//                    text = product.description,
//                    style = MaterialTheme.typography.bodySmall,
//                    maxLines = 2,
//                    overflow = TextOverflow.Ellipsis
//                )
//                Spacer(modifier = Modifier.height(10.dp))
//
//                Row(
//                    horizontalArrangement = Arrangement.spacedBy(8.dp)
//                ) {
//                    Button(
//                        onClick = {
//                            viewModel.deleteProduct(context, product.productId)
//                        },
//                        colors = ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.error),
//                        shape = RoundedCornerShape(12.dp)
//                    ) {
//                        Text("DELETE")
//                    }
//
//                    Button(
//                        onClick = {
//                            navController.navigate("$ROUTE_UPDATE_PRODUCT/${product.productId}")
//                        },
//                        colors = ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.primary),
//                        shape = RoundedCornerShape(12.dp)
//                    ) {
//                        Text("UPDATE")
//                    }
//                }
//            }
//        }
//    }
//}