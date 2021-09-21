package com.example.kotlinlearning.repository

import androidx.lifecycle.LiveData
import com.example.kotlinlearning.database.argomenti.Argument
import com.example.kotlinlearning.database.argomenti.ArgumentDao

class ArgumentRepository(private val argumentDao:ArgumentDao) {
    val readAllArgument: List<Argument> = argumentDao.getAllArgument()

    fun updateArgument(argument:Argument){
        argumentDao.update(argument)
    }

}