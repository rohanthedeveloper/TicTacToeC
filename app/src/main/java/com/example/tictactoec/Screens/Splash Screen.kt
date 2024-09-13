package com.example.tictactoec.Screens

import android.window.SplashScreen
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.tictactoec.Navigation.Screens
import com.example.tictactoec.ui.theme.Montserrat
import com.example.tictactoec.ui.theme.Pacifico
import kotlinx.coroutines.delay

@Composable
fun SplashScreen(navController: NavController) {
    LaunchedEffect(key1 = true) {
        delay(2000L)
        navController.navigate(Screens.HomeScreen.name)
    }
    Splash()
}

@Composable
fun Splash() {
    Surface {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(brush = Brush.verticalGradient(
                    colors = listOf(
                        Color(0xFF555555),
                        Color(0xFF121212)
                    ))
                ) , verticalArrangement = Arrangement.Center , horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Column(verticalArrangement = Arrangement.Center , horizontalAlignment = Alignment.CenterHorizontally) {
                Row {
                    Text("X" , fontSize = 36.sp , fontFamily = Montserrat , fontWeight = FontWeight.ExtraBold , color = Color(
                        0xFF000000
                    )
                    )
                    Text("O" ,  fontSize = 36.sp , fontFamily = Montserrat , fontWeight = FontWeight.Bold, color = Color(
                        0xFFFFFFFF
                    )
                    )
                }
                Text("TicTacToe" , fontSize = 48.sp , fontFamily = Pacifico)
                Spacer(modifier = Modifier.height(14.dp))
                Text(
                    text = "crafted by Rohan",
                    style = TextStyle(
                        fontSize = 13.sp,
                        fontFamily = Montserrat,
                        fontWeight = FontWeight(500),
                        color = Color(0xFF000000),
                    )
                )
            }
        }
    }
}