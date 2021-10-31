package com.example.kotlinlearning.view.fragment

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import androidx.navigation.fragment.navArgs
import com.example.kotlinlearning.R
import com.example.kotlinlearning.databinding.QuizTastieraBinding
import com.example.kotlinlearning.viewmodel.QuizTastieraViewModel


/**
 * A simple [Fragment] subclass.
 * Use the [QuizTastieraFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class QuizTastieraFragment : Fragment() {

    lateinit var binding: QuizTastieraBinding
    val args:QuizTastieraFragmentArgs by navArgs()
    lateinit var  quiztastieraviewmodel: QuizTastieraViewModel


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?

    ): View? {
        // Inflate the layout for this fragment
        binding= DataBindingUtil.inflate(inflater,R.layout.quiz_tastiera,container,false)
        // inizializzo il viewmodel
        quiztastieraviewmodel= ViewModelProvider(this).get(QuizTastieraViewModel::class.java)
        //setto l'argomento scelto dall'utente per eseguire una chiamata al database che ritorna domande relative
        // a quell'argomento
        quiztastieraviewmodel.setCurrentArgoment(args.argument)
        //osserva il cambiamentodel livedata e inizializza un semplice mutablelist<DomandeInserimento>
        quiztastieraviewmodel.domandelivedata.observe(viewLifecycleOwner, Observer { it ->
            quiztastieraviewmodel.domande = it.toMutableList()
            Log.d("QuizTastieraFragment", "domanda di inserimento: $it")


        })
        quiztastieraviewmodel.mischiaDomande()
        binding.bConferma.setOnClickListener{ view : View ->
            quiztastieraviewmodel.correctAnswer("qualcosa")
            // controlla se il numero delle domande effettuate e minore del numero delle domande imposte,
            // se è vero allora verrà impostata una nuova domanda e verrà fatto il refresh dell'interfaccia grafica,
            // se è falso, quindi il numero delle domande eseguite è uguale alle domande imposte allora navigo al fragment successivo
            if(quiztastieraviewmodel.checkquestionNumber()) {

                quiztastieraviewmodel.setQuestion()
                binding.invalidateAll()
            }
            else
                //val action=
            view.findNavController().navigate(R.id.action_quizTastieraFragment_to_completamentoQuizFragment) }
         binding.domandatastiera= quiztastieraviewmodel
        return binding.root
    }



}