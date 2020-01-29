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
        
       puzzleViewModel.score.observe(viewLifecycleOwner, Observer {
           binding.textScore.text = it.toString()
       })

        puzzleViewModel.gameFinish.observe(viewLifecycleOwner, Observer {
            if(it){
                gameOver()
            }
        })

        puzzleViewModel.word.observe(viewLifecycleOwner, Observer {
            binding.textAnswerBox1.text=puzzleViewModel.word.value?.question_gap_1
            binding.textAnswerBox2.text = puzzleViewModel.word.value?.question_gap_2
        })

        puzzleViewModel.currentTime.observe(viewLifecycleOwner, Observer {
            text_timer.text = DateUtils.formatElapsedTime(it)
        })

        binding.btnOK.setOnClickListener {
            checkAnswer()
        }
        binding.btnSkip.setOnClickListener {
            puzzleViewModel.onSkip()
        }
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
