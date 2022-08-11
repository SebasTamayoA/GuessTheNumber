package com.example.bemyguess

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView


class MainActivity : AppCompatActivity() {

    var answer = 0
    var isGameOver = false
    var numOfAttempts = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        // change tittle of the app
        this.title = "Guess the Number"
        startOver()
    }

    fun generateAnswer() {
        answer = (1..25).random()
    }

    fun startOver(){
        isGameOver = false
        generateAnswer()
        numOfAttempts = 0

        val answerTextView = findViewById<TextView>(R.id.answer) // get the text view
        answerTextView.text = "??" // set the text to the answer

        val submitButton = findViewById<Button>(R.id.buttonSubmit) // get the text view
        submitButton.isEnabled = true// disable the submit button

        val textView = findViewById<TextView>(R.id.textView) // get the text view
        textView.text = "Guess 1 to 25" // set the text to the answer

        val editTextGuess = findViewById<EditText>(R.id.editTextGuess) // get the text view
        editTextGuess.text.clear() // set the text to the answer
    }

    fun btnStartOverTapped(view: View){
        startOver()
    }

    fun btnSubmitTapped(view: View) {

        val guess = getUsersGuess() ?: -999 // get the users guess

        val textView = findViewById<TextView>(R.id.textView) // get the text view

        if(guess !in 1..25){
            textView.text = "Please enter a number between 1 and 25"
            return
        }

        var message = ""
        // count amount attempts
        numOfAttempts++

        if(guess == answer){

            message = "You got it!\nIt took you $numOfAttempts attempts"
            isGameOver = true

            val answerTextView = findViewById<TextView>(R.id.answer) // get the text view
            answerTextView.text = answer.toString() // set the text to the answer

            val submitButton = findViewById<Button>(R.id.buttonSubmit) // get the text view
            submitButton.isEnabled = false // disable the submit button
        } else {
            message = if (guess < answer) "Too low" else "Too high"
        }

        textView.text = message
    }

    fun getUsersGuess() : Int?{
        val editTextGuess = findViewById<EditText>(R.id.editTextGuess) // get the text view
        val userGuess = editTextGuess.text.toString() // get the text from the edit text

        var guessAsInt = 0

        try{
            guessAsInt = userGuess.toInt() // convert the string to an int
        } catch (e: NumberFormatException){
            return null
        }

        return guessAsInt
    }
}