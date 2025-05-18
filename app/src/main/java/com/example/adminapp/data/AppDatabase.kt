package com.example.adminapp.data

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [Appeal::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun appealDao(): AppealDao
}