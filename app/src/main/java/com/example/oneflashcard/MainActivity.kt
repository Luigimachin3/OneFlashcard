package com.example.oneflashcard

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import com.google.android.material.snackbar.Snackbar


class MainActivity : AppCompatActivity() {

    lateinit var flashcardDatabase: FlashcardDatabase
    var allFlashcards = mutableListOf<Flashcard>()

    var currCardDisplayedIndex = 0


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        flashcardDatabase = FlashcardDatabase(this)
        allFlashcards = flashcardDatabase.getAllCards().toMutableList()

        val flashcardQuestion = findViewById<TextView>(R.id.flashcard_question)
        val flashcardAnswer = findViewById<TextView>(R.id.flashcard_answer)

        if (allFlashcards.size > 0) {
            flashcardQuestion.text = allFlashcards[0].question
            flashcardAnswer.text = allFlashcards[0].answer
        }

        flashcardQuestion.setOnClickListener {
            flashcardQuestion.visibility = View.INVISIBLE
            flashcardAnswer.visibility = View.VISIBLE

            Toast.makeText(this, "Question button was clicked", Toast.LENGTH_SHORT).show()
            Snackbar.make(
                flashcardQuestion, "Question button was clicked",
                Snackbar.LENGTH_SHORT
            ).show()

            Log.i("Luis", "Question button was clicked")
        }

        flashcardAnswer.setOnClickListener {
            flashcardAnswer.visibility = View.INVISIBLE
            flashcardQuestion.visibility = View.VISIBLE
        }

        val resultLauncher =
            registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->

                val data: Intent? = result.data
                //TODO: extract data
                if (data != null) {
                    val questionString = data.getStringExtra("QUESTION_KEY")
                    val answerString = data.getStringExtra("ANSWER_KEY")

                    flashcardQuestion.text = questionString
                    flashcardAnswer.text = answerString

                    Log.i("MainActivity", "question: $questionString")
                    Log.i("MainActivity", "answer: $answerString")

                    if (!questionString.isNullOrEmpty() && !answerString.isNullOrEmpty()) {
                        flashcardDatabase.insertCard(Flashcard(questionString, answerString))
                        allFlashcards = flashcardDatabase.getAllCards().toMutableList()
                    }

                } else {
                    Log.i("MainActivity", "Returned null data from AddCardActivity")
                }
            }


        val addQuestionButton = findViewById<ImageView>(R.id.myPlusButton)
        addQuestionButton.setOnClickListener {
            val intent = Intent(this, AddCardActivity::class.java)
            resultLauncher.launch(intent)
        }

        val nextButton = findViewById<ImageView>(R.id.myNextButton)
        nextButton.setOnClickListener {
            if (allFlashcards.isEmpty()) {
                // early return so that the rest of the code doesn't execute
                return@setOnClickListener
            }

            currCardDisplayedIndex++

            if (currCardDisplayedIndex >= allFlashcards.size) {
                // go back to the beginning
                currCardDisplayedIndex = 0
            }

            allFlashcards = flashcardDatabase.getAllCards().toMutableList()

            val question = allFlashcards[currCardDisplayedIndex].question
            val answer = allFlashcards[currCardDisplayedIndex].answer

            flashcardQuestion.text = question
            flashcardAnswer.text = answer
        }





        val textView1 = findViewById<TextView>(R.id.firstChoice)
        val textView2 = findViewById<TextView>(R.id.secondChoice)
        val textView3 = findViewById<TextView>(R.id.thirdChoice)

        textView1.visibility = View.INVISIBLE
        textView2.visibility = View.INVISIBLE
        textView3.visibility = View.INVISIBLE

        var shouldShowAnswers = true

        val eyeVisible = findViewById<ImageView>(R.id.toggle_choices_visibility)
        eyeVisible.setOnClickListener {
            if (shouldShowAnswers) {
                eyeVisible.setImageResource(R.drawable.ic_iconmonstr_eye_off_lined)
                textView1.visibility = View.VISIBLE
                textView2.visibility = View.VISIBLE
                textView3.visibility = View.VISIBLE
                shouldShowAnswers = false
            } else {
                eyeVisible.setImageResource(R.drawable.ic_iconmonstr_eye_lined)
                textView1.visibility = View.INVISIBLE
                textView2.visibility = View.INVISIBLE
                textView3.visibility = View.INVISIBLE
                shouldShowAnswers = true
            }

        }

        textView1.setOnClickListener {
            textView1.setBackgroundColor(resources.getColor(R.color.my_red_color))
        }

        textView2.setOnClickListener {
            textView2.setBackgroundColor(resources.getColor(R.color.my_red_color))
        }

        textView3.setOnClickListener {
            textView3.setBackgroundColor(resources.getColor(R.color.my_green_color))
            textView2.setBackgroundColor(resources.getColor(R.color.my_red_color))
            textView1.setBackgroundColor(resources.getColor(R.color.my_red_color))
        }


    }
}









