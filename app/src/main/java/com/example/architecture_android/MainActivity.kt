package com.example.architecture_android

import android.os.Bundle
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import kotlinx.coroutines.flow.collectLatest
import kotlinx.serialization.Serializable

// Навигационные ключи
@Serializable
object MainListDestination

@Serializable
data class AppDetailsDestination(val appId: Int)

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MaterialTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    RootNavHost()
                }
            }
        }
    }
}

@Composable
fun RootNavHost() {
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = MainListDestination
    ) {
        composable<MainListDestination> {
            MainScreen(navController = navController)
        }

        composable<AppDetailsDestination> { backStackEntry ->
            // Если у вас используется toRoute из библиотеки сериализации
            // val details: AppDetailsDestination = backStackEntry.toRoute()
            AppDetailsScreen(navController = navController)
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainScreen(
    navController: NavController,
    viewModel: MainViewModel = viewModel() // Подключаем ViewModel
) {
    val snackbarHostState = remember { SnackbarHostState() }

    LaunchedEffect(key1 = true) {
        viewModel.snackbarEvents.collectLatest { message ->
            snackbarHostState.showSnackbar(message)
        }
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Магазин приложений") },
                actions = {
                    TextButton(onClick = { viewModel.onToolbarClicked() }) {
                        Text("Уведомление")
                    }
                }
            )
        },
        snackbarHost = { SnackbarHost(snackbarHostState) }
    ) { innerPadding ->
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding),
            contentPadding = PaddingValues(vertical = 16.dp),
            verticalArrangement = Arrangement.spacedBy(24.dp)
        ) {
            // Карточка приложения
            item {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Box(
                        modifier = Modifier
                            .size(96.dp)
                            .clip(RoundedCornerShape(24.dp))
                            .background(MaterialTheme.colorScheme.primary),
                        contentAlignment = Alignment.Center
                    ) {
                        Text(
                            text = "TS",
                            style = MaterialTheme.typography.headlineLarge,
                            color = MaterialTheme.colorScheme.onPrimary
                        )
                    }

                    Spacer(modifier = Modifier.height(12.dp))

                    Text(
                        text = "Tiles Survive! — Мир плиток",
                        style = MaterialTheme.typography.headlineMedium,
                        textAlign = TextAlign.Center
                    )
                    Text(
                        text = "Kingsglory",
                        style = MaterialTheme.typography.bodyMedium,
                        color = MaterialTheme.colorScheme.secondary
                    )

                    Spacer(modifier = Modifier.height(8.dp))

                    Row(
                        horizontalArrangement = Arrangement.spacedBy(8.dp),
                        modifier = Modifier.padding(vertical = 8.dp)
                    ) {
                        Text(text = "⭐ 4.8", style = MaterialTheme.typography.bodyMedium)
                        Text(text = "•", style = MaterialTheme.typography.bodyMedium)
                        Text(text = "Реклама 3+", style = MaterialTheme.typography.bodyMedium)
                        Text(text = "•", style = MaterialTheme.typography.bodyMedium)
                        Text(text = "10M+", style = MaterialTheme.typography.bodyMedium)
                    }

                    Spacer(modifier = Modifier.height(12.dp))

                    Button(
                        onClick = { /* Кнопка скачать */ },
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        Text(text = "Download")
                    }
                }
            }

            // Блок «Витрина»
            item {
                ShowcaseSection(
                    apps = showcaseApps,
                    onAppClick = { clickedAppId ->
                        navController.navigate(AppDetailsDestination(appId = clickedAppId))
                    }
                )
            }
        }
    }
}

@Composable
fun ShowcaseSection(apps: List<AppItem>, onAppClick: (Int) -> Unit) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        Text(
            text = "Витрина",
            style = MaterialTheme.typography.titleLarge
        )
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            apps.forEach { app ->
                Button(onClick = { onAppClick(app.id) }) {
                    Text(text = app.subtitle)
                }
            }
        }
    }
}

@Composable
fun AppDetailsScreen(navController: NavController) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .statusBarsPadding()
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        Text(
            text = "Карточка приложения",
            style = MaterialTheme.typography.headlineMedium
        )
        Button(onClick = { navController.popBackStack() }) {
            Text(text = "Назад")
        }
    }
}