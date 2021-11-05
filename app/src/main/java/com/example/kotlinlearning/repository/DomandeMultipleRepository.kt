package com.example.kotlinlearning.repository

import android.util.Log
import androidx.lifecycle.LiveData
import com.example.kotlinlearning.database.argomenti.Argument
import com.example.kotlinlearning.database.domande.DomandeMultiple
import com.example.kotlinlearning.database.domande.DomandeMultipleDao
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
//viene utilizzata in QuizBottoneViewmodel
class DomandeMultipleRepository(private val domandemultipleDao: DomandeMultipleDao) {

     suspend fun getMultipleQuestion():List<DomandeMultiple>{
         val domandeMultiple:List<DomandeMultiple>

         withContext(Dispatchers.IO) {
             domandeMultiple = domandemultipleDao.getAllQuestion()
         }
         Log.i("DomandeMultiRepository","Dentro  metodo getMultipleQuestion in repository, appena finito di eseguire l'operione nel thread IO ")


         return domandeMultiple

    }
    fun  readAllMultipleQuestion(): LiveData<List<DomandeMultiple>> = domandemultipleDao.getAllQuestionLiveData()
}