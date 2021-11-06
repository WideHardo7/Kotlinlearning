package com.example.kotlinlearning.view.fragment

import android.os.Binder
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
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
    var answeruser:String= ""


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?

    ): View? {
        // Inflate the layout for this fragment
        binding= DataBindingUtil.inflate(inflater,R.layout.quiz_tastiera,container,false)

        // inizializzo il viewmodel
        quiztastieraviewmodel= ViewModelProvider(this).get(QuizTastieraViewModel::class.java)
        Log.i("QuizTastieraFragment","Dentro QuizTastieraFragment, dopo aver inizializzato il viewmodel home, numero processo:${Binder.getCallingPid()}")


        //predo le domande che mi servono, le mischio e le setto
        quiztastieraviewmodel.selectQuestionfromArgument(args.argument,args.listadomandeinserimento.toList())
        quiztastieraviewmodel.mischiaDomande()



        binding.bConferma.setOnClickListener{ view : View ->
            answeruser=binding.rispostaDaTastiera.text.toString()

            if (!answeruser.isEmpty()) {
                //controlla se la risposta è giusta e incrementa l'indice delle domande,
                // come per segnare che un'altra domanda è stata eseguita
                quiztastieraviewmodel.correctAnswer(answeruser)
                Log.d(
                    "QuizTastieraFragment",
                    " la risposta data è corretta? : ${quiztastieraviewmodel.nrispcorrette}"
                )
                // controlla se il numero delle domande effettuate e minore del numero delle domande imposte,
                // se è vero allora verrà impostata una nuova domanda e verrà fatto il refresh dell'interfaccia grafica,
                // se è falso, quindi il numero delle domande eseguite è uguale alle domande imposte, allora navigo al fragment successivo,
                // passando il numero complessivo delle risposte esatte, di tutte le domande, l'argomento scelto
                if (quiztastieraviewmodel.checkquestionNumber()) {

                    quiztastieraviewmodel.setQuestion()
                    binding.invalidateAll()

                    Log.d("QuizTastieraFragment", "eseguito il refresh del layout ")
                } else {
                    var totrispostecorrette = args.nrisposte + quiztastieraviewmodel.nrispcorrette
                    val action =
                        QuizTastieraFragmentDirections.actionQuizTastieraFragmentToCompletamentoQuizFragment(
                            totrispostecorrette,
                            args.argument,quiztastieraviewmodel.listadiargomenti.toTypedArray()
                        )
                    view.findNavController().navigate(action)
                }
            }
            else{
                Toast.makeText(requireContext(),"Inserisci la risposta prima di confermare",Toast.LENGTH_LONG).show()
            }
        }

        binding.domandatastiera= quiztastieraviewmodel
        return binding.root
    }



}