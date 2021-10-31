package com.example.kotlinlearning.viewmodel

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import com.example.kotlinlearning.database.domande.DomandeMultiple

class QuizBottoneViewModel(application: Application): AndroidViewModel(application), GestioneDomande{

    //Variabile che contiene le domande relative all'argomento selezionato'
    var domande:MutableList<DomandeMultiple> = mutableListOf<DomandeMultiple>()

     /*var alldomandem:List<DomandeMultiple> = listOf<DomandeMultiple>()
    set(value){
        field=value
    }*/
    /*var name_argomento:String= " "
    set(value){
        field=value
    }*/

    //Variabile che contiene la domanda che viene eseguita in questo momento
    lateinit var domandaAttuale:DomandeMultiple
    //variabile che contiene le risposte multiple della domanda corrente
     var  risposte: MutableList<String> = mutableListOf<String>()
    // contiene la posizione della domanda scelta e viene utilizzato per avanzare
    private var indiceDomande:Int=0
    //si è deciso di avere 4 domande di questo tipo,
    // ma è possibile modificare il numero per incrementare  il numero di domande da visualizzare
     val numerodom=4
    //conta le risposte giuste date dall'utente
     override var nrispcorrette:Int=0

    //prende tutte le domande e scegli solo quelle relative all'argomento selezionato
     fun selectQuestionfromArgument(argomento: String,allQuestionm:List<DomandeMultiple>) {

        for(element in allQuestionm){
            if(element.cod_argomento==argomento)
                domande.add(element)
        }
        Log.d("QuizBottoneViewModel","Le domande sono queste: $domande")
    }
    //controlla che la risposta premuta dall'utente, corrisponda con la risposta giusta della domanda correlata
    // e incrementa l'indice delle domande eseguite
    fun correctAnswer(indexanswer: Int) {

        if (domandaAttuale.risposta_giusta.equals(risposte[indiceDomande])) {
            nrispcorrette++
        }
        indiceDomande++
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
        Log.d("QuizBottoneViewmodel","Queste sono le risposte della domanda :${domandaAttuale.testo_domanda}, $risposte")
    }
    //Controlla se le domande eseguite eccedono o no il numero di domande prestabilite.
     override fun checkquestionNumber():Boolean= indiceDomande< numerodom

}