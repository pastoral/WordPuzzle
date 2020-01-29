package com.munirs.wordpuzzle

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class PuzzleViewModel : ViewModel() {
    val score = MutableLiveData<Int>()
    val word = MutableLiveData<WordPuzzleData>()
    val gameFinish = MutableLiveData<Boolean>()



    lateinit var words : ArrayList<WordPuzzleData>
    init{
        loadData()
        nextWord()
        score.value = 0
        gameFinish.value = false
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
            gameFinish.value = true
        }
        else{
            word.value = words.removeAt(0)

        }
    }

    fun onCorrect(){
        score.value = (score.value)?.plus(1)
        nextWord()
    }
    fun onWrong(){
        score.value = (score.value)?.minus(1)
        nextWord()
    }
    fun onSkip(){
        score.value = (score.value)?.minus(1)
        nextWord()
    }

    fun onGameOver(){
        gameFinish.value = false
    }
}