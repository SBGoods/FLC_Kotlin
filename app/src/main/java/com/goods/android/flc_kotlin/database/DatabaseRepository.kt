package com.goods.android.flc_kotlin.database

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.room.Room
import com.goods.android.flc_kotlin.model.Answer
import com.goods.android.flc_kotlin.model.Question

class DatabaseRepository(context: Context) {

    companion object {
        private var INSTANCE: DatabaseRepository? = null
        fun initialize(context: Context) {
            if (INSTANCE == null) {
                INSTANCE = DatabaseRepository(context)
            }
        }
        fun get(): DatabaseRepository {
            return INSTANCE ?:
            throw IllegalStateException("CrimeRepository must be initialized")
        }
    }

    private val questionDatabase = Room.databaseBuilder(
        context.applicationContext,
        QuestionRoomDatabase::class.java,
        "question_database"
    ).createFromAsset("databases/questions.db").build()

    private val questionDao = questionDatabase.questionDao()
    private val answerDao = questionDatabase.answerDao()

    fun getQuestions(difficulty : String) : LiveData<List<Question>>{
        return questionDao.getQuestions(difficulty)
    }

    fun getRandomQuestions(amount : Int) : LiveData<List<Question>>{
        return questionDao.getRandomQuestions(amount)
    }

    fun getAnswers(questionId: Int) : LiveData<List<Answer>> {
        return answerDao.getAnswers(questionId)
    }

}