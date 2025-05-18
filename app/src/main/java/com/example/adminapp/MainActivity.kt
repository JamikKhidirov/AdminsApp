package com.example.adminapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.adminapp.ui.theme.AdminAppTheme
import com.example.adminapp.view.screens.AppealsApp
import com.example.adminapp.viewModel.AppealViewModel

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            val viewModel: AppealViewModel = viewModel()
            AppealsApp(viewModel)
        }
    }
}


