package com.example.kotlinlearning.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.kotlinlearning.database.AppDatabase
import com.example.kotlinlearning.database.argomenti.Argument
import com.example.kotlinlearning.database.domande.DomandeMultiple
import com.example.kotlinlearning.repository.DomandeMultipleRepository

class QuizTestConoscenzeViewModel(application: Application): AndroidViewModel(application), GestioneDomande {
    override var nrispcorrette: Int=0
    val repository:DomandeMultipleRepository
    var listadidomandemultiple: LiveData<List<DomandeMultiple>> = MutableLiveData<List<DomandeMultiple>>()
    var domande:MutableList<DomandeMultiple> = mutableListOf<DomandeMultiple>()

    //Variabile che contiene la domanda che viene eseguita in questo momento
    lateinit var domandaAttuale:DomandeMultiple

    //variabile che contiene le risposte multiple della domanda corrente
    var  risposte: MutableList<String> = mutableListOf<String>()

    // contiene la posizione della domanda scelta e viene utilizzato per avanzare
    private var indiceDomande:Int=0

    val namearguments:List<String> = listOf("Variabili","Stringhe","Condizioni e Cicli","Funzioni","Null-Safety","Array e Collection","Classi","Ereditarietà","Lambda Functions")

    init{
        val domandemultipleDao= AppDatabase.getInstance(application).domandemultipleDao()
        repository= DomandeMultipleRepository(domandemultipleDao)
        listadidomandemultiple=repository.readAllMultipleQuestion()
    }

    fun getAllMulltipleQuestion(): LiveData<List<DomandeMultiple>>{
        return listadidomandemultiple
    }

    fun selectQuestionfromArgument(dom:MutableList<DomandeMultiple>){
        dom.shuffle()
        for(e in namearguments){
            var n:  Int=0
            for(element in dom){
                if(element.cod_argomento== e && n < 2) {
                    domande.add(element)
                    n++
                }
                }


            }
        }





    override fun mischiaDomande() {
        TODO("Not yet implemented")
    }

    override fun setQuestion() {
        TODO("Not yet implemented")
    }

    override fun checkquestionNumber(): Boolean {
        TODO("Not yet implemented")
    }
}