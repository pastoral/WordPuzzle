package com.munirs.wordpuzzle


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.databinding.DataBindingUtil
import androidx.navigation.fragment.findNavController
import com.munirs.wordpuzzle.databinding.FragmentPuzzleBinding
import kotlinx.android.synthetic.main.fragment_puzzle.*
import kotlinx.android.synthetic.main.fragment_title.*

import java.util.*
import kotlin.collections.ArrayList

/**
 * A simple [Fragment] subclass.
 */
class FragmentPuzzle : Fragment() {

    lateinit var binding : FragmentPuzzleBinding
    var score = 0
    var word:WordPuzzleData? = null
    lateinit var words : ArrayList<WordPuzzleData>
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater,R.layout.fragment_puzzle,container,false)
        loadData()
        nextWord()
        binding.btnOK.setOnClickListener { checkAnswer() }
        binding.btnSkip.setOnClickListener { onSkip() }
//        updateScore()
////        updateWord()
        return binding.root
    }



    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

    }

    private fun loadData(){
         words = arrayListOf(
            WordPuzzleData("BOT",binding.textAnswerGap.text.toString().toUpperCase(Locale("en")),"LE","T"),
            WordPuzzleData("EX",binding.textAnswerGap.text.toString().toUpperCase(Locale("en")),"EPTION","C"),
            WordPuzzleData("PROCA",binding.textAnswerGap.text.toString().toUpperCase(Locale("en")),"TINATION","S"),
            WordPuzzleData("INF",binding.textAnswerGap.text.toString().toUpperCase(Locale("en")),"LTRATE","I"),
            WordPuzzleData("REC",binding.textAnswerGap.text.toString().toUpperCase(Locale("en")),"NCILE","O")
            )
        words.shuffle()
    }

    private fun nextWord(){
        if(words.isEmpty()){
            gameOver()
        }
        else{
            word = words.removeAt(0)

        }
        updateWord()
        updateScore()
        binding.invalidateAll()
    }




    private fun gameOver(){
        val action = FragmentPuzzleDirections.actionFragmentPuzzleToFragmentGameOver(score)
        findNavController().navigate(action)

    }

    fun updateWord(){
        var temp = word
        binding.textAnswerBox1.text=word?.question_gap_1
        binding.textAnswerBox2.text = word?.question_gap_2
    }

    fun updateScore(){
        binding.textScore.text = score?.toString()
    }

    fun checkAnswer(){
        if(text_answer_gap.text.toString().toUpperCase() == word?.corrctAnswer){
            text_answer_gap.text  = null
            score++
            nextWord()
        }
        else{
            text_answer_gap.text  = null
            score--
            nextWord()
        }
    }

    fun onSkip(){
        score--
        nextWord()
    }
}
