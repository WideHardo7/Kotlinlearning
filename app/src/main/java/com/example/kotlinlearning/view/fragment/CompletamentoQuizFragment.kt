package com.example.kotlinlearning.view.fragment

import android.os.Binder
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
import com.example.kotlinlearning.databinding.CompletamentoQuizBinding
import com.example.kotlinlearning.viewmodel.CompletamentoQuizViewModel


/**
 * A simple [Fragment] subclass.
 * Use the [CompletamentoQuizFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class CompletamentoQuizFragment : Fragment() {

    lateinit var completamentoQuizViewModel: CompletamentoQuizViewModel
    val args1: CompletamentoQuizFragmentArgs by navArgs()
    lateinit var binding: CompletamentoQuizBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding= DataBindingUtil.inflate(inflater,R.layout.completamento_quiz,container,false)

        completamentoQuizViewModel= ViewModelProvider(this).get(CompletamentoQuizViewModel::class.java)
        Log.i("CompletamentQuizFrgment","Dentro CompletamentoQuizFragment, dopo aver inizializzato il viewmodel home, numero processo:${Binder.getCallingPid()}")


        /*completamentoQuizViewModel.getAllArgument().observe(viewLifecycleOwner, Observer { listadiargomenti ->
            Log.d("CompletamentFragment","La lista di oggetti argument è stata estratta con successo, eccola :$listadiargomenti")

                completamentoQuizViewModel.listadiargomenti=listadiargomenti.toMutableList()



        })*/

        completamentoQuizViewModel.getArgumentbyNameArgument(args1.codArgomento).observe(viewLifecycleOwner, Observer { it->

            Log.i("CompletamentQuizFrgment","L'argomento passato è: $it")
            //effettuo l'operzione se e solo se la funzione insertNewValue non é stata ancora eseguita
            if(completamentoQuizViewModel.controllochiamatealdatabase)
            completamentoQuizViewModel.insertNewValue(args1.numerorisposteesatte,it,args1.listargomenti.toList())

            })
        setLayout()




        binding.bHome.setOnClickListener { view : View -> view.findNavController().navigate(R.id.action_completamentoQuizFragment_to_homeFragment) }

        return binding.root
    }
    //Funzione che setta gli elementi del layout in base al risultato dell'utente
    fun setLayout(){
        binding.risultato.text="${args1.numerorisposteesatte}/${completamentoQuizViewModel.ndomandetot}"
        //se ha superato o non superato il test setto una determinata immagine e testo
        if(completamentoQuizViewModel.isPassed(args1.numerorisposteesatte)){
            binding.immaginePunteggio.setImageResource(R.drawable.good_result)
            binding.sbloccoArgomento.setText(R.string.sblocco_argomento)
        } else{
            binding.immaginePunteggio.setImageResource(R.drawable.bad_result)
            binding.sbloccoArgomento.setText(R.string.non_sblocco_argomento)

        }
    }


}