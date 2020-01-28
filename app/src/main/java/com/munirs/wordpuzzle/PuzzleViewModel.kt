package com.munirs.wordpuzzle

import androidx.lifecycle.ViewModel

class PuzzleViewModel : ViewModel() {
    var score = 0
    var word:WordPuzzleData? = null
    lateinit var words : ArrayList<WordPuzzleData>
    init{
        loadData()
        nextWord()
    }

    private fun loadData(){
        words = arrayListOf(
            WordPuzzleData("BOT","LE","T"),
            WordPuzzleData("EX","EPTION","C"),
            WordPuzzleData("PROCA","TINATION","S"),
            WordPuzzleData("INF","LTRATE","I"),
            WordPuzzleData("REC","NCILE","O")
        )
        words.shuffle()
    }

    private fun nextWord(){
        if(words.isEmpty()){
//            gameOver()
        }
        else{
            word = words.removeAt(0)

        }
    }

    fun onCorrect(){
        score++
        nextWord()
    }
    fun onWrong(){
        score--
        nextWord()
    }
    fun onSkip(){
        score--
        nextWord()
    }
}