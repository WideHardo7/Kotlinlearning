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

     var listargomenti= listOf<Argument>()
    var listOfargument:LiveData<List<Argument>> = MutableLiveData<List<Argument>>()

     //var tuttiargomenti: List<Argument> = listOf<Argument>()
     init{
         val argomentoDao= AppDatabase.getInstance(application).argumentDao()
         repository=ArgumentRepository(argomentoDao)
         listOfargument= repository.readallargument()


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
