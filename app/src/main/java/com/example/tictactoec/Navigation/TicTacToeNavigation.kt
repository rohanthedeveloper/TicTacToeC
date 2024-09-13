package com.example.tictactoec.Navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.tictactoec.Screens.HomeScreen
import com.example.tictactoec.Screens.SplashScreen
import com.example.tictactoec.Screens.TicTacToeGame

@Composable
fun TicTacToeNavigation() {
    val navController = rememberNavController()
    NavHost(navController = navController , startDestination = Screens.SplashScreen.name){
        composable (Screens.SplashScreen.name){
            SplashScreen(navController = navController)
        }
        composable(Screens.HomeScreen.name) {
            TicTacToeGame(navController = navController)
        }
    }
}