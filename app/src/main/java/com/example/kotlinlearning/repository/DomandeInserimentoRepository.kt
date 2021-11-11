package com.example.kotlinlearning.repository

import android.util.Log
import androidx.lifecycle.LiveData
import com.example.kotlinlearning.database.domande.DomandeInserimento
import com.example.kotlinlearning.database.domande.DomandeInserimentoDao
import com.example.kotlinlearning.database.domande.DomandeMultiple
import com.example.kotlinlearning.database.domande.DomandeMultipleDao
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

//Viene utilizzata in QuizBottoneViewmodel
class DomandeInserimentoRepository(private val domandeinsertDao: DomandeInserimentoDao) {

     suspend fun getInputQuestion(): List<DomandeInserimento>{

            val domandeInput:List<DomandeInserimento>

            withContext(Dispatchers.IO) {
                domandeInput = domandeinsertDao.getQuestion()
            }
            Log.i("DomandeMultiRepository","Dentro  metodo getMultipleQuestion in repository, appena finito di eseguire l'operione nel thread IO ")


            return domandeInput
    }
}