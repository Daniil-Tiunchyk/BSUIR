package bsuir.labs.labwork_1_v1

import android.annotation.SuppressLint
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
    private lateinit var heightEditText: EditText
    private lateinit var weightEditText: EditText
    private lateinit var calculateButton: Button
    private lateinit var resultTextView: TextView

    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        heightEditText = findViewById(R.id.heightEditText)
        weightEditText = findViewById(R.id.weightEditText)
        calculateButton = findViewById(R.id.calculateButton)
        resultTextView = findViewById(R.id.resultTextView)

        calculateButton.setOnClickListener {
            val heightStr = heightEditText.text.toString()
            val weightStr = weightEditText.text.toString()

            if (heightStr.isNotEmpty() && weightStr.isNotEmpty()) {
                val height = heightStr.toDouble()
                val weight = weightStr.toDouble()

                if (height.toInt() == 0) {
                    resultTextView.text = "Пожалуйста, не делите на 0"
                } else {
                    val bmi = calculateBMI(height, weight).toInt()
                    val bmiCategory = when {
                        bmi < 16 -> "значительный дефицит массы тела"
                        bmi < 18.5 -> "дефицит массы тела"
                        bmi < 25 -> "норма"
                        bmi < 30 -> "лишний вес"
                        bmi < 35 -> "ожирение первой степени"
                        bmi < 40 -> "ожирение второй степени"
                        else -> "ожирение третьей степени"
                    }
                    resultTextView.text = "Ваш индекс массы тела (BMI): $bmi: $bmiCategory"
                }
            } else {
                resultTextView.text = "Пожалуйста, введите рост и вес."
            }

        }
    }

    private fun calculateBMI(height: Double, weight: Double): Double {
        return weight / (height * height)
    }
}