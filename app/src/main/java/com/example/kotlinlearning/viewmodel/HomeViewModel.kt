package com.example.kotlinlearning.viewmodel

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import kotlinx.coroutines.flow.Flow
import androidx.lifecycle.viewModelScope
import com.example.kotlinlearning.database.AppDatabase
import com.example.kotlinlearning.database.argomenti.Argument
import com.example.kotlinlearning.repository.ArgumentRepository
import kotlinx.coroutines.launch

class HomeViewModel( application: Application): AndroidViewModel(application) {
     private val repository:ArgumentRepository
     //val allArgument:LiveData<List<Argument>>
     var listargomenti= listOf<Argument>()
    var listOfargument:LiveData<List<Argument>> = MutableLiveData<List<Argument>>()

     //var tuttiargomenti: List<Argument> = listOf<Argument>()
     init{
         val argomentoDao= AppDatabase.getInstance(application).argumentDao()
         repository=ArgumentRepository(argomentoDao)
         listOfargument= repository.readallargument()
          /*allArgument = liveData<List<Argument>> {
             val argomenti= repository.getAllArgument()
             emit(argomenti)
         }*/
         //Log.i("HomeViewmodel","dentro init e dentro a allArgument:${allArgument.getValue()}")
         //Argomenti()

     }
     fun getAllArgument(): LiveData<List<Argument>>{
        return listOfargument
    }

    fun Argomenti(){
        viewModelScope.launch {
            getAllArgument()
        }

    }

    fun updateListofArgument(argomenti:List<Argument>){
        this.listargomenti=argomenti
    }

    /*
    //funzione che serve per estrarre un particolare argomento di tipo Argomento,passato come parametro alla funzione, dalla lista di tutti gli argomenti
    fun takeArgument(argomento:String):Argument{
         var argomentocorrente:Argument
        for(element in getAllArgument()) {
            if (element.cod_argomento == argomento)
                argomentocorrente = element
        }
        return argomentocorrente

        }*/

    }
