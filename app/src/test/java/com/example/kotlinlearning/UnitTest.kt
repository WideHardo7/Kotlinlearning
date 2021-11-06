package com.example.kotlinlearning

import android.app.Application
import android.content.Context
import android.util.Log
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.test.core.app.ApplicationProvider
import com.example.kotlinlearning.database.argomenti.Argument
import com.example.kotlinlearning.database.domande.DomandeMultiple
import com.example.kotlinlearning.repository.ArgumentRepository
import com.example.kotlinlearning.view.fragment.TeoriaFragmentArgs
import com.example.kotlinlearning.viewmodel.QuizBottoneViewModel
import com.example.kotlinlearning.viewmodel.QuizTastieraViewModel
import org.junit.Assert.*
import org.junit.Before
import org.junit.Rule


import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runner.manipulation.Ordering
import org.junit.runners.JUnit4
import org.mockito.Mock
import org.robolectric.RobolectricTestRunner

@RunWith(RobolectricTestRunner::class)
class UnitTest {

    @Mock
    private lateinit var quizTastieraViewModel: QuizTastieraViewModel
    private val app= ApplicationProvider.getApplicationContext<Application>()

    @Before
    fun setup(){
        quizTastieraViewModel= QuizTastieraViewModel(app)

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

@RunWith(RobolectricTestRunner::class)
class UnitTest2 {

    @Mock
    private lateinit var quizBottoneViewModel: QuizBottoneViewModel
    private val app= ApplicationProvider.getApplicationContext<Application>()

    @Before
    fun setup(){
        quizBottoneViewModel= QuizBottoneViewModel(app)

    }

    @Test
    fun setQuestionTest(){
        quizBottoneViewModel.domandaAttuale= DomandeMultiple(1,"2+2=?",
            "Addizioni","3","1","5","4")
        quizBottoneViewModel.risposte.apply {
            clear()
            add(quizBottoneViewModel.domandaAttuale.risposta_1)
            add(quizBottoneViewModel.domandaAttuale.risposta_2)
            add(quizBottoneViewModel.domandaAttuale.risposta_3)
            add(quizBottoneViewModel.domandaAttuale.risposta_giusta)
        }
        assertEquals(quizBottoneViewModel.domandaAttuale.risposta_1 , quizBottoneViewModel.risposte[0])
        assertEquals(quizBottoneViewModel.domandaAttuale.risposta_2, quizBottoneViewModel.risposte[1])
        assertEquals(quizBottoneViewModel.domandaAttuale.risposta_3 , quizBottoneViewModel.risposte[2])
        assertEquals(quizBottoneViewModel.domandaAttuale.risposta_giusta,quizBottoneViewModel.risposte[3])
    }
}