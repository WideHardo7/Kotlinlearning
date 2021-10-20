package com.example.kotlinlearning.view.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.navArgs
import com.example.kotlinlearning.R
import com.example.kotlinlearning.databinding.TeoriaNoQuizBinding
import com.example.kotlinlearning.viewmodel.ArgomentoViewModel


/**
 * A simple [Fragment] subclass.
 * Use the [TeoriaFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class TeoriaFragment : Fragment() {

    private lateinit var binding: TeoriaNoQuizBinding
    val args:ArgomentoFragmentArgs by navArgs()
    lateinit var argumentviewmodel:ArgomentoViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding= DataBindingUtil.inflate(inflater, R.layout.teoria_no_quiz,container,false)

        //inizializzo Argomentoviewmodel
        argumentviewmodel= ViewModelProvider(this).get(ArgomentoViewModel::class.java)

        //settare nome argomento
        ChangeNameArguments(args.nameArgument)

        //settare il testo della teoria dell'argomento passato
        AddText()

        return binding.root
    }

    //prende l'argomento passato al safe args e lo utilizza per modificare il nome dell'argomento
    private fun ChangeNameArguments(args:String){
        binding.teoriaNomeArgomento.text=args
    }

    //prende l'argomento passato e inserisce la teoria di questo argomento nella textview
    private fun AddText() {
        for (element in args.listateoria) {
            if (element.cod_argomento == args.nameArgument) binding.teoriaTeoria.text = element.testo_teoria
        }
    }
}