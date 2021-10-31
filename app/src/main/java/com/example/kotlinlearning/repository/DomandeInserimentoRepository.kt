package com.example.kotlinlearning.repository

import androidx.lifecycle.LiveData
import com.example.kotlinlearning.database.domande.DomandeInserimento
import com.example.kotlinlearning.database.domande.DomandeInserimentoDao
import com.example.kotlinlearning.database.domande.DomandeMultipleDao
//Viene utilizzata in QuizTastieraViewmodel
class DomandeInserimentoRepository(private val domandeinsertDao: DomandeInserimentoDao) {

    fun getInsertQuestionfromArgument(argument:String): LiveData<List<DomandeInserimento>>{
         return domandeinsertDao.getQuestionfromArgument(argument)
    }
}