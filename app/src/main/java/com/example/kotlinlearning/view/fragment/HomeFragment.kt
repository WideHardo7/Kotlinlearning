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
import androidx.navigation.findNavController
import androidx.navigation.ui.NavigationUI
import com.example.kotlinlearning.R
import com.example.kotlinlearning.databinding.HomeBinding


/**
 * A simple [Fragment] subclass.
 * Use the [HomeFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class HomeFragment : Fragment() {

    private lateinit var binding: HomeBinding


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        //val view=inflater.inflate(R.layout.home, container, false)
        binding=DataBindingUtil.inflate(inflater,
            R.layout.home,container,false)

        //creazione listener
        val listener=object :View.OnClickListener{
            override fun onClick(v: View?) {
                val argomento=v?.contentDescription.toString()
                val action= HomeFragmentDirections.actionHomeFragmentToArgomentoFragment(argomento)
                view?.findNavController()?.navigate(action)
            }
        }
        Log.d("HomeFragment","inizio funzione Unlocked Buttone")
        UnlockButton("Stringhe")
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
    //Cambia il colore del bottone e abilita il bottone
    private fun EnableButton(v: View){
        Log.d("HomeFragment","entro funzione Enable Button")
        v.apply{

            //setBackgroundColor(getContext().getResources().getColor(R.color.secondaryColor))
            setBackgroundColor(ContextCompat.getColor(context, R.color.secondaryColor))
            setEnabled(true)
        }
    }


}