package com.example.navigasi_compose_mvvm.ui.viewmodel

import androidx.lifecycle.ViewModel
import com.example.navigasi_compose_mvvm.data.model.Contact
import com.example.navigasi_compose_mvvm.data.repository.ContactRepository

class ContactViewModel : ViewModel() {
    private val repository = ContactRepository()
    val contacts = repository.contacts

    fun addContact(name: String, phone: String) {
        repository.addContact(name, phone)
    }

    fun deleteContact(contact: Contact) {
        repository.deleteContact(contact)
    }
}
