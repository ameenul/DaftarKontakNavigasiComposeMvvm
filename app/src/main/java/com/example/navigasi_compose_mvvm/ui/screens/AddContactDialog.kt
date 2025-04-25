package com.example.navigasi_compose_mvvm.ui.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.navigasi_compose_mvvm.ui.viewmodel.ContactViewModel


@Composable
fun AddContactDialog(viewModel: ContactViewModel, onDismiss: () -> Unit) {
    var name by rememberSaveable { mutableStateOf("") }
    var phone by rememberSaveable { mutableStateOf("") }

    AlertDialog(
        onDismissRequest = onDismiss,
        title = { Text("Add Contact") },
        text = {
            Column {
                TextField(value = name, onValueChange = { name = it }, label = { Text("Name") })
                Spacer(Modifier.height(8.dp))
                TextField(value = phone, onValueChange = { phone = it }, label = { Text("Phone") })
            }
        },
        confirmButton = {
            Button(onClick = {
                if (name.isNotBlank() && phone.isNotBlank()) {
                    viewModel.addContact(name.trim(), phone.trim())
                    onDismiss()
                }
            }) { Text("Add") }
        },
        dismissButton = {
            OutlinedButton(onClick = onDismiss) { Text("Cancel") }
        }
    )
}

