package com.example.kotlinlearning.repository

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.kotlinlearning.database.teoria.Teoria
import com.example.kotlinlearning.database.teoria.TeoriaDao
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class TeoriaRepository(private val teoriaDao:TeoriaDao) {



//una funzione suspend che esegue l'operazione getTheory() in un thread IO e poi ritorna nel main principale
// una volta finita l'operazione, restituendo la lisat di oggetti teoria

    suspend fun getTheory():List<Teoria> {
        val teoria:List<Teoria>

        withContext(Dispatchers.IO) {
            teoria = teoriaDao.getTheory()
        }
        Log.i("TeoriaRepository","Dentro getTheory in repository, appena finito di eseguire l'operione nel thread IO ")


            return teoria

    }







}