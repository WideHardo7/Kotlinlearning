package com.example.kotlinlearning.viewmodel

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.kotlinlearning.database.AppDatabase
import com.example.kotlinlearning.database.domande.DomandeMultiple
import com.example.kotlinlearning.database.teoria.Teoria
import com.example.kotlinlearning.database.teoria.TeoriaDao
import com.example.kotlinlearning.repository.DomandeMultipleRepository
import com.example.kotlinlearning.repository.TeoriaRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

 public class ArgomentoViewModel( application: Application):AndroidViewModel(application) {


    private val repository:TeoriaRepository
     var allTheory= listOf<Teoria>()
     private val repository1: DomandeMultipleRepository
     var allMultiQuestion= listOf<DomandeMultiple>()


    init{
        //prendo un istanza del database riguardante la tabella Teoria e ci inizializzo la relativa repository
        val teoriaDao= AppDatabase.getInstance(application).teoriaDao()
        repository= TeoriaRepository(teoriaDao)
        //prendo un istanza del database riguardante la tabella DomandeMultiple e ci inizializzo la relativa repository
        //ho necessità di inizializzare qui la lista di domande per avere il tempo necessario di eseguire e
        // completare la query per poi visualizzarla a video nel fragment successivo
        val domandemultidao= AppDatabase.getInstance(application).domandemultipleDao()
        repository1= DomandeMultipleRepository(domandemultidao)
        //Inizializzo la variabile allTheory
        Teoria()
        //Inizializzo la variabile allMultiQuestion
        domandeMulti()
    }

     suspend fun getTheory(){
         allTheory=repository.getTheory()
         Log.i("ArgomentoViewModel","La lista di oggetti teoria è stata estratta con successo")
         }

     //ho necessita di inizializzare la variabile globale alltheory, ma getTheory() è una funzione suspend e
     // quindi uso un viewmodelScope per usare la funzione
     fun Teoria() {
         viewModelScope.launch {
             getTheory()
         }
     }
         suspend fun getMultipleQuestion(){
             allMultiQuestion=repository1.getMultipleQuestion()
             Log.i("ArgomentoViewModel","La lista di domandemultiple è stata estratta con successo, ecco il contenuto: $allMultiQuestion")
         }

         //ho necessita di inizializzare la variabile globale allMultiQuestion, ma getMultiQuestion() è una funzione suspend e
         // quindi uso un viewmodelScope per usare la funzione
         fun domandeMulti(){
             viewModelScope.launch{
                 getMultipleQuestion()
             }
         }
 }










