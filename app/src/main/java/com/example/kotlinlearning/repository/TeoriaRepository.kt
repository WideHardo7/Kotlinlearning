package com.example.kotlinlearning.repository

import com.example.kotlinlearning.database.teoria.TeoriaDao

class TeoriaRepository(private val teoriaDao:TeoriaDao) {

    fun getTheory(argomento:String): String{
         return teoriaDao.getTheoryfromArgument(argomento)
    }
}