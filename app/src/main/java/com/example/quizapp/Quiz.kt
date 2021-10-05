package com.example.quizapp

import android.util.Log

class Quiz(
    private val questionList : List<Question>,
    private var currentQuestionIndex : Int = 0,
    var score : Int = 0
    ) {


    //Have a function to see if there are any more questions remaining, returning a boolean
    fun questionsRemaining() : Boolean {
        return currentQuestionIndex < this.questionList.size
    }

    //Have a function to return the current question object
    fun currentQuestion() : Question {
        return questionList[currentQuestionIndex]
    }

    /* Have a function to check the right answer(with a parameter of the selected answer choice),
    update the score, and return a String message about whether the player was right */
    fun isCorrect(playerAnswer : Int) : String {
        var retval = "Correct!"
        if (playerAnswer == currentQuestion().correctAnswer) {
            score++
        } else {
            retval = "False!"
        }
        currentQuestionIndex++
        return retval
    }
}