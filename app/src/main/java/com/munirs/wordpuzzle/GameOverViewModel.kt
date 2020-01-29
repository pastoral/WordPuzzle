package com.munirs.wordpuzzle

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class GameOverViewModel(finalScore:Int) : ViewModel(){
    val score = MutableLiveData<Int>()
    init {
        score.value = finalScore
    }
}