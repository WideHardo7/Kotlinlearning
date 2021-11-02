package com.example.kotlinlearning.view.fragment

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.OnBackPressedCallback
import androidx.activity.OnBackPressedDispatcher
import androidx.activity.addCallback
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import androidx.navigation.fragment.navArgs
import com.example.kotlinlearning.R
import com.example.kotlinlearning.databinding.QuizBottoniBinding
import com.example.kotlinlearning.viewmodel.QuizBottoneViewModel


/**
 * A simple [Fragment] subclass.
 * Use the [QuizBottoneFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class QuizBottoneFragment : Fragment() {

    lateinit var binding: QuizBottoniBinding
    val args:QuizBottoneFragmentArgs by navArgs()
    lateinit var quizbottoneviewmodel:QuizBottoneViewModel
    var nrefresh=1

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

       //associa il layout "quiz_bottoni" a questo fragment
        binding= DataBindingUtil.inflate(inflater, R.layout.quiz_bottoni,container,false)



        //inizializzo il viewmodel
        quizbottoneviewmodel=ViewModelProvider(this).get(QuizBottoneViewModel::class.java)


        //prendo le domande che mi servono, le mischio e le setto
        quizbottoneviewmodel.selectQuestionfromArgument(args.codArgomento, args.listadomandemulti.toList())
        quizbottoneviewmodel.mischiaDomande()
        Log.d("QuizBottoneFragment","Selezionate domande, mischiate e settate nel layout")

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
                quizbottoneviewmodel.correctAnswer(indexrisposta)
                // controlla se il numero delle domande effettuate e minore del numero delle domande imposte,
                // se è vero allora verrà impostata una nuova domanda e verrà fatto il refresh dell'interfaccia grafica,
                // se è falso, quindi il numero delle domande eseguite è uguale alle domande imposte allora navigo al fragment successivo
                if(quizbottoneviewmodel.checkquestionNumber()){

                    quizbottoneviewmodel.setQuestion()
                    binding.invalidateAll()

                    Log.d("QuizBottoneFragment","eseguito il refresh del layout ${nrefresh++} volte")
                } else{
                    //naviga al fragment successivo, passando un safe args con argomenti, il numero delle domande effettuate
                    val action= QuizBottoneFragmentDirections.actionQuizBottoneFragmentToQuizTastieraFragment(quizbottoneviewmodel.nrispcorrette,args.codArgomento,quizbottoneviewmodel.allInputQuestion.toTypedArray())
                    view?.findNavController()?.navigate(action)

                }
            }
        }

        binding.domademultiple = quizbottoneviewmodel
        binding.bRisp1.setOnClickListener(listener)
        binding.bRisp2.setOnClickListener(listener)
        binding.bRisp3.setOnClickListener(listener)
        binding.bRisp4.setOnClickListener(listener)
        val callback= requireActivity().onBackPressedDispatcher.addCallback(this){}
        callback.remove()
        return binding.root

    }




}