package com.example.shopcart.ui.theme.screens.login

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.*
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.*
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.*
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.shopcart.R
import com.example.shopcart.data.AuthViewModel
import com.example.shopcart.navigation.ROUTE_REGISTER
import com.example.shopcart.ui.theme.screens.register.RoundedInputField
import com.example.shopcart.ui.theme.screens.register.RoundedPasswordField

@Composable
fun LoginScreen(navController: NavController) {
    val authViewModel: AuthViewModel = viewModel()
    val context = LocalContext.current

    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var passwordVisible by remember { mutableStateOf(false) }

    Surface (
        modifier = Modifier.fillMaxSize(),
        color = Color(0xFFF2F2F2)
    ) {
        Column(
            verticalArrangement = Arrangement.Center,
            modifier = Modifier
                .padding(20.dp)
                .fillMaxSize()
        ) {
            Text(
                text = "Welcome Back!",
                fontSize = 30.sp,
                fontWeight = FontWeight.Bold,
                color = Color(0xFF1E88E5),
                modifier = Modifier.fillMaxWidth(),
                textAlign = TextAlign.Center
            )

            Spacer(modifier = Modifier.height(16.dp))

            Image(
                painter = painterResource(id = R.drawable.logo),
                contentDescription = "App Logo",
                modifier = Modifier
                    .height(100.dp)
                    .fillMaxWidth()
            )

            Spacer(modifier = Modifier.height(16.dp))

            RoundedInputField(
                value = email,
                onValueChange = { email = it },
                label = "Email",
                icon = Icons.Default.Email,
                keyboardType = KeyboardType.Email
            )

            Spacer(modifier = Modifier.height(12.dp))

            RoundedPasswordField(
                value = password,
                onValueChange = { password = it },
                label = "Password",
                visible = passwordVisible,
                toggleVisibility = { passwordVisible = !passwordVisible }
            )

            Spacer(modifier = Modifier.height(24.dp))

            Button (
                onClick = {
                    authViewModel.login(email, password, navController, context)
                },
                colors = ButtonDefaults.buttonColors(Color(0xFF1E88E5)),
                shape = RoundedCornerShape(16.dp),
                modifier = Modifier
                    .fillMaxWidth()
                    .height(50.dp)
            ) {
                Text("Login", color = Color.White, fontSize = 16.sp)
            }

            Spacer(modifier = Modifier.height(16.dp))

            Text(
                text = "Don't have an account? Register",
                color = Color(0xFF1E88E5),
                modifier = Modifier
                    .align(Alignment.CenterHorizontally)
                    .clickable { navController.navigate(ROUTE_REGISTER) },
                fontSize = 14.sp
            )
        }
    }
}