package com.example.kotlinlearning.view.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.navigation.findNavController
import androidx.navigation.fragment.navArgs
import com.example.kotlinlearning.R
import com.example.kotlinlearning.databinding.ArgomentoBinding


/**
 * A simple [Fragment] subclass.
 * Use the [ArgomentoFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class ArgomentoFragment : Fragment() {

    lateinit var binding: ArgomentoBinding
    val args:ArgomentoFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding= DataBindingUtil.inflate(inflater,
            R.layout.argomento,container,false)
        ChangeNameArguments(args.nameArgument)

        binding.bQuiz.setOnClickListener{  view : View ->
            view.findNavController().navigate(R.id.action_argomentoFragment_to_quizBottoneFragment) }

        return binding.root
        
    }
    //prende l'argomento passato al safe args e lo utilizza per modificare il nome dell'argomento
    private fun ChangeNameArguments(args:String){
        binding.quizNomeArgomento.text=args
    }


}