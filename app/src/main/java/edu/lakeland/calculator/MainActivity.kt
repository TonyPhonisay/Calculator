package edu.lakeland.calculator

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    private lateinit var num1: EditText
    private lateinit var num2: EditText
    private lateinit var answer: TextView

    private lateinit var btnAdd: Button
    private lateinit var btnSubtract: Button
    private lateinit var btnMultiply: Button
    private lateinit var btnDivide: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Bind views
        num1 = findViewById(R.id.editTextNumber1)
        num2 = findViewById(R.id.editTextNumber2)
        answer = findViewById(R.id.txtAnswer)

        btnAdd = findViewById(R.id.btnAdd)
        btnSubtract = findViewById(R.id.btnSubtract)
        btnMultiply = findViewById(R.id.btnMultiply)
        btnDivide = findViewById(R.id.btnDivide)

        // Click listeners
        btnAdd.setOnClickListener { compute(Operation.ADD) }
        btnSubtract.setOnClickListener { compute(Operation.SUB) }
        btnMultiply.setOnClickListener { compute(Operation.MUL) }
        btnDivide.setOnClickListener { compute(Operation.DIV) }
    }

    private fun compute(op: Operation) {
        val a = num1.text.toString().trim().toDoubleOrNull()
        val b = num2.text.toString().trim().toDoubleOrNull()

        if (a == null || b == null) {
            Toast.makeText(this, "Please enter valid numbers", Toast.LENGTH_SHORT).show()
            return
        }

        val result = when (op) {
            Operation.ADD -> a + b
            Operation.SUB -> a - b
            Operation.MUL -> a * b
            Operation.DIV -> {
                if (b == 0.0) {
                    Toast.makeText(this, "Cannot divide by zero", Toast.LENGTH_SHORT).show()
                    return
                }
                a / b
            }
        }

        // Display as "Answer: 99.99"
        answer.text = String.format("Answer: %.2f", result)
    }

    private enum class Operation { ADD, SUB, MUL, DIV }
}