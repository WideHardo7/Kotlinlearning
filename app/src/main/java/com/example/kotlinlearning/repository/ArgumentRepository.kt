package com.example.kotlinlearning.repository

import androidx.lifecycle.LiveData
import com.example.kotlinlearning.database.argomenti.Argument
import kotlinx.coroutines.flow.Flow
import com.example.kotlinlearning.database.argomenti.ArgumentDao
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class ArgumentRepository(private val argumentDao:ArgumentDao) {
    fun  readallargument():LiveData<List<Argument>> = argumentDao.getAllArgument()

     fun getArgument(argomento:String):LiveData<Argument>{
        return argumentDao.getArgument(argomento)

    }

    suspend fun insertArgument(argument:Argument){
        argumentDao.insert(argument)
    }

}