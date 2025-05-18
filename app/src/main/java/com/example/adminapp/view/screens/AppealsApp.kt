package com.example.adminapp.view.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import com.example.adminapp.view.components.AddAppealDialog
import com.example.adminapp.view.components.AppealItem
import com.example.adminapp.view.components.StatusFilter
import com.example.adminapp.viewModel.AppealViewModel


@Composable
fun AppealsApp(viewModel: AppealViewModel) {
    var showDialog by remember { mutableStateOf(false) }
    val appeals by viewModel.appeals.collectAsState()
    var selectedStatus by remember { mutableStateOf("Все") }

    Scaffold(
        floatingActionButton = {
            FloatingActionButton(onClick = { showDialog = true }) {
                Icon(Icons.Default.Add, contentDescription = "Добавить")
            }
        }
    ) { padding ->
        Column(
            modifier = Modifier
                .padding(padding)
                .fillMaxSize()
        ) {
            // Фильтр по статусу
            StatusFilter(selectedStatus) { selectedStatus = it }

            // Список обращений
            LazyColumn {
                items(appeals.filter {
                    selectedStatus == "Все" || it.status == selectedStatus
                }) { appeal ->
                    AppealItem(
                        appeal = appeal,
                        onStatusChange = { newStatus ->
                            viewModel.updateStatus(appeal, newStatus)
                        },
                        onDelete = {
                            viewModel.deleteAppeal(appeal)
                        }
                    )
                }
            }
        }
    }

    if (showDialog) {
        AddAppealDialog(
            onDismiss = { showDialog = false },
            onAdd = { text ->
                viewModel.addAppeal(text)
                showDialog = false
            }
        )
    }
}






