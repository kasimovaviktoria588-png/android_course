package com.example.task6_arch

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import com.example.task6_arch.presentation.UserScreen
import com.example.task6_arch.presentation.UserViewModel
import com.example.task6_arch.ui.theme.Task6_archTheme

class MainActivity : ComponentActivity() {

    private val userViewModel: UserViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Task6_archTheme {
                UserScreen(viewModel = userViewModel)
            }
        }
    }
}
