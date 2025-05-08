package com.example.shopcart.ui.theme.screens.home

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.shopcart.navigation.ROUTE_ADD_PRODUCT
import com.example.shopcart.navigation.ROUTE_LOGIN
import com.example.shopcart.navigation.ROUTE_VIEW_PRODUCTS

@Composable
fun DashboardScreen(navController: NavHostController) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "QuickCart Dashboard",
            fontSize = 28.sp,
            fontWeight = FontWeight.Bold,
            color = MaterialTheme.colorScheme.primary
        )

        Spacer(modifier = Modifier.height(24.dp))

        DashboardButton("Add Product") {
            navController.navigate(ROUTE_ADD_PRODUCT)
        }

        DashboardButton("View Products") {
            navController.navigate(ROUTE_VIEW_PRODUCTS)
        }

        DashboardButton("Profile") {
            // Example: navController.navigate(ROUTE_PROFILE)
        }

        DashboardButton("Settings") {
            // Example: navController.navigate(ROUTE_SETTINGS)
        }

        DashboardButton("Logout") {
            navController.navigate(ROUTE_LOGIN) // or handle actual Firebase logout
        }
    }
}

@Composable
fun DashboardButton(label: String, onClick: () -> Unit) {
    Button (
        onClick = onClick,
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp),
        shape = RoundedCornerShape(12.dp),
        colors = ButtonDefaults.buttonColors(
            containerColor = MaterialTheme.colorScheme.primaryContainer,
            contentColor = MaterialTheme.colorScheme.onPrimaryContainer
        )
    ) {
        Text(
            text = label,
            fontSize = 18.sp,
            fontWeight = FontWeight.Medium
        )
    }
}