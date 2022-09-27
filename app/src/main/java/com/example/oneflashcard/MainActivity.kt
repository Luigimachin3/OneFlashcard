package com.example.oneflashcard

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.activity.result.contract.ActivityResultContracts


class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val flashcardQuestion = findViewById<TextView>(R.id.flashcard_question)
        val flashcardAnswer = findViewById<TextView>(R.id.flashcard_answer)

        val textView1 = findViewById<TextView>(R.id.firstChoice)
        val textView2 = findViewById<TextView>(R.id.secondChoice)
        val textView3 = findViewById<TextView>(R.id.thirdChoice)

        val isShowingAnswers = findViewById<ImageView>(R.id.toggle_choices_visibility)
        val isShowingAnswers2 = findViewById<ImageView>(R.id.toggle_choices_blind)


        val resultLauncher = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
        val data: Intent? = result.data
        }


            findViewById<ImageView>(R.id.myPlusButton).setOnClickListener {
                val intent = Intent(this, AddCardActivity::class.java)
                resultLauncher.launch(intent)
            }


//    if (data != null) {
//        val string1 = data.getStringExtra("string1")
//        val string2 = data.getStringExtra("string2")
//
//        Log.i("MainActivity", "string1: $string1")
//        Log.i("MainActivity", "string2: $string2")
//    }else {
//        Log.i("MainActivity", "Returned null data from AddCardActivity")
//    }
//
//












        flashcardQuestion.setOnClickListener {
            flashcardQuestion.visibility = View.INVISIBLE
            flashcardAnswer.visibility = View.VISIBLE
        }

        flashcardAnswer.setOnClickListener {
            flashcardAnswer.visibility = View.INVISIBLE
            flashcardQuestion.visibility = View.VISIBLE
        }

        textView1.setOnClickListener {
            textView1.setBackgroundColor(resources.getColor(R.color.my_red_color))
        }

        textView2.setOnClickListener {
            textView2.setBackgroundColor(resources.getColor(R.color.my_red_color))
        }

        textView3.setOnClickListener {
            textView3.setBackgroundColor(resources.getColor(R.color.my_green_color))
        }


        isShowingAnswers.setOnClickListener {
            isShowingAnswers.setImageResource(R.drawable.ic_iconmonstr_eye_lined)
            textView1.visibility = View.INVISIBLE
            textView2.visibility = View.INVISIBLE
            textView3.visibility = View.INVISIBLE

            isShowingAnswers2.setOnClickListener {
                isShowingAnswers2.setImageResource(R.drawable.ic_iconmonstr_eye_off_lined)
                textView1.visibility = View.VISIBLE
                textView2.visibility = View.VISIBLE
                textView3.visibility = View.VISIBLE
            }
        }

    }

    }





