package com.munirs.wordpuzzle


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.activity.addCallback
import androidx.databinding.DataBindingUtil
import androidx.navigation.Navigation
import androidx.navigation.fragment.findNavController
import com.munirs.wordpuzzle.databinding.FragmentGameOverBinding
import kotlinx.android.synthetic.main.fragment_game_over.*

/**
 * A simple [Fragment] subclass.
 */
class FragmentGameOver : Fragment() {
    lateinit var binding: FragmentGameOverBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater,R.layout.fragment_game_over,container,false)

        binding.btnPlayAgain.setOnClickListener {
//            val action = FragmentGameOverDirections.actionFragmentGameOverToTitleFragment2()
////            findNavController().navigate(action)
            Navigation.findNavController(it).navigate(R.id.action_fragmentGameOver_to_titleFragment2)
        }
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        arguments?.let {
            val args = FragmentGameOverArgs.fromBundle(it)
            text_final_score.text = args.score.toString()
        }

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // This callback will only be called when MyFragment is at least Started.
        val callback = requireActivity().onBackPressedDispatcher.addCallback(this) {
            Navigation.findNavController(view!!).navigate(R.id.action_fragmentGameOver_to_titleFragment2)
        }

        // The callback can be enabled or disabled here or in the lambda
    }




}
