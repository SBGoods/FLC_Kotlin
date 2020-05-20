package com.goods.android.flc_kotlin.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Query
import com.goods.android.flc_kotlin.model.Question

@Dao
interface QuestionDao {

    @Query("select * from questions where difficulty == :difficulty")
    fun getQuestions(difficulty : String) : LiveData<List<Question>>

    @Query("select * from questions order by random() limit :amount")
    fun getRandomQuestions(amount : Int) : LiveData<List<Question>>


}