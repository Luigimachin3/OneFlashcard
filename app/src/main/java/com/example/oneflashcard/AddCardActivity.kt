package com.example.oneflashcard

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.EditText
import android.widget.ImageView

class AddCardActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_card)

        val questionEditText = findViewById<EditText>(R.id.editTextField)
        val answerEditText = findViewById<EditText>(R.id.editTextFieldAnswer)

        val saveButton = findViewById<ImageView>(R.id.mySaveButton)
        saveButton.setOnClickListener {
            val questionString = questionEditText.text.toString()
            val answerString = answerEditText.text.toString()

            val data = Intent()
            data.putExtra("QUESTION_KEY", questionString)
            data.putExtra("ANSWER_KEY", answerString)

            setResult(RESULT_OK, data)
            finish()
        }

        val cancelButton = findViewById<ImageView>(R.id.myCancelButton)
        cancelButton.setOnClickListener {
            finish()
        }



        }


        }

