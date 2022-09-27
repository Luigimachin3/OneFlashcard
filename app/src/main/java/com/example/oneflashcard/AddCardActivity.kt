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


findViewById<ImageView>(R.id.myCancelButton).setOnClickListener {

    findViewById<EditText>(R.id.editTextField).text.toString()

    val data = Intent()

    data.putExtra(
        "string1",
        "some string"
    )

    data.putExtra(
        "string2",
        "another string"
    )
    setResult(RESULT_OK, data)

    finish()
}

    }
}

