package com.example.kotlinlearning.repository

import com.example.kotlinlearning.database.domande.DomandeMultipleDao

class DomandeMultipleRepository(private val domandemultipleDao: DomandeMultipleDao) {

    fun getMultipleQuestionfromArgument(argument:String){
        domandemultipleDao.getQuestionfromArgument(argument)
    }
}