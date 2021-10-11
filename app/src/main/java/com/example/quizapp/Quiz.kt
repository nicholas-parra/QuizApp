package com.example.quizapp

class Quiz(
    private val questionList : List<Question>,
    private var currentQuestionIndex : Int = 0,
    var score : Int = 0
    ) {

    fun getQuestionNumber() : Int {
        return questionList.size
    }

    //Have a function to see if there are any more questions remaining, returning a boolean
    fun questionsRemaining() : Boolean {
        return currentQuestionIndex < questionList.size
    }

    //Have a function to return the current question object
    fun getCurrentQuestion() : Question {
        return questionList[currentQuestionIndex]
    }

    /* Have a function to check the right answer(with a parameter of the selected answer choice),
    update the score, and return a String message about whether the player was right */
    fun isCorrect(playerAnswer : Int) : Boolean {
        var retval = true
        if (playerAnswer == getCurrentQuestion().correctAnswer) {
            score++
        } else {
            retval = false
        }
        currentQuestionIndex++
        return retval
    }
}