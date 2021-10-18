package com.example.kotlinlearning.viewmodel

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.kotlinlearning.database.AppDatabase
import com.example.kotlinlearning.database.teoria.Teoria
import com.example.kotlinlearning.database.teoria.TeoriaDao
import com.example.kotlinlearning.repository.TeoriaRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

 public class ArgomentoViewModel( application: Application):AndroidViewModel(application) {
        //lateinit var singolTheory:Teoria

    private val repository:TeoriaRepository
     var allTheory= listOf<Teoria>()


    init{
        val teoriaDao= AppDatabase.getInstance(application).teoriaDao()
        repository= TeoriaRepository(teoriaDao)
        Teoria()


    }
    /*fun getTheoryfromArgument(argomento:String){
        viewModelScope.launch(Dispatchers.IO){
            singolTheory=repository.getTheoryfromArgument((argomento))
        }
        Log.i("ArgomentoViewModel","$singolTheory")

    }*/
     suspend fun getTheory(){

         allTheory=repository.getTheory()
         Log.i("ArgomentoViewModel","$allTheory")


         }
     //ho necessita di inizializzare la variabile globale alltheory, ma getTheory() è una funzione suspend e quindi uso un viewmodelScope per usare la funzione
     fun Teoria(){
         viewModelScope.launch{
             getTheory()
         }
     }










}