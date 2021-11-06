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
import com.example.kotlinlearning.database.teoria.Teoria
import com.example.kotlinlearning.repository.ArgumentRepository
import com.example.kotlinlearning.repository.TeoriaRepository
import kotlinx.coroutines.launch

class HomeViewModel( application: Application): AndroidViewModel(application) {
     private val repository:ArgumentRepository
    private val repository1: TeoriaRepository

     //lista di oggetti teoria che uso per inizializzare la textview del fragment successivo (ArgomentoFragment)
    var allTheory= listOf<Teoria>()


    var listargomenti= listOf<Argument>()
    var listOfargument:LiveData<List<Argument>> = MutableLiveData<List<Argument>>()

     //var tuttiargomenti: List<Argument> = listOf<Argument>()
     init{
         val argomentoDao= AppDatabase.getInstance(application).argumentDao()
         repository=ArgumentRepository(argomentoDao)
         listOfargument= repository.readallargument()

         //prendo un istanza del database riguardante la tabella Teoria e ci inizializzo la relativa repository
         val teoriaDao= AppDatabase.getInstance(application).teoriaDao()

         repository1= TeoriaRepository(teoriaDao)

         //Inizializzo la variabile allTheory
         Teoria()


     }

    suspend fun getTheory(){
        allTheory=repository1.getTheory()
        Log.i("ArgomentoViewModel","La lista di oggetti teoria è stata estratta con successo")
    }

    //ho necessita di inizializzare la variabile globale alltheory, ma getTheory() è una funzione suspend e
    // quindi uso un viewmodelScope per usare la funzione
    fun Teoria() {
        viewModelScope.launch {
            getTheory()
        }
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



    }
