package com.example.kotlinlearning.viewmodel

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.kotlinlearning.database.AppDatabase
import com.example.kotlinlearning.database.domande.DomandeMultiple
import com.example.kotlinlearning.repository.DomandeMultipleRepository
import kotlinx.coroutines.launch

 public class ArgomentoViewModel( application: Application):AndroidViewModel(application) {




     private val repository: DomandeMultipleRepository

     //Lista di oggetti domandeMultiple che passo al fragment successivo (QuizDomandeMultiple)
     var allMultiQuestion= listOf<DomandeMultiple>()


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
             allMultiQuestion=repository.getMultipleQuestion()
             Log.i("ArgomentoViewModel","La lista di domandemultiple è stata estratta con successo")
         }

         //ho necessita di inizializzare la variabile globale allMultiQuestion, ma getMultiQuestion() è una funzione suspend e
         // quindi uso un viewmodelScope per usare la funzione
         fun domandeMulti(){
             viewModelScope.launch{
                 getMultipleQuestion()
             }
         }

     fun selectQuestionfromArgument(argomento: String,allQuestionm:List<DomandeMultiple>): MutableList<DomandeMultiple> {
         val domande:MutableList<DomandeMultiple> = mutableListOf<DomandeMultiple>()

         for(element in allQuestionm){
             if(element.cod_argomento==argomento)
                 domande.add(element)
         }
         Log.d("QuizBottoneViewModel","Le domande sono queste: $domande")
         return  domande
     }
 }










