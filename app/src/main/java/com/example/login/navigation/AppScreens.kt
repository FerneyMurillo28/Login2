package com.example.login.navigation

sealed class AppScreens(
    val ruta:String){
    object LoginScreen:AppScreens(ruta="loginScreen/{nombre}"){
        fun crearRouteNueva(nombre:String):String{
            return "loginScreen/$nombre"
        }
    }
    object RegistrationScreen:AppScreens(ruta="RegistrationScreen/{nombre}"){
        fun crearRoutes(nombres:String):String{
            return "RegistrationScreen/$nombres"
        }
    }
    object HomeScreen:AppScreens(ruta="homeScreen/{user}"){
        fun createRoute(user:String):String{
            return "homeScreen/$user"
        }
    }
}
