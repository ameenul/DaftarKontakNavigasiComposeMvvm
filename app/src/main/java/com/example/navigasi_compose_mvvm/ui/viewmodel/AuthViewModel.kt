package com.example.navigasi_compose_mvvm.ui.viewmodel

import androidx.lifecycle.ViewModel
import com.example.navigasi_compose_mvvm.data.repository.AuthRepository

class AuthViewModel : ViewModel() {
    private val repository = AuthRepository()
    val loginState = repository.loginState
    val registerState = repository.registerState

    fun login(username: String, password: String) {
        repository.login(username, password)
    }

    fun register(username: String, password: String) {
        repository.register(username, password)
    }
    fun logout() {
        repository.logout()
    }
    fun resetRegisterState(){
        repository.resetRegisterState()
    }
}
