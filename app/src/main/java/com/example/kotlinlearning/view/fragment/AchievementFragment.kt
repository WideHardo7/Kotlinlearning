package com.example.kotlinlearning.view.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import com.example.kotlinlearning.R
import com.example.kotlinlearning.databinding.AchievementsBinding


/**
 * A simple [Fragment] subclass.
 * Use the [AchievementFragment.newInstance] factory method to
 * create an instance of this fragment.
 */



class AchievementFragment : Fragment() {

    private lateinit var binding: AchievementsBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding= DataBindingUtil.inflate(inflater, R.layout.achievements,container,false)
        binding.obPbVariabili.max=2
        binding.obPbVariabili.progress=1
        return binding.root

    }


}