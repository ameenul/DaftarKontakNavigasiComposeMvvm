package com.example.navigasi_compose_mvvm.data.repository

import com.example.navigasi_compose_mvvm.data.model.Contact
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class ContactRepository {
    private val _contacts = MutableStateFlow<List<Contact>>(
        listOf(
            Contact("John Doe", "123-456-789"),
            Contact("Jane Smith", "987-654-321"),
            Contact("Alice Johnson", "555-666-777")
        )
    )
    val contacts: StateFlow<List<Contact>> = _contacts

    fun addContact(name: String, phone: String) {
        _contacts.value = _contacts.value + Contact(name, phone)
    }

    fun deleteContact(contact: Contact) {
        _contacts.value = _contacts.value - contact
    }
}
