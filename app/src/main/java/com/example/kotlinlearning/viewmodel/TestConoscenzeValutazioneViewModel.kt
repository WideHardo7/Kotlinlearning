package com.example.kotlinlearning.viewmodel

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.kotlinlearning.database.AppDatabase
import com.example.kotlinlearning.database.argomenti.Argument
import com.example.kotlinlearning.repository.ArgumentRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class TestConoscenzeValutazioneViewModel(application: Application): AndroidViewModel(application),NumeroDomande {

    val repository:ArgumentRepository
    
    val listadiArgomentiIniziali= listOf<Argument>(Argument("Variabili",1,0,1),
        Argument("Stringhe",2,0,1),
        Argument("Condizioni e Cicli",3,0,1),
        Argument("Funzioni",4,0,1),
        Argument("Null-Safety",5,0,1),
        Argument("Array e Collection",6,0,1),
        Argument("Classi",7,0,1),
        Argument("Ereditarietà",8,0,1),
        Argument("Lambda Functions",9,0,1))

    override val numdomtestconosc: Int
        get() = super.numdomtestconosc

init{
    val argumentdao = AppDatabase.getInstance(application).argumentDao()
    repository = ArgumentRepository(argumentdao)
}

    fun insertMoreArgument(vararg argomento: Argument) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.insertMoreArgument(*argomento)
        }
    }
    //Funzione che aggiorna il database con gli argomenti sbloccati dall'utente, in base
    //al numro di risposte esatte fornite, e che inoltre fornisce un valore int che viene utilizzato
    //per aggiornare il layout in base al risultato ottenuto
    fun checkandUpdateResult(nrisposte:Int):Int{
        var result=0
        when(nrisposte){
            //se l'utente ha azzeccato tutte le domande sblocco tutti gli argomenti
            numdomtestconosc -> {insertMoreArgument(*scegliArgomentidaAggiornare(9))
                                            result=3
            Log.i("TestConoscValuViewModel","L'utente ha sbloccato TUTTI gli argomenti")}
            //se l'utente ha azzeccato i 2/3(incluso) o più delle domande allora sblocca tutti gli argomenti,
            // esclusi quelli del livello avanzato
            in (numdomtestconosc*2/3)..(numdomtestconosc-1) -> {insertMoreArgument(*scegliArgomentidaAggiornare(7))
                                                                result=2
                Log.i("TestConoscValuViewModel","L'utente ha sbloccato tutti gli argomenti del livello intermedio")}
            //se l'utente ha risposto correttamente ad un numero di domande comprese tra 1/3(incluso) e i 2/3 -1,
            // allora sblocca solo gli argomenti del livello base
            in (numdomtestconosc/3)..((numdomtestconosc*2/3)-1) ->{insertMoreArgument(*scegliArgomentidaAggiornare(4))
                                                                    result=1
                Log.i("TestConoscValuViewModel","L'utente ha sbloccato TUTTI gli argomenti del livello base")}
            else -> Log.i("TestConoscValuViewModel","L'utente non ha sbloccato nessun argomento")

        }
        return result
    }

    private fun scegliArgomentidaAggiornare(numeroelementi: Int): Array<out Argument> {
        var n=0
        var argomenti:MutableList<Argument> = mutableListOf<Argument>()
        while (n < numeroelementi){
            argomenti.add(listadiArgomentiIniziali.get(n))
            n++
        }
        return argomenti.toTypedArray()

    }


}