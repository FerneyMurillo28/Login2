package com.example.login.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import com.example.login.navigation.AppScreens

@Composable
fun HomeScreen(navController: NavController, user:String){
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.SpaceAround,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = "HomeScreen",
            style = MaterialTheme.typography.h3,
            color = MaterialTheme.colors.secondary
        )
        Text(text =  "$user")
        Button(onClick = {navController.navigate(route = AppScreens.LoginScreen.ruta)}) {
            Text(text = "Volver")
        }
    }
}