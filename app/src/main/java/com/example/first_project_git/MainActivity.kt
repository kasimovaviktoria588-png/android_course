package com.example.first_project_git

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // Связываем класс с разметкой activity_main.xml
        setContentView(R.layout.activity_main)

        // Находим все элементы интерфейса по их ID
        val editTextData = findViewById<EditText>(R.id.editTextData)
        val buttonOpenSecond = findViewById<Button>(R.id.buttonOpenSecond)
        val buttonCallFriend = findViewById<Button>(R.id.buttonCallFriend)
        val buttonShareText = findViewById<Button>(R.id.buttonShareText)

        // 1. ЯВНЫЙ INTENT (Открыть вторую Activity и передать текст)
        buttonOpenSecond.setOnClickListener {
            val text = editTextData.text.toString().trim()

            // Валидация: проверяем, что поле не пустое
            if (text.isEmpty()) {
                Toast.makeText(this, "Пожалуйста, введите текст", Toast.LENGTH_SHORT).show()
            } else {
                // Создаем явный интент для открытия SecondActivity
                val intent = Intent(this, SecondActivity::class.java).apply {
                    putExtra("EXTRA_TEXT", text) // Складываем данные по ключу
                }
                startActivity(intent)
            }
        }

        // 2. НЕЯВНЫЙ INTENT (Позвонить другу)
        buttonCallFriend.setOnClickListener {
            val phoneNumber = editTextData.text.toString().trim()

            // Валидация: проверяем, что номер введен
            if (phoneNumber.isEmpty()) {
                Toast.makeText(this, "Пожалуйста, введите номер телефона", Toast.LENGTH_SHORT).show()
            } else {
                // Создаем неявный интент с действием ACTION_DIAL (открытие набирателя номера)
                val intent = Intent(Intent.ACTION_DIAL).apply {
                    data = Uri.parse("tel:$phoneNumber")
                }
                startActivity(intent)
            }
        }

        // 3. СИСТЕМНЫЙ INTENT (Поделиться текстом)
        buttonShareText.setOnClickListener {
            val shareText = editTextData.text.toString().trim()

            // Валидация: проверяем, что есть чем делиться
            if (shareText.isEmpty()) {
                Toast.makeText(this, "Пожалуйста, введите текст для отправки", Toast.LENGTH_SHORT).show()
            } else {
                // Создаем интент отправки текста
                val sendIntent = Intent(Intent.ACTION_SEND).apply {
                    type = "text/plain"
                    putExtra(Intent.EXTRA_TEXT, shareText)
                }
                // Оборачиваем в системный диалог выбора приложений (Chooser)
                val chooser = Intent.createChooser(sendIntent, "Поделиться через")
                startActivity(chooser)
            }
        }
    }
}
