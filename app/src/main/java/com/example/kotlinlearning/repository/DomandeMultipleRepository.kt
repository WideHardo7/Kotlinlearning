package com.example.kotlinlearning.repository

import android.util.Log
import com.example.kotlinlearning.database.domande.DomandeMultiple
import com.example.kotlinlearning.database.domande.DomandeMultipleDao
import com.example.kotlinlearning.database.teoria.Teoria
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
//viene utilizzata in QuizBottoneViewmodel
class DomandeMultipleRepository(private val domandemultipleDao: DomandeMultipleDao) {

     suspend fun getMultipleQuestion():List<DomandeMultiple>{
         val domandeMultiple:List<DomandeMultiple>

         withContext(Dispatchers.IO) {
             domandeMultiple = domandemultipleDao.getQuestion()
         }
         Log.i("DomandeMultiRepository","Dentro  metodo getMultipleQuestion in repository, appena finito di eseguire l'operione nel thread IO ")


         return domandeMultiple

    }
}