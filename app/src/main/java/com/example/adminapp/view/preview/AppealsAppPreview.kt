package com.example.adminapp.view.preview

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
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.adminapp.data.Appeal
import com.example.adminapp.view.components.AddAppealDialog
import com.example.adminapp.view.components.AppealItem
import com.example.adminapp.view.components.StatusFilter


@Preview(showBackground = true, showSystemUi = true)
@Composable
fun AppealsAppPreview() {
    // Пример списка обращений для превью
    var sampleAppeals by remember {
        mutableStateOf(
            listOf(
                Appeal(id = 1,
                    text = "Проблема с дорогой",
                    status = "Новое",
                    date = System.currentTimeMillis()),

                Appeal(id = 2,
                    text = "Просьба убрать мусор",
                    status = "В работе",
                    date = System.currentTimeMillis() - 86400000),

                Appeal(id = 3,
                    text = "Предложение по благоустройству",

                    status = "Выполнено",
                    date = System.currentTimeMillis() - 172800000),
            )
        )
    }

    // Локальное состояние для фильтра и диалога
    var showDialog by remember { mutableStateOf(false) }
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
            StatusFilter(selectedStatus) { selectedStatus = it }

            LazyColumn {
                items(sampleAppeals.filter {
                    selectedStatus == "Все" || it.status == selectedStatus
                }) { appeal ->
                    AppealItem(
                        appeal = appeal,
                        onStatusChange = { newStatus ->
                            // Обновляем статус обращения в локальном списке
                            sampleAppeals = sampleAppeals.map {
                                if (it.id == appeal.id) it.copy(status = newStatus) else it
                            }
                        },
                        onDelete = {  
                            sampleAppeals = sampleAppeals.filter { it.id != appeal.id }
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
                // Добавляем новое обращение в локальный список
                val newAppeal = Appeal(
                    id = (sampleAppeals.maxOfOrNull { it.id } ?: 0) + 1,
                    text = text,
                    status = "Новое",
                    date = System.currentTimeMillis()
                )
                sampleAppeals = listOf(newAppeal) + sampleAppeals
                showDialog = false
            }
        )
    }
}

