package com.example.quizapp

import com.google.gson.annotations.SerializedName

data class Question(
    val question : String,
    val answers : List<String>,
    @SerializedName("correct_answer")
    val correctAnswer : Int
)