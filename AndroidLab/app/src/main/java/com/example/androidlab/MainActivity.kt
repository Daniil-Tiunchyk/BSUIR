package com.example.androidlab

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val heightEditText: EditText = findViewById(R.id.heightEditText)
        val weightEditText: EditText = findViewById(R.id.weightEditText)
        val calculateButton: Button = findViewById(R.id.calculateButton)
        val resultTextView: TextView = findViewById(R.id.resultTextView)

        calculateButton.setOnClickListener {
            val height = heightEditText.text.toString().toDoubleOrNull()
            val weight = weightEditText.text.toString().toDoubleOrNull()

            if (height != null && weight != null) {
                val bmi = weight / (height * height)
                resultTextView.text = String.format("BMI: %.2f", bmi)
            } else {
                resultTextView.text = "Введите корректные данные"
            }
        }
    }
}
