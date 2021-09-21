package com.example.kotlinlearning.view.fragment

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.navigation.findNavController
import com.example.kotlinlearning.R
import com.example.kotlinlearning.databinding.TestConoscenzeBinding
import com.example.kotlinlearning.view.activity.MainActivity


class TestConoscenzeFragment : Fragment() {

    private lateinit var binding: TestConoscenzeBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding=DataBindingUtil.inflate(inflater,
            R.layout.test_conoscenze,container,false)
        binding.bProcedi.setOnClickListener{  view : View ->
           view.findNavController().navigate(R.id.action_testConoscenze_to_quizTestConoscenze) }
        var intent = Intent(activity, MainActivity::class.java)
        binding.bSalta.setOnClickListener{  view : View -> startActivity(intent) }

        return binding.root
    }


}