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
import com.example.kotlinlearning.databinding.QuizTestConoscenzeBinding
import com.example.kotlinlearning.viewmodel.QuizTestConoscenzeViewModel


class QuizTestConoscenzeFragment : Fragment() {

    lateinit var quizTestConoscenzeViewModel: QuizTestConoscenzeViewModel
    private lateinit var binding: QuizTestConoscenzeBinding
    private val args: QuizTestConoscenzeFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding= DataBindingUtil.inflate(inflater, R.layout.quiz_test_conoscenze,container,false)

        quizTestConoscenzeViewModel= ViewModelProvider(this).get(QuizTestConoscenzeViewModel::class.java)
        Log.i("QuizTestConoscFragment","Dentro QuizTestConoscenzeFragment, dopo aver inizializzato il viewmodel ")
        //prendo le domande che mi servono, le mischio e le setto
        quizTestConoscenzeViewModel.domande=args.listadomandetest.toMutableList()
        quizTestConoscenzeViewModel.mischiaDomande()

        Log.i("QuizTestConoscFragment","Selezionate domande, mischiate e settate nel layout")

        //creo il listener
        val listener=object :View.OnClickListener {
            override fun onClick(v: View?) {
                //prendo il contentDescription di ogni bottone, che contiene il nome della risposta
                // cliccata dall'utente
                val id = v?.contentDescription.toString()
                // viene memorizzato il numero della risposta scelta dall'utente,
                // se è la prima rimmarrà a zero
                var indexrisposta= 0
                when(id){
                    "risposta2" -> indexrisposta=1
                    "risposta3" -> indexrisposta=2
                    "risposta4" -> indexrisposta=3
                }
                //controlla se la risposta è giusta e incrementa l'indice delle domande,
                // come per segnare che un'altra domanda è stata eseguita
                quizTestConoscenzeViewModel.correctAnswer(indexrisposta)
                // controlla se il numero delle domande effettuate e minore del numero delle domande imposte,
                // se è vero allora verrà impostata una nuova domanda e verrà fatto il refresh dell'interfaccia grafica,
                // se è falso, quindi il numero delle domande eseguite è uguale alle domande imposte allora navigo al fragment successivo
                if(quizTestConoscenzeViewModel.checkquestionNumber()){

                    quizTestConoscenzeViewModel.setQuestion()
                    //Cambio la textview vicino alla progress bar con il numero delle domanda completate
                    binding.tcPunteggio.text= "${quizTestConoscenzeViewModel.indiceDomande.toString()}/${quizTestConoscenzeViewModel.domande.size}"
                    binding.invalidateAll()

                    Log.d("QuizTestConoscFragment","eseguito il refresh del layout  ")
                } else{
                    //naviga al fragment successivo, passando un safe args con argomenti, il numero delle domande effettuate
                    val action= QuizTestConoscenzeFragmentDirections.actionQuizTestConoscenzeToTestConoscenzeValutazioneFragment(quizTestConoscenzeViewModel.nrispcorrette)
                    view?.findNavController()?.navigate(action)

                }
            }
        }
        //associo il listener hai bottoni
        binding.tcRisp1.setOnClickListener(listener)
        binding.tcRisp2.setOnClickListener(listener)
        binding.tcRisp3.setOnClickListener(listener)
        binding.tcRisp4.setOnClickListener(listener)
        // Associo la variabile di layout al relativo viewmodel
        binding.domandetest= quizTestConoscenzeViewModel




        return binding.root
    }


}