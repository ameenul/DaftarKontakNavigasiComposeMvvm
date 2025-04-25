package com.example.navigasi_compose_mvvm.ui.screens

// LoginScreen.kt
import androidx.compose.foundation.layout.*

import androidx.compose.material3.Button
import androidx.compose.material3.Text

import androidx.compose.material3.TextField
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.navigasi_compose_mvvm.ui.viewmodel.AuthViewModel


// RegisterScreen.kt
@Composable
fun RegisterScreen(
    viewModel: AuthViewModel,
    onRegisterSuccess: () -> Unit
) {
    var username by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    val registerState by viewModel.registerState.collectAsStateWithLifecycle()

    // Navigate once when registration succeeds
    LaunchedEffect(registerState) {
        if (registerState == true) {
            onRegisterSuccess()
        }
    }

    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            TextField(
                value = username,
                onValueChange = { username = it },
                label = { Text("Username") }
            )
            Spacer(modifier = Modifier.height(8.dp))
            TextField(
                value = password,
                onValueChange = { password = it },
                label = { Text("Password") },
                visualTransformation = PasswordVisualTransformation()
            )
            Spacer(modifier = Modifier.height(16.dp))
            Button(onClick = { viewModel.register(username, password) }) {
                Text("Register")
            }
        }
    }
}
