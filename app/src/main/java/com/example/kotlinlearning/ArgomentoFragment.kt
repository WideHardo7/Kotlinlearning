package com.example.kotlinlearning

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.navigation.findNavController
import com.example.kotlinlearning.databinding.ArgomentoBinding


/**
 * A simple [Fragment] subclass.
 * Use the [ArgomentoFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class ArgomentoFragment : Fragment() {

    lateinit var binding: ArgomentoBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding= DataBindingUtil.inflate(inflater,
            R.layout.argomento,container,false)

        binding.bQuiz.setOnClickListener{  view : View ->
            view.findNavController().navigate(R.id.action_argomentoFragment_to_quizTastieraFragment) }

        return binding.root
        
    }


}