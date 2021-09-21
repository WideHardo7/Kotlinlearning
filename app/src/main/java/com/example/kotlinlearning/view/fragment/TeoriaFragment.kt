package com.example.kotlinlearning.view.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.kotlinlearning.R


/**
 * A simple [Fragment] subclass.
 * Use the [TeoriaFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class TeoriaFragment : Fragment() {


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.teoria_no_quiz, container, false)
    }


}