package com.example.kotlinlearning.viewmodel

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import com.example.kotlinlearning.database.domande.DomandeMultiple

class QuizTestConoscenzeViewModel(application: Application): AndroidViewModel(application), GestioneDomande,NumeroDomande {

    //val repository:DomandeMultipleRepository



    //lista di domande selezionate per eseguire il test delle conoscenze
    var domande:MutableList<DomandeMultiple> = mutableListOf<DomandeMultiple>()
    set(value) {
        field=value
    }

    //Numero di risposte corrette
    override var nrispcorrette: Int=0

    //numero totale delle domande del test conoscenze
    override val numdomtestconosc: Int
        get() = super.numdomtestconosc

    //Variabile che contiene la domanda che viene eseguita in questo momento
    lateinit var domandaAttuale:DomandeMultiple

    //variabile che contiene le risposte multiple della domanda corrente
    var  risposte: MutableList<String> = mutableListOf<String>()

    // contiene la posizione della domanda scelta e viene utilizzato per avanzare
     var indiceDomande:Int=0


    init{

    }


    //mischia le domande e setta inidice a zero, in modo che verra sceltà
    // la domanda che si trova ora in posizione [0]
    override fun mischiaDomande() {
        domande.shuffle()
        indiceDomande=0
        setQuestion()
    }

    //inizializza la domanda corrente e le sue relative risposte
    override fun setQuestion() {
        domandaAttuale= domande[indiceDomande]
        risposte.apply {
            clear()
            add(domandaAttuale.risposta_1)
            add(domandaAttuale.risposta_2)
            add(domandaAttuale.risposta_3)
            add(domandaAttuale.risposta_giusta)
            shuffle()
        }
        Log.d("QuizTestConoscViewmodel","Queste sono le risposte della domanda :${domandaAttuale.testo_domanda}, $risposte")

    }
    //controlla che la risposta premuta dall'utente, corrisponda con la risposta giusta della domanda correlata
    // e incrementa l'indice delle domande eseguite correttamente e l'indice delle domande effettuate
    fun correctAnswer(indexanswer: Int) {

        if (domandaAttuale.risposta_giusta==risposte[indexanswer]) {
            nrispcorrette++
        }
        indiceDomande++
    }

    //Controlla se le domande eseguite eccedono o no il numero di domande prestabilite.
    override fun checkquestionNumber():Boolean= indiceDomande< domande.size
}