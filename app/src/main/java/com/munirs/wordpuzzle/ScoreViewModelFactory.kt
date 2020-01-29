package com.munirs.wordpuzzle

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import java.lang.IllegalArgumentException

class ScoreViewModelFactory(private val finalScore:Int):ViewModelProvider.Factory{
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(GameOverViewModel::class.java)){
            return GameOverViewModel(finalScore) as T
        }
        throw IllegalArgumentException("wrong ViewModel class")
    }
}