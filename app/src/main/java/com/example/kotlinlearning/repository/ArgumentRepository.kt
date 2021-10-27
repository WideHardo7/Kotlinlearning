package com.example.kotlinlearning.repository

import androidx.lifecycle.LiveData
import com.example.kotlinlearning.database.argomenti.Argument
import kotlinx.coroutines.flow.Flow
import com.example.kotlinlearning.database.argomenti.ArgumentDao
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class ArgumentRepository(private val argumentDao:ArgumentDao) {
    fun  readallargument():LiveData<List<Argument>> = argumentDao.getAllArgument()

    /*suspend fun getAllArgument():LiveData<List<Argument>>{
        val readAllArgument: LiveData<List<Argument>>
        withContext(Dispatchers.IO){
            readAllArgument= argumentDao.getAllArgument()
        }
        return readAllArgument

    }*/

    suspend fun updateArgument(argument:Argument){
        argumentDao.update(argument)
    }

}