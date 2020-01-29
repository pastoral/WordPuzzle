package com.munirs.wordpuzzle

import android.os.CountDownTimer
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class PuzzleViewModel : ViewModel() {
    val score = MutableLiveData<Int>()
    val word = MutableLiveData<WordPuzzleData>()

    val gameFinish = MutableLiveData<Boolean>()
    val DONE = 0L
    // This is the number of milliseconds in a second
    val ONE_SECOND = 1000L
    // This is the total time of the game
   val COUNTDOWN_TIME = 10000L
   lateinit var timer : CountDownTimer
    val currentTime = MutableLiveData<Long>()




    lateinit var words : ArrayList<WordPuzzleData>
    init{
        loadData()
        nextWord()
        score.value = 0
        gameFinish.value = false

        timer = object :CountDownTimer(COUNTDOWN_TIME,ONE_SECOND){
            override fun onTick(millisUntilFinished: Long) {
                currentTime.value = millisUntilFinished/ONE_SECOND
            }

            override fun onFinish() {
                gameFinish.value = true
                currentTime.value = DONE
            }
        }
        timer.start()
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
            //gameFinish.value = true
            loadData()
        }
        //else{
            word.value = words.removeAt(0)

        //}
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

    override fun onCleared() {
        super.onCleared()
        timer.cancel()
    }


}