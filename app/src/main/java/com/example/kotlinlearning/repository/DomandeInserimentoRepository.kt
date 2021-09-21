package com.example.kotlinlearning.repository

import com.example.kotlinlearning.database.domande.DomandeInserimentoDao
import com.example.kotlinlearning.database.domande.DomandeMultipleDao

class DomandeInserimentoRepository(private val domandeinsertDao: DomandeInserimentoDao) {

    fun getInsertQuestionfromArgument(argument:String){
        domandeinsertDao.getQuestionfromArgument(argument)
    }
}