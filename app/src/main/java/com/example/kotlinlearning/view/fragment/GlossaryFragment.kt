package com.example.kotlinlearning.view.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import com.example.kotlinlearning.R
import com.example.kotlinlearning.databinding.GlossarioBinding
import com.example.kotlinlearning.viewmodel.ArgomentoViewModel
import com.example.kotlinlearning.viewmodel.HomeViewModel


/**
 * A simple [Fragment] subclass.
 * Use the [GlossaryFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class GlossaryFragment : Fragment() {

    private lateinit var binding:GlossarioBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding= DataBindingUtil.inflate(inflater,
            R.layout.glossario,container,false)


    val homeviewmodel= ViewModelProvider(this).get(HomeViewModel::class.java)

    val listener=object :View.OnClickListener{
        override fun onClick(v: View?) {
            val argomento=v?.contentDescription.toString()
            //esegue un safe args che passa l'argomento selezionato dell'utente e una lista di tutti gli elementi contenuti nel database teoria
            val action= GlossaryFragmentDirections.actionGlossaryFragmentToTeoriaFragment(argomento,homeviewmodel.allTheory.toTypedArray()/*homeviewmodel.takeArgument(argomento)*/)
            view?.findNavController()?.navigate(action)
        }
    }

        binding.gBVariabili.setOnClickListener(listener)
        binding.gBStringhe.setOnClickListener(listener)
        binding.gBArray.setOnClickListener(listener)
        binding.gBFunzioni.setOnClickListener(listener)
        binding.gBNull.setOnClickListener(listener)
        binding.gBCicli.setOnClickListener(listener)
        binding.gBClassi.setOnClickListener(listener)
        binding.gBEreditariet.setOnClickListener(listener)
        binding.gBLambda.setOnClickListener(listener)

        return binding.root
    }
}