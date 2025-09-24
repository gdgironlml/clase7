package com.example.clase7

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import com.example.clase7.ui.theme.Clase7Theme
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.clase7.screens.LoginScreen
import com.example.clase7.screens.RegisterScreen
import com.example.clase7.screens.SuccessScreen
import com.example.clase7.screens.UserScreen
import com.example.clase7.screens.UsersFormScreen
import com.google.firebase.Firebase
import com.google.firebase.FirebaseApp
import com.google.firebase.auth.auth

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {

        if (FirebaseApp.getApps(this).isEmpty()){
            FirebaseApp.initializeApp(this)
        }

        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {

            Clase7Theme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    Box(modifier = Modifier.padding(innerPadding)) {
                        MainScreens()
                    }
                }
            }
        }
    }
}

@Composable
fun MainScreens() {
    // Para la navegacion
    val navController = rememberNavController()
    val routeLogin = stringResource(R.string.route_login)
    val routeRegister = stringResource(R.string.route_register)
    val routeSuccess = stringResource(R.string.route_logsuccess)
    val routeUsers = stringResource(R.string.route_users)
    val routeUsersForm = stringResource(R.string.route_users_form)

    var initialScreen: String = "login"

    val auth = Firebase.auth
    val currentUser = auth.currentUser

    if (currentUser != null){
        initialScreen = routeUsers
    }

    NavHost(navController = navController, startDestination = initialScreen) {
        composable(routeLogin) { LoginScreen(navController) }
        composable(routeRegister) { RegisterScreen(navController) }
        composable(routeSuccess) { SuccessScreen(navController) }
        composable(routeUsers) { UserScreen(navController) }
        composable(routeUsersForm){ UsersFormScreen(navController) }
    }
}

