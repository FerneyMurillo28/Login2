package com.example.login.navigation

import android.annotation.SuppressLint
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.navArgument
import androidx.navigation.compose.rememberNavController
import com.example.login.presentation.login.LoginScreen
import com.example.login.presentation.login.LoginViewModel
import com.example.login.presentation.registration.RegisterViewModel
import com.example.login.presentation.registration.RegistrationScreen
import com.example.login.screens.HomeScreen

@SuppressLint("SuspiciousIndentation")
@Composable
fun AppNavigation(){
    val navController= rememberNavController()
    NavHost(navController = navController,
        startDestination = AppScreens.LoginScreen.ruta ){
        val viewModel=LoginViewModel()
        composable(route = AppScreens.LoginScreen.ruta){
        if(viewModel.state.value.successLogin){
            LaunchedEffect(key1 = Unit ){
                navController.navigate(AppScreens.HomeScreen.createRoute(viewModel.state.value.email))
                {
                    popUpTo(AppScreens.LoginScreen.ruta){
                        inclusive=true
                    }
                }
            }
        }else{
            LoginScreen(
                navController,
                state=viewModel.state.value,
                onLogin = viewModel::login,
                onDismissDiolog = viewModel::hideErrorDialog,
                onNavigateToRegister = {navController.navigate((AppScreens.RegistrationScreen.ruta))}
            )
        }
        }
        val viewModel2=RegisterViewModel()
        composable(route = AppScreens.RegistrationScreen.ruta){
            RegistrationScreen(
                navController,
                state =  viewModel2.state.value,
                onRegister = viewModel2::register,
                onBack = {navController.popBackStack()},
                onDismissDialog =viewModel2::hideErrorDialog)
        }
        composable(route = AppScreens.HomeScreen.ruta,
        arguments = listOf(navArgument("user"){
            type= NavType.StringType
        })
        ){
            HomeScreen(navController, user = it.arguments?.getString("user")?: "") }
    }
}//finFun