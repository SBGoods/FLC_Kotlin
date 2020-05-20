package com.goods.android.flc_kotlin.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import java.sql.Blob

@Entity(tableName = "answers")

data class Answer (
    @ColumnInfo(name = "answer_id") @PrimaryKey val id: Int,
    @ColumnInfo(name = "question_id") val question_id: Int,
    @ColumnInfo(name = "answer_text") val text: String,
    @ColumnInfo(name = "correctness") val isCorrect: Int,
    @ColumnInfo(name = "answer_text_spanish") val textSpanish: String
)