package com.example.vk_task

import android.os.Bundle
import androidx.compose.foundation.background
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.ui.Alignment
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.style.TextAlign
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import com.example.vk_task.ui.theme.Vk_taskTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            Vk_taskTheme {
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
            val details: AppDetailsDestination = backStackEntry.toRoute()
            AppDetailsScreen(
                appId = details.appId,
                navController = navController
            )
        }
    }
}

@Composable
fun MainScreen(navController: NavController) {
    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .statusBarsPadding(),
        contentPadding = PaddingValues(vertical = 16.dp),
        verticalArrangement = Arrangement.spacedBy(24.dp)
    ) {
        // карточка приложения
        item {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                // Иконка приложения
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

                // Название и издатель
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

                // Рейтинг и категория
                Row(
                    horizontalArrangement = Arrangement.spacedBy(16.dp),
                    modifier = Modifier.padding(vertical = 8.dp)
                ) {
                    Text(text = "⭐ 4.8", style = MaterialTheme.typography.bodyMedium)
                    Text(text = "•", style = MaterialTheme.typography.bodyMedium)
                    Text(text = "Реклама 3+", style = MaterialTheme.typography.bodyMedium)
                    Text(text = "•", style = MaterialTheme.typography.bodyMedium)
                    Text(text = "10M+ скачиваний", style = MaterialTheme.typography.bodyMedium)
                }

                Spacer(modifier = Modifier.height(12.dp))

                // Кнопка Скачать
                Button(
                    onClick = { /* Действие по скачиванию */ },
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
@Composable
fun AppDetailsScreen(appId: Int, navController: NavController) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .statusBarsPadding()
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        Text(
            text = "Карточка приложения (ID: $appId)",
            style = MaterialTheme.typography.headlineMedium
        )
        Text(
            text = "Здесь отображается полная информация о выбранном приложении из витрины.",
            style = MaterialTheme.typography.bodyLarge
        )
        Button(onClick = { navController.popBackStack() }) {
            Text(text = "Назад")
        }
    }
}