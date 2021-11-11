package com.example.kotlinlearning.view.fragment

import android.os.Binder
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
    private val args:ArgomentoFragmentArgs by navArgs()
    lateinit var argumentviewmodel:ArgomentoViewModel


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
//Associo un layout " argomento" a questo fragment
        binding= DataBindingUtil.inflate(inflater,
            R.layout.argomento,container,false)



//inizializzo Argomentoviewmodel
        argumentviewmodel=ViewModelProvider(this).get(ArgomentoViewModel::class.java)
        Log.i("ArgomentoFragment","Dentro ArgomentoFragment, dopo aver inizializzato il viewmodel ")


        //settare nome argomento
        ChangeNameArguments(args.nameArgument)

        //settare il testo della teoria dell'argomento passato
        AddText()
        //setto il listener sul bottone quiz e gli passo  un safe args con argomenti, la stringa relativa all'argomento
        //selezionato e una lista contenente  le domande multiple relative all'argomento scelto(Si intende le domande a scelta multipla)
        binding.bQuiz.setOnClickListener{  view : View ->
            val action= ArgomentoFragmentDirections.actionArgomentoFragmentToQuizBottoneFragment(args.nameArgument, argumentviewmodel.selectQuestionfromArgument(args.nameArgument,argumentviewmodel.allMultiQuestion).toTypedArray())
            view.findNavController().navigate(action) }

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
   }


}