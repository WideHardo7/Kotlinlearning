package com.example.kotlinlearning.view.fragment

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import androidx.navigation.ui.NavigationUI
import com.example.kotlinlearning.R
import com.example.kotlinlearning.databinding.HomeBinding
import com.example.kotlinlearning.viewmodel.ArgomentoViewModel
import com.example.kotlinlearning.viewmodel.HomeViewModel
import kotlinx.coroutines.flow.observeOn


/**
 * A simple [Fragment] subclass.
 * Use the [HomeFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class HomeFragment : Fragment() {

    private lateinit var binding: HomeBinding
    lateinit var homeviewmodel:HomeViewModel


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment

        binding=DataBindingUtil.inflate(inflater,
            R.layout.home,container,false)

        //inizializzo il viewmodel del fragment home
                homeviewmodel= ViewModelProvider(this).get(HomeViewModel::class.java)
        Log.i("homefragment","dopo aver inizializzato il viewmodel home")

//setto un observer per aggiornare la lista "listargomenti" una volta che la query è stata eseguita
        homeviewmodel.listOfargument.observe(viewLifecycleOwner, Observer { argument ->
            homeviewmodel.updateListofArgument(argument)
            Log.i("HomeFragment","Il contenuto della lista argomenti è:$argument")
        })

        //inizializzo il viewmodel relativo al fragment argomento,
        val argomentoviewmodel=ViewModelProvider(this).get(ArgomentoViewModel::class.java)


        //creazione listener
        val listener=object :View.OnClickListener{
            override fun onClick(v: View?) {
                val argomento=v?.contentDescription.toString()
                //esegue un safe args che passa l'argomento selezionato dell'utente e una lista di tutti gli elementi contenuti nel database teoria
                val action= HomeFragmentDirections.actionHomeFragmentToArgomentoFragment(argomento,argomentoviewmodel.allTheory.toTypedArray()/*homeviewmodel.takeArgument(argomento)*/)
                view?.findNavController()?.navigate(action)
            }
        }
        Log.d("HomeFragment","inizio funzione Unlocked Buttone")
        CheckView()
        Log.d("HomeFragment","fine funzione Unlocked Buttone")

        //Asssociazione listener ai bottoni
        binding.bottoneVariabili.setOnClickListener(listener)
        binding.bottoneStringhe.setOnClickListener(listener)
        binding.bottoneArray.setOnClickListener(listener)
        binding.bottoneFunzioni.setOnClickListener(listener)
        binding.bottoneNullSafety.setOnClickListener(listener)
        binding.bottoneCicli.setOnClickListener(listener)
        binding.bottoneClassi.setOnClickListener(listener)
        binding.bottoneEreditariet.setOnClickListener(listener)
        binding.bottoneLambda.setOnClickListener(listener)



        return binding.root
    }


    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return NavigationUI.onNavDestinationSelected(item,requireView().findNavController())||super.onOptionsItemSelected(item)
    }

    //in base all'argomento passato, modifica il relativo bottone
    private fun UnlockButton(argomento:String){
        Log.d("HomeFragment","entro funzione Unlocked Buttone")
        when(argomento){
            "Stringhe" -> EnableButton(binding.bottoneStringhe)
            "Condizioni e Cicli" -> EnableButton(binding.bottoneCicli)
            "Funzioni" -> EnableButton(binding.bottoneFunzioni)
            "Null-Safety" -> EnableButton(binding.bottoneNullSafety)
            "Array e Collection" -> EnableButton(binding.bottoneArray)
            "Classi" -> EnableButton(binding.bottoneClassi)
            "Ereditarietà" -> EnableButton(binding.bottoneEreditariet)
            "Lambda Functions" -> EnableButton(binding.bottoneLambda)

        }
    }
    //Cambia il colore del bottone e abilita il click del bottone
    private fun EnableButton(v: View){
        Log.d("HomeFragment","entro funzione Enable Button")
        v.apply{

            //setBackgroundColor(getContext().getResources().getColor(R.color.secondaryColor))
            setBackgroundColor(ContextCompat.getColor(context, R.color.secondaryColor))
            setEnabled(true)
        }
    }
    //controlla la lista di argomenti e  per ogni elemento che ha il valore .unlocked true o uguale ad 1, ne modifica la visualizzazione del bottone relativo a quell'argomento
    private fun CheckView(){

        for(element in homeviewmodel.listargomenti){
            //argomento.unlocked è un intero che funfe da boolean settando  con 1 true e 0 false
            if(element.unlocked==1)
                UnlockButton(element.cod_argomento)
        }

    }


}