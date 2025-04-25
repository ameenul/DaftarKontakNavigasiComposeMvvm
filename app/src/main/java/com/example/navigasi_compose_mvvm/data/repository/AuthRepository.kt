package com.example.navigasi_compose_mvvm.data.repository

import com.example.navigasi_compose_mvvm.data.model.User
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class AuthRepository {
    private val users = mutableListOf(
        User("admin", "admin123"),
        User("user", "user123")
    )
    private val _loginState = MutableStateFlow<Boolean?>(null)
    val loginState: StateFlow<Boolean?> = _loginState
    private val _registerState = MutableStateFlow<Boolean?>(null)
    val registerState: StateFlow<Boolean?> = _registerState

    fun login(username: String, password: String) {
        _loginState.value = users.any { it.username == username && it.password == password }
    }

    fun register(username: String, password: String) {
        if (users.any { it.username == username }) {
            _registerState.value = false
        } else {
            users.add(User(username, password))
            _registerState.value = true
        }
    }
    fun logout() {
        _loginState.value = false
    }
    //reset register state
    fun resetRegisterState() {
        _registerState.value = null
    }
}
