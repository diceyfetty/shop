package com.example.shopcart.ui.theme.screens.register

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.*
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.*
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.*
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.shopcart.R
import com.example.shopcart.data.AuthViewModel
import com.example.shopcart.navigation.ROUTE_LOGIN


@Composable
fun RegisterScreen(navController: NavController) {
    val authViewModel: AuthViewModel = viewModel()
    val context = LocalContext.current

    var firstname by remember { mutableStateOf("") }
    var lastname by remember { mutableStateOf("") }
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
                text = "Create Account",
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
                value = firstname,
                onValueChange = { firstname = it },
                label = "First Name",
                icon = Icons.Default.Person
            )

            Spacer(modifier = Modifier.height(12.dp))

            RoundedInputField(
                value = lastname,
                onValueChange = { lastname = it },
                label = "Last Name",
                icon = Icons.Default.Person
            )

            Spacer(modifier = Modifier.height(12.dp))

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

            Button(
                onClick = {
                    authViewModel.signup(
                        firstname,
                        lastname,
                        email,
                        password,
                        navController,
                        context
                    )
                },
                colors = ButtonDefaults.buttonColors(Color(0xFF1E88E5)),
                shape = RoundedCornerShape(16.dp),
                modifier = Modifier
                    .fillMaxWidth()
                    .height(50.dp)
            ) {
                Text("Register", color = Color.White, fontSize = 16.sp)
            }

            Spacer(modifier = Modifier.height(16.dp))

            Text(
                text = "Already have an account? Login",
                color = Color(0xFF1E88E5),
                modifier = Modifier
                    .align(Alignment.CenterHorizontally)
                    .clickable { navController.navigate(ROUTE_LOGIN) },
                fontSize = 14.sp
            )
        }
    }
}

@Composable
fun RoundedInputField(
    value: String,
    onValueChange: (String) -> Unit,
    label: String,
    icon: ImageVector,
    keyboardType: KeyboardType = KeyboardType.Text
) {
    OutlinedTextField(
        value = value,
        onValueChange = onValueChange,
        label = { Text(label) },
        leadingIcon = { Icon(icon, contentDescription = null) },
        keyboardOptions = KeyboardOptions(keyboardType = keyboardType),
        singleLine = true,
        shape = RoundedCornerShape(12.dp),
        modifier = Modifier.fillMaxWidth()
    )
}

@Composable
fun RoundedPasswordField(
    value: String,
    onValueChange: (String) -> Unit,
    label: String,
    visible: Boolean,
    toggleVisibility: () -> Unit
) {
    OutlinedTextField(
        value = value,
        onValueChange = onValueChange,
        label = { Text(label) },
        leadingIcon = { Icon(Icons.Default.Lock, contentDescription = null) },
        trailingIcon = {
            IconButton(onClick = toggleVisibility) {
                Icon(
                    imageVector = if (visible) Icons.Default.VisibilityOff else Icons.Default.Visibility,
                    contentDescription = "Toggle password"
                )
            }
        },
        visualTransformation = if (visible) VisualTransformation.None else PasswordVisualTransformation(),
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
        singleLine = true,
        shape = RoundedCornerShape(12.dp),
        modifier = Modifier.fillMaxWidth()
    )
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun RegisterScreenPreview() {
    RegisterScreen(rememberNavController())
}