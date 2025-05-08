package com.example.shopcart.ui.theme

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.shopcart.R

@Composable
fun SplashScreen(onNavigateToNext: () -> Unit){
    val splashScreenDuration = 3000L
    LaunchedEffect(Unit) {
        kotlinx.coroutines.delay(splashScreenDuration)
        onNavigateToNext()
    }
    Box(modifier = Modifier
        .fillMaxSize()
        .background(Color.Black),
        contentAlignment = Alignment.Center){
        Column (horizontalAlignment = Alignment.CenterHorizontally){
            Spacer(modifier = Modifier.height(70.dp))
            Image(painter = painterResource(id = R.drawable.logo),
                contentDescription = "App Logo",
                modifier = Modifier.size(250.dp))
            Spacer(modifier = Modifier.height(300.dp))
            Text(text = "QuickCart",
                fontFamily = FontFamily.SansSerif,
                fontWeight = FontWeight.Bold,
                fontSize = 30.sp,
                color = Color.Red
            )
            Spacer(modifier = Modifier.height(5.dp))
            Text(text = "Shop smart, Live Easy!",
                fontFamily = FontFamily.SansSerif,
                fontWeight = FontWeight.Bold,
                fontSize = 30.sp,
                color = Color.White
            )
        }
    }
}
@Preview(showBackground = true, showSystemUi = true)
@Composable
fun SplashScreenPreview(){
    SplashScreen {  }
}