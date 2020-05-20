package com.goods.android.flc_kotlin.model

import androidx.room.*
import java.sql.Blob

@Entity(tableName = "questions")
data class Question (
    @ColumnInfo(name = "question_id") @PrimaryKey val id: Int,
    @ColumnInfo(name = "question_text") val text: String,
    @ColumnInfo(name = "number_of_answers") val numOfAnswers: Int,
    @ColumnInfo(name = "question_type") val type: Int,
    @ColumnInfo(name = "number_correct_answers") val numOfCorrectAnswers: Int,
    val difficulty: String,
    val description: String,
    @ColumnInfo(name = "image_blob") val imageBlob: ByteArray,
    @ColumnInfo(name = "question_text_spanish") val textSpanish: String,
    @ColumnInfo(name = "description_spanish") val descriptionSpanish: String
)