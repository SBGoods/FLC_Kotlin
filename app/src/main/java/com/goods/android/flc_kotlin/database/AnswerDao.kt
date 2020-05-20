package com.goods.android.flc_kotlin.database

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.room.Dao
import androidx.room.Query
import com.goods.android.flc_kotlin.model.Answer

@Dao
interface AnswerDao {

    @Query("select * from answers where question_id == :questionId")
    fun getAnswers(questionId : Int) : LiveData<List<Answer>>
}