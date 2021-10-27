package com.example.kotlinlearning.view.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.navigation.findNavController
import com.example.kotlinlearning.R
import com.example.kotlinlearning.databinding.CompletamentoQuizBinding


/**
 * A simple [Fragment] subclass.
 * Use the [CompletamentoQuizFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class CompletamentoQuizFragment : Fragment() {

    lateinit var binding: CompletamentoQuizBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding= DataBindingUtil.inflate(inflater,R.layout.completamento_quiz,container,false)
        binding.bHome.setOnClickListener { view : View -> view.findNavController().navigate(R.id.action_completamentoQuizFragment_to_homeFragment) }
        binding.bTryAgain.setOnClickListener { view : View -> view.findNavController().navigate(R.id.action_completamentoQuizFragment_to_argomentoFragment) }
        return binding.root
    }


}