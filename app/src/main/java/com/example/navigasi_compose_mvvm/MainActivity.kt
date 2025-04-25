package com.example.navigasi_compose_mvvm

// MainActivity.kt

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Info
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.navigasi_compose_mvvm.ui.screens.AboutScreen
import com.example.navigasi_compose_mvvm.ui.screens.HomeScreen
import com.example.navigasi_compose_mvvm.ui.screens.LoginScreen
import com.example.navigasi_compose_mvvm.ui.screens.RegisterScreen
import com.example.navigasi_compose_mvvm.ui.viewmodel.AuthViewModel
import com.example.navigasi_compose_mvvm.ui.viewmodel.ContactViewModel

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MainActivityScreen()
        }
    }
}

@Composable
fun MainActivityScreen() {
    val navController = rememberNavController()
    val authViewModel: AuthViewModel = viewModel()
    val contactViewModel: ContactViewModel = viewModel()
    val loginState by authViewModel.loginState.collectAsStateWithLifecycle()

    if (loginState != true) {
        NavHost(
            navController = navController,
            startDestination = "login",
            modifier = Modifier.fillMaxSize()
        ) {
            composable("login") {
                LoginScreen(
                    viewModel = authViewModel,
                    onLoginSuccess = { /* loginState flips via ViewModel */ navController.navigate("home") },
                    onNavigateToRegister = { navController.navigate("register") }
                )
            }
            composable("register") {
                RegisterScreen(
                    viewModel = authViewModel,
                    onRegisterSuccess = { navController.popBackStack() }
                )
            }
        }
    } else {
        Scaffold(
            bottomBar = { BottomNavigationBar(navController) }
        ) { innerPadding ->
            NavHost(
                navController = navController,
                startDestination = "home",
                modifier = Modifier.padding(innerPadding)
            ) {
                composable("home") {
                    HomeScreen(viewModel = contactViewModel)
                }
                composable("about") {
                    AboutScreen()
                }
            }
        }
    }
}

@Composable
fun BottomNavigationBar(navController: NavController) {
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route
    NavigationBar {
        NavigationBarItem(
            selected = currentRoute == "home",
            onClick = { navController.navigate("home") },
            icon = { Icon(Icons.Filled.Home, contentDescription = "Home") },
            label = { Text("Home") }
        )
        NavigationBarItem(
            selected = currentRoute == "about",
            onClick = { navController.navigate("about") },
            icon = { Icon(Icons.Filled.Info, contentDescription = "About") },
            label = { Text("About") }
        )
    }
}