package com.example.adminapp.viewModel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import androidx.room.Room
import com.example.adminapp.data.AppDatabase
import com.example.adminapp.data.Appeal
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class AppealViewModel(application: Application) : AndroidViewModel(application) {
    private val db = Room.databaseBuilder(
        application,
        AppDatabase::class.java, "appeals-db"
    ).build()

    private val _appeals = db.appealDao().getAllAppeals()
        .flowOn(Dispatchers.IO)
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(), emptyList())

    val appeals: StateFlow<List<Appeal>> = _appeals

    fun addAppeal(text: String) {
        viewModelScope.launch(Dispatchers.IO) {
            db.appealDao().insert(Appeal(text = text))
        }
    }

    fun updateStatus(appeal: Appeal, newStatus: String) {
        viewModelScope.launch(Dispatchers.IO) {
            db.appealDao().update(appeal.copy(status = newStatus))
        }
    }

    fun deleteAppeal(appeal: Appeal) {
        viewModelScope.launch(Dispatchers.IO) {
            db.appealDao().delete(appeal)
        }
    }
}
