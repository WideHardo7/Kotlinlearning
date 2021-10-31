package com.example.kotlinlearning.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import com.example.kotlinlearning.database.AppDatabase
import com.example.kotlinlearning.database.domande.DomandeInserimento
import com.example.kotlinlearning.database.domande.DomandeMultiple
import com.example.kotlinlearning.repository.DomandeInserimentoRepository

class QuizTastieraViewModel(application: Application): AndroidViewModel(application), GestioneDomande {

    val repository: DomandeInserimentoRepository
    //variabile che contiene una stringa che identifica l'argomento scelto dall'utente
    // e che viene utilizzato per chiamare una query getInsertQuestionfromArgument
    private   var currentargument: MutableLiveData<String> = MutableLiveData<String>()
    fun setCurrentArgoment(argument:String){
        currentargument.value= argument
    }
    //Lista di DomandeInserimento che  contiene le domande relative solo a quell'argomento
    var domande: MutableList<DomandeInserimento> = mutableListOf<DomandeInserimento>()
    set(value){
        field=value
    }
    //Variabile che contiene la domanda che viene eseguita in questo momento
    lateinit var domandaAttuale: DomandeInserimento


    // contiene la posizione della domanda scelta e viene utilizzato per avanzare
    private var indiceDomande:Int=0
    //si è deciso di avere 1 domanda di questo tipo,
    // ma è possibile modificare il numero per incrementare  il numero di domande da visualizzare,
    // una volto incrementato il numero di domande nel database
    val numerodom=1



    //variabile che contiene la risposta della domanda corrente
    var risposte: String = " "
    //conta le risposte giuste date dall'utente
    override var nrispcorrette: Int=0

    init {
        val domandeinserimentodao= AppDatabase.getInstance(application).domandeinserimentoDao()
        repository= DomandeInserimentoRepository(domandeinserimentodao)
    }

    //livedata che riceve la lista di domandeinserimento ottenute dalla query lanciata
    val domandelivedata: LiveData<List<DomandeInserimento>> = Transformations.switchMap(currentargument) { argomento -> repository.getInsertQuestionfromArgument(argomento)}






    override fun mischiaDomande() {
        domande.shuffle()
        indiceDomande=0
        setQuestion()
    }

    override fun setQuestion() {
        domandaAttuale= domande[indiceDomande]
        risposte=domandaAttuale.risposta_giusta
    }

     fun correctAnswer(useranswer:String) {
        if(risposte.equals(useranswer) || risposte.equals(useranswer.capitalize()))
            nrispcorrette++
    }
     override fun checkquestionNumber():Boolean= indiceDomande< numerodom
}