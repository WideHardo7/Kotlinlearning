package com.example.kotlinlearning.view.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import android.widget.TextView
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.kotlinlearning.R
import com.example.kotlinlearning.databinding.AchievementsBinding
import com.example.kotlinlearning.viewmodel.AchievementViewModel


/**
 * A simple [Fragment] subclass.
 * Use the [AchievementFragment.newInstance] factory method to
 * create an instance of this fragment.
 */



class AchievementFragment : Fragment() {

    private lateinit var binding: AchievementsBinding
    lateinit var achievementviewmodel:AchievementViewModel
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding= DataBindingUtil.inflate(inflater, R.layout.achievements,container,false)
        achievementviewmodel=ViewModelProvider(this).get(AchievementViewModel::class.java)

        achievementviewmodel.getAllArgument().observe( viewLifecycleOwner, Observer { argument ->
            for (e in argument){
                changeRelativeView(e.cod_argomento,e.score)
            }


        })


        return binding.root

    }

    private fun changeRelativeView(codArgomento: String, score: Int) {
        when (codArgomento){
            "Variabili" -> updateProgressBar(binding.obPbVariabili,binding.obTxpbVariabili,score)
            "Stringhe" -> updateProgressBar(binding.obPbStringhe,binding.obTxpbStringhe,score)
            "Condizioni e Cicli" -> updateProgressBar(binding.obPbCicli,binding.obTxpbCicli,score)
            "Funzioni" -> updateProgressBar(binding.obPbFunzioni,binding.obTxpbFunzioni,score)
            "Null-Safety" -> updateProgressBar(binding.obPbNull,binding.obTxpbNull,score)
            "Array e Collection" -> updateProgressBar(binding.obPbArray,binding.obTxpbArray,score)
            "Classi" -> updateProgressBar(binding.obPbClassi,binding.obTxpbClassi,score)
            "Ereditarietà" -> updateProgressBar(binding.obPbEreditariet,binding.obTxpbEreditariet,score)
            "Lambda Functions" -> updateProgressBar(binding.obPbLambda,binding.obTxpbLambda,score)
        }

    }
    private fun updateProgressBar(v1:ProgressBar,v2:TextView,score:Int){
        v1.setMax(achievementviewmodel.ndomandetot)
            v1.setProgress(score)

        v2.setText("$score/${achievementviewmodel.ndomandetot} ")
    }



}