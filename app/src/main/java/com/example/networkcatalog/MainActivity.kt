package com.example.networkcatalog

import android.os.Bundle
import com.example.networkcatalog.viewModel.CatalogViewModel
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.networkcatalog.model.AppItem

class MainActivity : ComponentActivity() {

    private val viewModel: CatalogViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Surface(
                modifier = Modifier.fillMaxSize(),
                color = MaterialTheme.colorScheme.background
            ) {
                CatalogScreen(viewModel = viewModel)
            }
        }
    }
}

@Composable
fun CatalogScreen(viewModel: CatalogViewModel) {
    Box(modifier = Modifier.fillMaxSize()) {
        when {
            viewModel.isLoading -> {
                CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
            }
            viewModel.errorMessage != null -> {
                Column(
                    modifier = Modifier.align(Alignment.Center),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(text = viewModel.errorMessage ?: "Неизвестная ошибка", color = MaterialTheme.colorScheme.error)
                    Spacer(modifier = Modifier.height(8.dp))
                    Button(onClick = { viewModel.loadCatalog() }) {
                        Text("Повторить")
                    }
                }
            }
            else -> {
                // Выводим список приложений
                LazyColumn(
                    modifier = Modifier.fillMaxSize(),
                    contentPadding = PaddingValues(16.dp),
                    verticalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    items(viewModel.appList) { app ->
                        AppCard(app = app)
                    }
                }
            }
        }
    }
}

@Composable
fun AppCard(app: AppItem) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        ) {
            Text(
                text = app.title,
                style = MaterialTheme.typography.titleMedium
            )
            Spacer(modifier = Modifier.height(4.dp))
            Text(
                text = app.description ?: "Нет описания",
                style = MaterialTheme.typography.bodyMedium
            )
        }
    }
}