package com.example.kotlinlearning.view.fragment

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import com.example.kotlinlearning.R
import com.example.kotlinlearning.databinding.TestConoscenzeBinding
import com.example.kotlinlearning.view.activity.MainActivity
import com.example.kotlinlearning.view.activity.MainActivity2
import com.example.kotlinlearning.viewmodel.TestConoscenzeViewModel


class TestConoscenzeFragment : Fragment() {

    private lateinit var binding: TestConoscenzeBinding
    lateinit var testConoscenzeViewModel: TestConoscenzeViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding=DataBindingUtil.inflate(inflater, R.layout.test_conoscenze,container,false)

        testConoscenzeViewModel=ViewModelProvider(this).get(TestConoscenzeViewModel::class.java)
        Log.i("TestConoscenzeFragment","Dentro TestConoscenzeFragment, dopo aver inizializzato il viewmodel ")

        //premendo il bottone procedi, viene inviata una lista di domande selezionate per effettuare il test
        binding.bProcedi.setOnClickListener{  view : View ->

            val action= TestConoscenzeFragmentDirections.actionTestConoscenzeToQuizTestConoscenze(testConoscenzeViewModel.selectQuestionfromArgument().toTypedArray())
            view.findNavController().navigate(action)
        }

        //se l'utente decide di saltarlo viene fatto un intent alla mainActivity
        var intent = Intent(activity, MainActivity::class.java)
        binding.bSalta.setOnClickListener{  view : View ->

            startActivity(intent)
        activity?.finish()}

        return binding.root
    }


}