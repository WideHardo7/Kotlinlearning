package com.example.kotlinlearning.repository

import android.util.Log
import androidx.lifecycle.LiveData
import com.example.kotlinlearning.database.argomenti.Argument
import com.example.kotlinlearning.database.argomenti.ArgumentDao
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class ArgumentRepository(private val argumentDao:ArgumentDao) {
    fun  readallargument():LiveData<List<Argument>> = argumentDao.getAllArgument()

     fun getArgument(argomento:String):LiveData<Argument>{
        return argumentDao.getArgument(argomento)

    }

    suspend fun getAllArgumentwithCoroutine(): List<Argument>{
        val argomenti:List<Argument>
        withContext(Dispatchers.IO){
            argomenti= argumentDao.getAllArgumentwithCouroutine()
        }
        Log.i("ArgumentRepository","Dentro funzione getAllArgumentwithCoroutine in repository, appena finito di eseguire l'operione di prendere la lista di argomenti, nel thread IO ")
        return argomenti
    }

    suspend fun insertArgument(argument:Argument){
        argumentDao.insertArgument(argument)
    }

    suspend fun insertMoreArgument( argomenti:Argument){
        argumentDao.insertMoreArgument(argomenti)
    }

}