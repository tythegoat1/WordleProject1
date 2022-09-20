package com.example.wordleproject1


import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.Toast
import android.widget.TextView
import android.widget.EditText

import java.util.*



class MainActivity : AppCompatActivity() {
    var counter = 0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val wordToGuess = FourLetterWordList.getRandomFourLetterword()
        val guessesField = findViewById<TextView>(R.id.guesses_view)
        val editText = findViewById<EditText>(R.id.four_letter)
        val showAnswer = findViewById<TextView>(R.id.dev_answer_show)
        val failView = findViewById<TextView> (R.id.info_view)
        val debugView = findViewById<TextView> (R.id.guess _wrd)

        val button = findViewById<Button>(R.id.button)
        var guessLimit = 3
        var checkGuessDisplay = ""
        var userInputDisplay   = ""

        button.setOnClickListener {
            if (guessLimit == 0) {
                finish()
                overridePendingTransition(0, 0);
                startActivity(intent);
                overridePendingTransition(0, 0)
            }

            counter++
            if (counter >=3) {
                Toast.makeText(
                    it.context,
                    "You have exceeded the number of tries!",
                    Toast.LENGTH_SHORT
                ).show()
            }
            if (guessLimit != 0) {
                showAnswer.text= "Ans: $wordToGuess"
                var isCorrect = false
                val editTextString = edittext.text.toString().uppercase(Locale.getDefault())
                if (editTextString.length == 4) {
                    failView.text = ""
                    val result = checkGuess(editTextString, wordToGuess)
                    checkGuess(editTextString, wordToGuess)

                    if (checkGuessDisplay == "") {
                        checkGuessDisplay = result
                        userInputDisplay = editTextString
                    }

                    else  {
                        checkGuessDisplay = checkGuessDisplay + "\n" + result
                        userInputDisplay = userInputDisplay + "\n" + editTextString
                    }

                    guessesField.text = checkGuessDisplay
                    debugView.text = userInputDisplay

                    if (result == "0000") {
                        failView.text = "Correct! \nAnswer: $wordToGuess"
                        button.text = "Reset"
                        isCorrect = true
                        guessLimit = 0
                    } else {
                        guessLimit--

                        if (guessLimit == 0 && !isCorrect) {
                            failView.text = "You're Out of Attempts! \nAnswer: $wordToGuess"
                            button.text = "Reset"
                        }
                    }
                }

                else
                    failView.text = "Oops, Gotta enter a Four Letter Word!"
            }
        }

    }

    private fun checkGuess(guess: String, wordToGuess: String) : String {
        var result = ""
        for (i in 0..3) {
            if (guess[i] == wordToGuess[i]) {
                result +=  "0"
            }
            else if (guess[i] in wordToGuess)  {
                result += "+"
            }
            else {
                result += "X"
            }
        }
        return result
    }

}
