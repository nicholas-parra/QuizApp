package com.example.quizapp

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.Group
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class MainActivity : AppCompatActivity() {
    lateinit var game : Quiz
    lateinit var questionText : TextView
    lateinit var answerOne : Button
    lateinit var answerTwo : Button
    lateinit var answerThree : Button
    lateinit var answerFour : Button
    lateinit var answerButtons : List<Button>
    lateinit var currentScore : TextView
    lateinit var finalScore : TextView
    lateinit var groupMainUi : Group


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        wireWidgets()

        // open questions.json, read text and save to jsonText
        val inputStream = resources.openRawResource(R.raw.questions)
        val jsonText = inputStream.bufferedReader().use {
            // the last line of the use lambda is returned -> lambda returns it.readText()
            it.readText()
        } // remember this is a LAMBDA, use BRACES

        // use gson to convert json into a List<Question>
        val sType = object : TypeToken<List<Question>>() { }.type
        val questions = Gson().fromJson<List<Question>>(jsonText, sType)
        game = Quiz(questions)

        // setup each button's onClickListener function to allow the game to run
        for (i in 0..3) {
            answerButtons[i].setOnClickListener {
                val currentQuestion = game.getCurrentQuestion()
                var response = if(game.isCorrect(i)) {
                    resources.getString(R.string.correct_response)
                } else {
                    resources.getString(R.string.false_response) +
                            "\"${currentQuestion.answers[currentQuestion.correctAnswer]}\""
                }
                Toast.makeText(this@MainActivity, response, Toast.LENGTH_SHORT).show()
                if (game.questionsRemaining()) {
                    updateText()
                } else {
                    reportScore()
                }
            }
        }

        // update each button/textview for the first time, game functionality separate from this call
        // functionality depends on onClickListeners
        updateText()
    }

    fun updateText() {
        val currentQuestion = game.getCurrentQuestion()
        questionText.text = currentQuestion.question
        for (i in 0..3) {
            answerButtons[i].text = currentQuestion.answers[i]
        }
        currentScore.text = resources.getString(R.string.current_score) + game.score
    }

    fun reportScore() {
        groupMainUi.visibility = View.GONE
        finalScore.text = resources.getString(R.string.final_score) +
                "${game.score}/${game.getQuestionNumber()}"
        finalScore.visibility = View.VISIBLE
    }

    fun wireWidgets() {
        questionText = findViewById(R.id.main_text_question)
        answerOne = findViewById(R.id.main_button_ansOne)
        answerTwo = findViewById(R.id.main_button_ansTwo)
        answerThree = findViewById(R.id.main_button_ansThree)
        answerFour = findViewById(R.id.main_button_ansFour)
        answerButtons = mutableListOf(answerOne, answerTwo, answerThree, answerFour)
        currentScore = findViewById(R.id.main_text_currentScore)
        finalScore = findViewById(R.id.main_text_finalScore)
        groupMainUi = findViewById(R.id.main_group_buttonsAndText)
        groupMainUi.visibility = View.VISIBLE
        finalScore.visibility = View.GONE
    }
}