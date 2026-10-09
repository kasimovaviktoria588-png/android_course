package com.example.first_project_git

import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class SecondActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_second)

        val textViewResult = findViewById<TextView>(R.id.textViewResult)
        val message = intent.getStringExtra("EXTRA_TEXT")

        textViewResult.text = message ?: "Данные не переданы"
    }
}
