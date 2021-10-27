package com.example.kotlinlearning.view.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.navigation.findNavController
import com.example.kotlinlearning.R
import com.example.kotlinlearning.databinding.QuizTastieraBinding


/**
 * A simple [Fragment] subclass.
 * Use the [QuizTastieraFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class QuizTastieraFragment : Fragment() {

    lateinit var binding: QuizTastieraBinding


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?

    ): View? {
        // Inflate the layout for this fragment
        binding= DataBindingUtil.inflate(inflater,R.layout.quiz_tastiera,container,false)
        binding.bConferma.setOnClickListener{  view : View -> view.findNavController().navigate(R.id.action_quizTastieraFragment_to_completamentoQuizFragment) }

        return binding.root
    }



}