package com.example.tictactoec.Navigation

enum class Screens {
    SplashScreen ,
    HomeScreen ;

    companion object{
        fun fromRoute(route : String?): Screens = when(route?.substringBefore('/')){
            SplashScreen.name -> SplashScreen
            HomeScreen.name -> HomeScreen
            null -> HomeScreen
            else-> throw IllegalArgumentException("route $route not found")
        }
    }

}