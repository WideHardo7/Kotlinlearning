package com.example.kotlinlearning.view.fragment

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import androidx.navigation.fragment.navArgs
import com.example.kotlinlearning.R
import com.example.kotlinlearning.database.teoria.Teoria
import com.example.kotlinlearning.databinding.ArgomentoBinding
import com.example.kotlinlearning.viewmodel.ArgomentoViewModel
import com.example.kotlinlearning.viewmodel.HomeViewModel


class ArgomentoFragment : Fragment() {

    lateinit var binding: ArgomentoBinding
    val args:ArgomentoFragmentArgs by navArgs()
    lateinit var argumentviewmodel:ArgomentoViewModel


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
//Associo un layout a questo fragment
        binding= DataBindingUtil.inflate(inflater,
            R.layout.argomento,container,false)



//inizializzo Argomentoviewmodel
        argumentviewmodel=ViewModelProvider(this).get(ArgomentoViewModel::class.java)
        //argumentviewmodel.getTheoryfromArgument(args.nameArgument)
        //settare nome argomento
        ChangeNameArguments(args.nameArgument)

        //settare il testo della teoria dell'argomento passato
        AddText()
        binding.argomentoviewmodel=argumentviewmodel
        binding.lifecycleOwner=this




        binding.bQuiz.setOnClickListener{  view : View ->
            view.findNavController().navigate(R.id.action_argomentoFragment_to_quizBottoneFragment) }

        return binding.root
        
    }
    //prende l'argomento passato al safe args e lo utilizza per modificare il nome dell'argomento
    private fun ChangeNameArguments(args:String){
        binding.quizNomeArgomento.text=args
    }
    //prende l'argomento passato e inserisce la teoria di questo argomento nella textview
   private fun AddText(){
        for(element in args.listateoria){
            if(element.cod_argomento==args.nameArgument)
                binding.teoriaQuiz.text=element.testo_teoria

        }
         //val testo=argumentviewmodel.getTheoryfromArgument(args.nameArgument)
        //binding.teoriaQuiz.text=testo


    }


}