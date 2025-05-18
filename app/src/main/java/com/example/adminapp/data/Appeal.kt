package com.example.adminapp.data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "appeals")
data class Appeal(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val text: String,
    val status: String = "Новое",
    val date: Long = System.currentTimeMillis()
)
