package com.example.kotlinlearning.viewmodel

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.kotlinlearning.database.AppDatabase
import com.example.kotlinlearning.database.domande.DomandeInserimento
import com.example.kotlinlearning.database.domande.DomandeMultiple
import com.example.kotlinlearning.repository.DomandeInserimentoRepository
import kotlinx.coroutines.launch

class QuizBottoneViewModel(application: Application): AndroidViewModel(application), GestioneDomande, NumeroDomande{

    //Variabile che contiene le domande relative all'argomento selezionato'
    var domande:MutableList<DomandeMultiple> = mutableListOf<DomandeMultiple>()

     val repository:DomandeInserimentoRepository
     //lista che contiene le domande input od a inserimento, vengono eseguite in questo fragment e poi passate
     // come safe args al fragment dopo per, dare il tempo di eseguire e completare la query, in modo da visualizzare
     // il successivo layout senza problemi
     var allInputQuestion:List<DomandeInserimento> = listOf<DomandeInserimento>()

    //Variabile che contiene la domanda che viene eseguita in questo momento
    lateinit var domandaAttuale:DomandeMultiple
    //variabile che contiene le risposte multiple della domanda corrente
     var  risposte: MutableList<String> = mutableListOf<String>()
    // contiene la posizione della domanda scelta e viene utilizzato per avanzare
    private var indiceDomande:Int=0
    //numero domande
    override val ndomandemulti: Int
        get() = super.ndomandemulti

    //conta le risposte giuste date dall'utente
     override var nrispcorrette:Int=0

    init {
        val domandeinserimentodao=AppDatabase.getInstance(application).domandeinserimentoDao()
        repository= DomandeInserimentoRepository((domandeinserimentodao))
        domandeInput()
    }

    suspend fun getInputQuestion(){
        allInputQuestion=repository.getInputQuestion()
        Log.i("QuizBottoneViewModel","La lista di domandeinserimento è stata estratta con successo, ecco il contenuto: ${allInputQuestion}")
    }

    //ho necessita di inizializzare la variabile globale allInputQuestion, ma getInputQuestion() è una funzione suspend e
    // quindi uso un viewmodelScope per usare la funzione
    fun domandeInput(){
        viewModelScope.launch{
            getInputQuestion()
        }
    }

    //prende tutte le domande e scegli solo quelle relative all'argomento selezionato
     fun selectQuestionfromArgument(argomento: String,allQuestionm:List<DomandeMultiple>) {

        for(element in allQuestionm){
            if(element.cod_argomento==argomento)
                domande.add(element)
        }
        Log.d("QuizBottoneViewModel","Le domande sono queste: $domande")
    }
    //controlla che la risposta premuta dall'utente, corrisponda con la risposta giusta della domanda correlata
    // e incrementa l'indice delle domande eseguite correttamente e l'indice delle domande effettuate
    fun correctAnswer(indexanswer: Int) {

        if (domandaAttuale.risposta_giusta==risposte[indexanswer]) {
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
     override fun checkquestionNumber():Boolean= indiceDomande< ndomandemulti

}