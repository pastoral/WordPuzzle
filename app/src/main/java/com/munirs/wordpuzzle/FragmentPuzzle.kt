package com.munirs.wordpuzzle


import android.os.Bundle
import android.text.format.DateUtils
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.*
import androidx.lifecycle.Observer

import androidx.navigation.fragment.findNavController
import com.munirs.wordpuzzle.databinding.FragmentPuzzleBinding
import kotlinx.android.synthetic.main.fragment_puzzle.*
import kotlinx.android.synthetic.main.fragment_title.*
import java.text.DateFormat

import java.util.*
import kotlin.collections.ArrayList

/**
 * A simple [Fragment] subclass.
 */
class FragmentPuzzle : Fragment() {

    lateinit var binding : FragmentPuzzleBinding
    private lateinit var puzzleViewModel: PuzzleViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater,R.layout.fragment_puzzle,container,false)
        puzzleViewModel = ViewModelProvider(this).get(PuzzleViewModel::class.java)

        binding.lifecycleOwner = this


        puzzleViewModel.gameFinish.observe(viewLifecycleOwner, Observer {
            if(it){
                gameOver()
            }
        })


        puzzleViewModel.currentTime.observe(viewLifecycleOwner, Observer {
            text_timer.text = DateUtils.formatElapsedTime(it)
        })

        binding.btnOK.setOnClickListener {
            checkAnswer()
        }

        binding.puzzleModel = puzzleViewModel
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

    }

    private fun gameOver(){
        val action = FragmentPuzzleDirections.actionFragmentPuzzleToFragmentGameOver(puzzleViewModel.score.value?:0)
        findNavController().navigate(action)
        puzzleViewModel.onGameOver()
//        Toast.makeText(activity,"Game Over", Toast.LENGTH_SHORT).show()

    }


    fun checkAnswer(){
        if(text_answer_gap.text.toString().toUpperCase() == puzzleViewModel.word.value?.corrctAnswer){
            text_answer_gap.text  = null
            puzzleViewModel.onCorrect()
        }
        else{
            text_answer_gap.text  = null
            puzzleViewModel.onWrong()
        }
    }


}
