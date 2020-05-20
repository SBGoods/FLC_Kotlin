package com.goods.android.flc_kotlin.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.goods.android.flc_kotlin.model.Answer
import com.goods.android.flc_kotlin.model.Question

// Annotates class to be a Room Database with a table (entity) of the Word class
@Database(entities = [Question::class, Answer::class], version = 13, exportSchema = false)
abstract class QuestionRoomDatabase : RoomDatabase() {

    abstract fun questionDao(): QuestionDao
    abstract fun answerDao(): AnswerDao


}