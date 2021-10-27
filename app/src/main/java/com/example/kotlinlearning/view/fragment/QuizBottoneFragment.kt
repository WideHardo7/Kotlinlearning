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
import androidx.navigation.findNavController
import com.example.kotlinlearning.R
import com.example.kotlinlearning.databinding.QuizBottoniBinding


/**
 * A simple [Fragment] subclass.
 * Use the [QuizBottoneFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class QuizBottoneFragment : Fragment() {

    lateinit var binding: QuizBottoniBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding= DataBindingUtil.inflate(inflater, R.layout.quiz_bottoni,container,false)
        binding.bRisp1.setOnClickListener(listener)
        binding.bRisp2.setOnClickListener(listener)
        binding.bRisp3.setOnClickListener(listener)
        binding.bRisp4.setOnClickListener(listener)
        val callback= requireActivity().onBackPressedDispatcher.addCallback(this){}
        callback.remove()
        return binding.root

    }



    val listener=object :View.OnClickListener{
        var count=0
        override fun onClick(v: View?) {
            if(count<3)
            {
                //Do the refresh (⌐■_■)▄︻┻┳═一 (•_•)(•_•)(•_•)
                Log.d("ciclo im pepega","$count")
                count++
            } else {
            view?.findNavController()?.navigate(R.id.action_quizBottoneFragment_to_quizTastieraFragment)

        }

    }

}
}