package com.example.oneflashcard

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.oneflashcard.FlashcardDao

@Database(entities = [Flashcard::class], version = 1)

abstract class AppDatabase : RoomDatabase() {
    abstract fun flashcardDao(): FlashcardDao
}
