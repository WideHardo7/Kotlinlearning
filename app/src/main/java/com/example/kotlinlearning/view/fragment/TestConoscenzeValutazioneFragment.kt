package com.example.kotlinlearning.view.fragment

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.navArgs
import com.example.kotlinlearning.R
import com.example.kotlinlearning.databinding.TestConoscenzeValutazioneBinding
import com.example.kotlinlearning.view.activity.MainActivity
import com.example.kotlinlearning.viewmodel.TestConoscenzeValutazioneViewModel


class TestConoscenzeValutazioneFragment : Fragment() {

    private lateinit var binding: TestConoscenzeValutazioneBinding
    lateinit var  testConoscenzeValutazioneViewModel: TestConoscenzeValutazioneViewModel
    val args: TestConoscenzeValutazioneFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        // Inflate the layout for this fragment
        binding= DataBindingUtil.inflate(inflater, R.layout.test_conoscenze_valutazione,container,false)
        //inizializzo il viewmodel relativo a questo fragment
        testConoscenzeValutazioneViewModel= ViewModelProvider(this).get(TestConoscenzeValutazioneViewModel::class.java)
        setLayout()

        var intent = Intent(activity, MainActivity::class.java)
        binding.bottoneHome.setOnClickListener{  view : View -> startActivity(intent) }
        return binding.root
    }

    private fun setLayout() {

        binding.risultatoConoscenze.text="${args.numerorispostecorrette}/${testConoscenzeValutazioneViewModel.numdomtestconosc}"
        var risultato= testConoscenzeValutazioneViewModel.checkandUpdateResult(args.numerorispostecorrette)
        when(risultato){
            3 -> {binding.andamentoConoscenze.setText(R.string.andamento_conoscenze_eccellente)
            binding.livelloConoscenze.setText(R.string.livello_conoscenze_Eccellente)}
            2 -> {binding.andamentoConoscenze.setText(R.string.andamento_conoscenze_ottimo)
                binding.livelloConoscenze.setText(R.string.livello_conoscenze_Avanzato)}
            1 -> {binding.andamentoConoscenze.setText(R.string.andamento_conoscenze_buono)
                binding.livelloConoscenze.setText(R.string.livello_conoscenze_intermedio)}
            0 -> {binding.andamentoConoscenze.setText(R.string.andamento_conoscenze_discreto)
                binding.livelloConoscenze.setText(R.string.livello_conoscenze_base)}

        }
    }


}