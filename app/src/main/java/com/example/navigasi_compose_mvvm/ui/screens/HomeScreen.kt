package com.example.navigasi_compose_mvvm.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.navigasi_compose_mvvm.ui.viewmodel.ContactViewModel

//import androidx.compose.foundation.lazy.items



@Composable
fun HomeScreen(viewModel: ContactViewModel) {
    val contacts by viewModel.contacts.collectAsStateWithLifecycle()
    var showDialog by rememberSaveable { mutableStateOf(false) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text("Contacts", style = MaterialTheme.typography.headlineSmall)
            Button(onClick = { showDialog = true }) {
                Icon(Icons.Filled.Add, contentDescription = "Add")
                Spacer(Modifier.width(4.dp))
                Text("Add")
            }
        }
        Spacer(Modifier.height(16.dp))
        LazyColumn {
            items(contacts) { contact ->
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 8.dp),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Column {
                        Text(contact.name)
                        Text(contact.phoneNumber)
                    }
                    Button(onClick = { viewModel.deleteContact(contact) }) {
                        Text("Delete")
                    }
                }
            }
        }
    }

    if (showDialog) AddContactDialog(viewModel) { showDialog = false }
}