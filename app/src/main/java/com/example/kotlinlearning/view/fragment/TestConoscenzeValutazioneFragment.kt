package com.example.kotlinlearning.view.fragment

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import com.example.kotlinlearning.R
import com.example.kotlinlearning.databinding.TestConoscenzeValutazioneBinding
import com.example.kotlinlearning.view.activity.MainActivity


class TestConoscenzeValutazioneFragment : Fragment() {

    private lateinit var binding: TestConoscenzeValutazioneBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding= DataBindingUtil.inflate(inflater,
            R.layout.test_conoscenze_valutazione,container,false)
        var intent = Intent(activity, MainActivity::class.java)
        binding.bottoneHome.setOnClickListener{  view : View -> startActivity(intent) }
        return binding.root
    }



}