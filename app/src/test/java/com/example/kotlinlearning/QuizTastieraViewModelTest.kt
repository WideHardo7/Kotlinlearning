package com.example.kotlinlearning

import android.app.Application
import android.util.Log
import com.example.kotlinlearning.database.argomenti.Argument
import com.example.kotlinlearning.viewmodel.QuizTastieraViewModel
import org.junit.Assert.*
import org.junit.Before
import org.junit.Rule


import org.junit.Test
import org.mockito.Mock

class QuizTastieraViewModelTest {


    private lateinit var quizTastieraViewModel: QuizTastieraViewModel

    @Before
    fun setup(){
        quizTastieraViewModel= QuizTastieraViewModel(Application())
    }

    @Test
    fun correctAnswerTest() {
        quizTastieraViewModel.risposte="risposta corretta"
        val input:String="risposta corretta"
        val expected=1
        var rispostareg= quizTastieraViewModel.correctAnswer(input)
        var output= quizTastieraViewModel.nrispcorrette
        assertEquals(expected,output)
    }

}