package com.example.kotlinlearning.viewmodel

import android.app.Application
import android.util.Log
import androidx.lifecycle.*
import com.example.kotlinlearning.database.AppDatabase
import com.example.kotlinlearning.database.argomenti.Argument
import com.example.kotlinlearning.database.domande.DomandeInserimento
import com.example.kotlinlearning.database.domande.DomandeMultiple
import com.example.kotlinlearning.repository.ArgumentRepository
import com.example.kotlinlearning.repository.DomandeInserimentoRepository
import kotlinx.coroutines.launch

class QuizTastieraViewModel(application: Application): AndroidViewModel(application), GestioneDomande,NumeroDomande {

    //Lista di DomandeInserimento che  contiene le domande relative solo a quell'argomento
    var domande: MutableList<DomandeInserimento> = mutableListOf<DomandeInserimento>()

    //Variabile che contiene la domanda che viene eseguita in questo momento
    lateinit var domandaAttuale: DomandeInserimento

    // contiene la posizione della domanda scelta e viene utilizzato per avanzare
    private var indiceDomande: Int = 0

    //numero di domande
    override val ndomandeinput: Int
        get() = super.ndomandeinput

    //variabile che contiene la risposta della domanda corrente
    var risposte: String = " "

    //conta le risposte giuste date dall'utente
    override var nrispcorrette: Int = 0

    val repository: ArgumentRepository

    //Lista di oggetti argument che passo al Fragment CompletamentoQuiz
    var listadiargomenti: List<Argument> = mutableListOf<Argument>()

    init {
        val argumentsDao = AppDatabase.getInstance(application).argumentDao()
        repository = ArgumentRepository(argumentsDao)
        viewModelScope.launch {
            getTuttiArgomenti()
        }
    }

    suspend fun getTuttiArgomenti() {
        listadiargomenti = repository.getAllArgumentwithCoroutine()
        Log.d(
            "CompletamentViewModel",
            "La lista di oggetti argument è stata estratta con successo, eccola :$listadiargomenti"
        )
    }

        //prende tutte le domande e scegli solo quelle relative all'argomento selezionato
        fun selectQuestionfromArgument(argomento: String, allQuestionm: List<DomandeInserimento>) {

            for (element in allQuestionm) {
                if (element.cod_argomento == argomento)
                    domande.add(element)
            }
            Log.d("QuizTastieraViewModel", "Le domande sono queste: $domande")
        }


        override fun mischiaDomande() {
            domande.shuffle()
            indiceDomande = 0
            setQuestion()
        }

        override fun setQuestion() {
            domandaAttuale = domande[indiceDomande]
            risposte = domandaAttuale.risposta_giusta
        }

        //controlla che la risposta inserita dall'utente, corrisponda con la risposta giusta della domanda correlata
        // e incrementa l'indice delle domande eseguite
        fun correctAnswer(useranswer: String) {
            if (risposte.equals(useranswer) || risposte.equals(useranswer.capitalize())) {
                nrispcorrette++
            }
            indiceDomande++
        }

        override fun checkquestionNumber(): Boolean = indiceDomande < ndomandeinput
}
