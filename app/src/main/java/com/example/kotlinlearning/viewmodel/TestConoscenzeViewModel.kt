package com.example.kotlinlearning.viewmodel

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.kotlinlearning.database.AppDatabase
import com.example.kotlinlearning.database.domande.DomandeMultiple
import com.example.kotlinlearning.repository.DomandeMultipleRepository
import com.example.kotlinlearning.util.NumeroDomande
import kotlinx.coroutines.launch

class TestConoscenzeViewModel(application: Application): AndroidViewModel(application),
    NumeroDomande {
    private val repository: DomandeMultipleRepository

    //Lista di oggetti domandeMultiple che passo al fragment successivo (QuizDomandeMultiple)
    var allMultiQuestion= mutableListOf<DomandeMultiple>()

    //Lista di stringhe contenete il nome di tutti gli argomenti trattati nell'app
    private val namearguments:List<String> = listOf("Variabili","Stringhe","Condizioni e Cicli","Funzioni","Null-Safety","Array e Collection","Classi","Ereditarietà","Lambda Functions")


    init{

        //prendo un istanza del database riguardante la tabella DomandeMultiple e ci inizializzo la relativa repository
        //ho necessità di inizializzare qui la lista di domande per avere il tempo necessario di eseguire e
        // completare la query per poi visualizzarla a video nel fragment successivo
        val domandemultidao= AppDatabase.getInstance(application).domandemultipleDao()

        repository= DomandeMultipleRepository(domandemultidao)

        //Inizializzo la variabile allMultiQuestion
        domandeMulti()
    }


    suspend fun getMultipleQuestion(){
        allMultiQuestion=repository.getMultipleQuestion().toMutableList()
        Log.i("TestConoscenzeViewModel","La lista di domandemultiple è stata estratta con successo")
    }

    //ho necessita di inizializzare la variabile globale allMultiQuestion, ma getMultiQuestion() è una funzione suspend e
    // quindi uso un viewmodelScope per usare la funzione
    fun domandeMulti(){
        viewModelScope.launch{
            getMultipleQuestion()
        }
    }

    //funzione che per ogni oggetto della lista namearguments, contenete
    // i nomi di tutti gli argomenti trattati nell'app, prende due domande dalla lista di domande passate
    //e li inserisce dentro la mutablelist domande, se la variabile n è uguale a numerodomandexargomenti,
    // allora significa che ho già preso le domande che mi servivano e non è più necessario iterare gli elementi di dom,
    // perciò per evitare iterazioni inutili si è utilizzato continue
    fun selectQuestionfromArgument():MutableList<DomandeMultiple>{
        allMultiQuestion.shuffle()
        var domande:MutableList<DomandeMultiple> = mutableListOf<DomandeMultiple>()
        label@ for(e in namearguments){
            var n: Int =0
            for(element in allMultiQuestion){
                if(element.cod_argomento== e && n < numerodomandexargomenti) {
                    domande.add(element)
                    n++
                }
                if(n==numerodomandexargomenti){
                    continue@label
                }
            }


        }
        Log.i("TestConoscenzeViewmodel", "Le domande selezionate per il test sono ${domande.size} e sono le seguenti: $domande")
        return  domande
    }
}