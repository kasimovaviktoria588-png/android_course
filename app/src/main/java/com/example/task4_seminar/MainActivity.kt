package com.example.task4_seminar

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountBalance
import androidx.compose.material.icons.filled.Build
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Map
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material.icons.filled.Send
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.task4_seminar.ui.theme.Task4_seminarTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Task4_seminarTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    AppListScreen()
                }
            }
        }
    }
}

// Модель данных для приложения
data class AppData(
    val id: Int,
    val name: String,
    val description: String,
    val category: String,
    val icon: ImageVector
)

//Захардкоженные данные
val mockAppList = listOf(
    AppData(1, "Telegram", "Безопасный и быстрый мессенджер для общения.", "Социальные", Icons.Default.Send),
    AppData(2, "Яндекс Карты", "Навигатор, общественный транспорт и поиск мест.", "Навигация", Icons.Default.Map),
    AppData(3, "Госуслуги", "Быстрый доступ к государственным сервисам.", "Утилиты", Icons.Default.Email),
    AppData(4, "СберБанк", "Управление финансами и онлайн-банкинг.", "Финансы", Icons.Default.AccountBalance),
    AppData(5, "YouTube", "Платформа для просмотра и обмена видео.", "Развлечения", Icons.Default.PlayArrow)
)

// Главный экран
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AppListScreen() {
    Column(modifier = Modifier.fillMaxSize()) {
        // Тулбар с акцентным цветом и иконкой приложения слева
        TopAppBar(
            title = { Text(text = "Мои Приложения", color = Color.White) },
            navigationIcon = {
                Icon(
                    imageVector = Icons.Default.Build,
                    contentDescription = "App Icon",
                    tint = Color.White,
                    modifier = Modifier.padding(start = 12.dp, end = 8.dp)
                )
            },
            colors = TopAppBarDefaults.topAppBarColors(
                containerColor = MaterialTheme.colorScheme.primary
            )
        )

        LazyColumn(
            modifier = Modifier.fillMaxSize(),
            contentPadding = PaddingValues(16.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            items(mockAppList) { app ->
                AppItemRow(app = app)
            }
        }
    }
}

// Компонент одного элемента списка
@Composable
fun AppItemRow(app: AppData) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(12.dp),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surfaceVariant
        )
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            // Иконка приложения слева
            Icon(
                imageVector = app.icon,
                contentDescription = null,
                modifier = Modifier
                    .size(48.dp)
                    .background(MaterialTheme.colorScheme.primaryContainer, RoundedCornerShape(8.dp))
                    .padding(8.dp),
                tint = MaterialTheme.colorScheme.onPrimaryContainer
            )

            Spacer(modifier = Modifier.width(16.dp))

            // Текстовая информация
            Column(modifier = Modifier.weight(1f)) {
                Text(
                    text = app.name,
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold,
                    color = MaterialTheme.colorScheme.onSurface
                )
                Text(
                    text = app.description,
                    fontSize = 14.sp,
                    color = MaterialTheme.colorScheme.onSurfaceVariant,
                    modifier = Modifier.padding(vertical = 4.dp)
                )
                Text(
                    text = app.category,
                    fontSize = 12.sp,
                    fontWeight = FontWeight.SemiBold,
                    color = MaterialTheme.colorScheme.primary
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun AppListScreenPreview() {
    Task4_seminarTheme {
        AppListScreen()
    }
}
